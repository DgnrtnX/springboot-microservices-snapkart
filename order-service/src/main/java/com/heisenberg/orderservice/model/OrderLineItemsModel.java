package com.heisenberg.orderservice.model;

import java.math.BigDecimal;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OrderLineItemsModel {
	private String skuCode;
	private BigDecimal price;
	private Integer quantity;
}
