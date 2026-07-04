package com.example.SpringbootProject.order.controller;

import com.example.SpringbootProject.order.dto.request.OrderCreateRequest;
import com.example.SpringbootProject.order.dto.response.OrderCreateResponse;
import com.example.SpringbootProject.order.dto.response.OrderDetailResponse;
import com.example.SpringbootProject.order.dto.response.OrderSummaryResponse;
import com.example.SpringbootProject.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderCreateResponse createOrder(
            @Valid @RequestBody OrderCreateRequest request) {
        return orderService.createOrder(request);
    }

    @GetMapping("/{id}")
    public OrderDetailResponse getOrder(
            @PathVariable Long id) {
        return orderService.getOrder(id);
    }

    @GetMapping("/users/{userId}")
    public Page<OrderSummaryResponse> getOrdersByUser(
            @PathVariable Long userId,
            Pageable pageable) {
        return orderService.getOrdersByUserId(userId, pageable);
    }

    @PatchMapping("/{id}/cancel")
    public OrderDetailResponse cancelOrder(
            @PathVariable Long id) {
        return orderService.cancelOrder(id);
    }

    @GetMapping
    public Page<OrderSummaryResponse> getOrders(
            Pageable pageable) {
        return orderService.getOrders(pageable);
    }
}

