package com.example.SpringbootProject.orderItems.repository;

import com.example.SpringbootProject.orderItems.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderItemsRepository extends JpaRepository<OrderItems, Long> {
}
