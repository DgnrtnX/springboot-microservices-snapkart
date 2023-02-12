package com.heisenberg.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.heisenberg.productservice.entity.Product;

public interface ProductRepo extends MongoRepository<Product, String> {

}
