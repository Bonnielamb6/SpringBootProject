package com.example.SpringbootProject.order.dto.response;

import com.example.SpringbootProject.order.model.OrderStatus;
import com.example.SpringbootProject.orderItem.dto.response.OrderItemResponse;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record OrderSummaryResponse(
        Long id,
        BigDecimal total,
        OrderStatus status,
        Instant createdAt
) {
}
