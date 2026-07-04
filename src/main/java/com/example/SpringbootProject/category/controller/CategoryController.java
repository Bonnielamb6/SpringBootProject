package com.example.SpringbootProject.category.controller;

import com.example.SpringbootProject.category.dto.request.CategoryCreateRequest;
import com.example.SpringbootProject.category.dto.request.CategoryUpdateRequest;
import com.example.SpringbootProject.category.dto.response.CategoryDetailResponse;
import com.example.SpringbootProject.category.dto.response.CategoryResponse;
import com.example.SpringbootProject.category.dto.response.CategorySummary;
import com.example.SpringbootProject.category.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse createCategory(
            @Valid @RequestBody CategoryCreateRequest request) {
        return categoryService.createCategory(request);
    }

    @GetMapping("/{id}")
    public CategoryDetailResponse getCategory(
            @PathVariable Long id) {
        return categoryService.getCategory(id);
    }

    @GetMapping
    public Page<CategorySummary> getCategories(Pageable pageable) {
        return categoryService.getCategories(pageable);
    }

    @PutMapping("/{id}")
    public CategoryDetailResponse updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryUpdateRequest request) {
        return categoryService.updateCategory(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeCategory(
            @PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
