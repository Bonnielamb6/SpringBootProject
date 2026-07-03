package com.example.SpringbootProject.orderItem.repository;

import com.example.SpringbootProject.orderItem.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderItemsRepository extends JpaRepository<OrderItem, Long> {
}
