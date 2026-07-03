package com.example.SpringbootProject.category.mapper;

import com.example.SpringbootProject.category.dto.response.CategoryDetailResponse;
import com.example.SpringbootProject.category.dto.response.CategoryResponse;
import com.example.SpringbootProject.category.dto.response.CategorySummary;
import com.example.SpringbootProject.category.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse toCreateResponse(Category category);

    CategorySummary toSummaryResponse(Category category);

    CategoryDetailResponse toDetailResponse(Category category);
}
