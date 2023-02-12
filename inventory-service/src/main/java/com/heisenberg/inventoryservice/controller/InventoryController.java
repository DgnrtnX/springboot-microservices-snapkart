package com.heisenberg.inventoryservice.controller;

import java.util.List;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.heisenberg.inventoryservice.model.InventoryResponseModel;
import com.heisenberg.inventoryservice.service.InventoryService;

@RestController
@RequestMapping("api/inventory")
@Slf4j
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponseModel> isInStock(@RequestParam List<String> skuCode) {
        log.info("inside inventory service controller with skuCode list");
        return inventoryService.isInStock(skuCode);
    }
}
