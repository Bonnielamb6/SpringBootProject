package com.example.SpringbootProject.product.dto.response;

import com.example.SpringbootProject.category.model.Category;
import com.example.SpringbootProject.productImages.model.ProductImages;

import java.util.List;
import java.util.Set;

public record ProductResponse(
        Long id,
        String name,
        String description,
        Set<Category> categorySet,
        List<ProductImages> imagesList
) {
}
