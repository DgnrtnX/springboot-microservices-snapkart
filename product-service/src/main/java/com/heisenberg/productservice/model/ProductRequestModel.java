package com.heisenberg.productservice.model;

import java.math.BigDecimal;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class ProductRequestModel {
	private String name;
	private String description;
	private BigDecimal price;
}
