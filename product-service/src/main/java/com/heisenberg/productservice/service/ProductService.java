package com.heisenberg.productservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heisenberg.productservice.entity.Product;
import com.heisenberg.productservice.model.ProductRequestModel;
import com.heisenberg.productservice.model.ProductResponseModel;
import com.heisenberg.productservice.repository.ProductRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {
	@Autowired
	private ProductRepo productRepo;

	public void createProduct(ProductRequestModel productModel) {
		Product product = new Product().setName(productModel.getName()).setDescription(productModel.getDescription())
				.setPrice(productModel.getPrice());

		productRepo.save(product);

		log.info("Product {} saved.", product.toString());

	}

	public List<ProductResponseModel> getAllProducts() {
		List<Product> getAllProducts = productRepo.findAll();
		return getAllProducts.stream().map(this::mapToProductResponseModel).toList();
	}

	private ProductResponseModel mapToProductResponseModel(Product product) {
		log.info("Product response for product {}.", product.toString());
		return new ProductResponseModel().setId(product.getId()).setName(product.getName())
				.setDescription(product.getDescription()).setPrice(product.getPrice());
	}
}
