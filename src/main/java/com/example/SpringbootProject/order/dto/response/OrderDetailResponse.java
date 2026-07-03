package com.example.SpringbootProject.order.dto.response;

import com.example.SpringbootProject.orderItem.dto.response.OrderItemResponse;
import com.example.SpringbootProject.user.dto.response.UserSummary;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record OrderDetailResponse(
        Long id,
        BigDecimal total,
        String status,
        Instant created_at,
        UserSummary user,
        List<OrderItemResponse> items
) {
}
