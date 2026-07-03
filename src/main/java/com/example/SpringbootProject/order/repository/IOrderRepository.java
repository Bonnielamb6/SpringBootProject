package com.example.SpringbootProject.order.repository;

import com.example.SpringbootProject.order.model.Order;
import com.example.SpringbootProject.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findByUserIdOrderByCreatedAtDesc(Long userId, Pageable pageable);

    Long user(User user);
}
