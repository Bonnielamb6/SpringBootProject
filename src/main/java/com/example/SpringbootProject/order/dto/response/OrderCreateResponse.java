package com.example.SpringbootProject.order.dto.response;

import com.example.SpringbootProject.orderItems.dto.response.OrderItemResponse;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record OrderCreateResponse(
        Long id,
        BigDecimal total,
        String status,
        Instant created_at,
        Long userId,
        String userName,
        List<OrderItemResponse> items
) {
}
