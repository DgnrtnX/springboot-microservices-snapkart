package com.heisenberg.productservice.controller;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.heisenberg.productservice.model.ProductRequestModel;
import com.heisenberg.productservice.model.ProductResponseModel;
import com.heisenberg.productservice.service.ProductService;

@RestController
@RequestMapping("/api/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void CreateProduct(@RequestBody ProductRequestModel productModel) {
        log.info("inside product controller with product details");
        productService.createProduct(productModel);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<ProductResponseModel> getProduct() {
        log.info("inside product controller to get product details");
        return productService.getAllProducts();
    }

}	
