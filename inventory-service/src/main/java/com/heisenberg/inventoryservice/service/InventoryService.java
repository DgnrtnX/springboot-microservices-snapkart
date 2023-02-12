package com.heisenberg.inventoryservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heisenberg.inventoryservice.model.InventoryResponseModel;
import com.heisenberg.inventoryservice.repository.InventoryRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InventoryService {

	@Autowired
	private InventoryRepo inventoryRepo;

	@Transactional(readOnly = true)
	public List<InventoryResponseModel> isInStock(List<String> skuCode) {
		log.info("finding data for skuCode: {}", skuCode);

		return inventoryRepo.findBySkuCodeIn(skuCode).stream().map(inventory -> new InventoryResponseModel()
				.setSkuCode(inventory.getSkuCode()).setInStock(inventory.getQuantity() > 0)).toList();
	}

}
