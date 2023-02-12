package com.heisenberg.orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.heisenberg.orderservice.event.OrderPlacedEvent;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.heisenberg.orderservice.constants.OrderConstants;
import com.heisenberg.orderservice.entity.Order;
import com.heisenberg.orderservice.entity.OrderLineItems;
import com.heisenberg.orderservice.model.InventoryResponseModel;
import com.heisenberg.orderservice.model.OrderLineItemsModel;
import com.heisenberg.orderservice.model.OrderRequestModel;
import com.heisenberg.orderservice.repository.OrderRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class OrderService {

	@Autowired
	WebClient.Builder webClientBuilder;
	@Autowired
	private OrderRepo orderRepo;
	@Autowired
	private Tracer tracer;

	@Autowired
	KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

	public String placeOrder(OrderRequestModel orderRequestModel) {
		Order order = new Order();

		order.setOrderNumber(UUID.randomUUID().toString());
		List<OrderLineItems> orderLineItemsList = orderRequestModel.getOrderLineItemsModel().stream()
				.map(this::mapModelToEntity).toList();

		order.setOrderLineItems(orderLineItemsList);

		List<String> skuCodes = order.getOrderLineItems().stream().map(OrderLineItems::getSkuCode).toList();

		log.info("list of skuCode:{}", skuCodes);

		Span inventoryServiceLookup = tracer.nextSpan().name("inventoryServiceLookup");
		try(Tracer.SpanInScope spanInScope = tracer.withSpan(inventoryServiceLookup.start())){
			// call inventory Service and place order if product is in stock
			InventoryResponseModel[] inventoryResponseModels = webClientBuilder.build().get()
					.uri(OrderConstants.INVENTORY_URL, UriBuilder -> UriBuilder.queryParam("skuCode", skuCodes).build())
					.retrieve().bodyToMono(InventoryResponseModel[].class).block();

			boolean allProductsInStock = Arrays.stream(inventoryResponseModels).allMatch(InventoryResponseModel::isInStock);

			if (allProductsInStock) {
				orderRepo.save(order);
				kafkaTemplate.send("notificationTopic",new OrderPlacedEvent(order.getOrderNumber()));
				log.info("Order placed for orders: {}", order.getOrderNumber());
				return "Order places successfully";
			} else {
				throw new IllegalArgumentException("Product not in stock, try again later");
			}
		}finally{
			inventoryServiceLookup.end();
		}
	}

	private OrderLineItems mapModelToEntity(OrderLineItemsModel orderLineItemsModel) {
		return new OrderLineItems().setPrice(orderLineItemsModel.getPrice())
				.setQuantity(orderLineItemsModel.getQuantity()).setSkuCode(orderLineItemsModel.getSkuCode());

	}
}
