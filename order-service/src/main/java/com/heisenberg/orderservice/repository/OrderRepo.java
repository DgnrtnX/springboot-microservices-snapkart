package com.heisenberg.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heisenberg.orderservice.entity.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {

}
