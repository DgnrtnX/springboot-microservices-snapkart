package com.heisenberg.inventoryservice.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class InventoryResponseModel {
	private String skuCode;
	private boolean isInStock;

}
