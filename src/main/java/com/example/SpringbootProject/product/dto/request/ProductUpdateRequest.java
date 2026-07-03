package com.example.SpringbootProject.product.dto.request;

import java.math.BigDecimal;

public record ProductUpdateRequest(
        String name,
        String description,
        BigDecimal unitPrice,
        Integer stock
) {
}
