package com.example.SpringbootProject.order.dto.request;

import com.example.SpringbootProject.orderItem.dto.request.OrderItemRequest;

import java.util.List;

public record OrderCreateRequest(
        Long userId,
        List<OrderItemRequest> items
) {
}
