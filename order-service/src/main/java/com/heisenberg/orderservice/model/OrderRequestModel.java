package com.heisenberg.orderservice.model;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OrderRequestModel {
	private List<OrderLineItemsModel> orderLineItemsModel;

}
