package com.example.SpringbootProject.order.dto.response;

import com.example.SpringbootProject.order.model.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;

public record OrderSummaryResponse(
        Long id,
        BigDecimal total,
        OrderStatus status,
        Instant createdAt
) {
}
