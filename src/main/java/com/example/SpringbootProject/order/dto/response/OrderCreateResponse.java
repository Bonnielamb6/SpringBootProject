package com.example.SpringbootProject.order.dto.response;

import com.example.SpringbootProject.order.model.OrderStatus;

import java.math.BigDecimal;

public record OrderCreateResponse(
        Long id,
        BigDecimal total,
        OrderStatus status
) {
}
