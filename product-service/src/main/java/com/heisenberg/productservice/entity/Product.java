package com.heisenberg.productservice.entity;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import lombok.Data;
import lombok.experimental.Accessors;

@Document("product")
@Accessors(chain = true)
@Data
public class Product {
	@Id
	private String id;
	private String name;
	private String description;
	private BigDecimal price;
}