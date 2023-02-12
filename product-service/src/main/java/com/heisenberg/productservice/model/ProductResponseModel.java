package com.heisenberg.productservice.model;

import java.math.BigDecimal;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class ProductResponseModel {
	private String id;
	private String name;
	private String description;
	private BigDecimal price;
}
