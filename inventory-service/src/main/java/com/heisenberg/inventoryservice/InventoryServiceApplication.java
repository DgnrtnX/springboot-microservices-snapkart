package com.heisenberg.inventoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner loadData(InventoryRepo inventoryRepo) {
//		return args -> {
//			Inventory inventory = new Inventory().setSkuCode("sku010").setQuantity(69);
//
//			Inventory inventory1 = new Inventory().setSkuCode("sku015").setQuantity(2);
//
//			inventoryRepo.save(inventory);
//			inventoryRepo.save(inventory1);
//		};
//	}
}
