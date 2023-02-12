package com.heisenberg.orderservice.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.heisenberg.orderservice.model.OrderRequestModel;
import com.heisenberg.orderservice.service.OrderService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
@Slf4j
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@CircuitBreaker(name="inventory", fallbackMethod = "fallbackMethod")
	@TimeLimiter(name = "inventory")
	@Retry(name = "inventory")
	public CompletableFuture<String> placeOrder(@RequestBody OrderRequestModel orderRequestModel) {
		log.info("inside order controller with order details");
		return CompletableFuture.supplyAsync(() ->orderService.placeOrder(orderRequestModel));
	}

	public CompletableFuture<String> fallbackMethod(@RequestBody OrderRequestModel orderRequestModel, RuntimeException runtimeException){
		return CompletableFuture.supplyAsync(() ->"Something went wrong. Order later");
	}
}
