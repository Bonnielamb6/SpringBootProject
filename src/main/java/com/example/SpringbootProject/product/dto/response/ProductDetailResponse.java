package com.example.SpringbootProject.product.dto.response;

import com.example.SpringbootProject.category.dto.response.CategorySummary;
import com.example.SpringbootProject.productImages.dto.response.ProductImageResponse;

import java.math.BigDecimal;
import java.util.List;

public record ProductDetailResponse(
        Long id,
        String name,
        String description,
        BigDecimal unitPrice,
        Integer stock,
        List<CategorySummary> categories,
        List<ProductImageResponse> images
) {
}
