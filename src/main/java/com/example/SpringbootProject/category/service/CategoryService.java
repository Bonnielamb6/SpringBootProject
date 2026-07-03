package com.example.SpringbootProject.category.service;

import com.example.SpringbootProject.category.dto.request.CategoryCreateRequest;
import com.example.SpringbootProject.category.dto.request.CategoryUpdateRequest;
import com.example.SpringbootProject.category.dto.response.CategoryDetailResponse;
import com.example.SpringbootProject.category.dto.response.CategoryResponse;
import com.example.SpringbootProject.category.mapper.CategoryMapper;
import com.example.SpringbootProject.category.model.Category;
import com.example.SpringbootProject.category.repository.ICategoryRepository;
import com.example.SpringbootProject.exceptions.NoSuchCategoryException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final ICategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(ICategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public CategoryResponse saveCategory(CategoryCreateRequest request) {
        Category category = new Category(
                request.name(),
                request.description()
        );
        Category categoryCreated = categoryRepository.save(category);
        return categoryMapper.toCreateResponse(categoryCreated);
    }

    public CategoryDetailResponse getCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NoSuchCategoryException(id));
        return categoryMapper.toDetailResponse(category);
    }

    @Transactional
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NoSuchCategoryException(id));
        categoryRepository.delete(category);
    }

    @Transactional
    public CategoryDetailResponse updateCategory(Long id, CategoryUpdateRequest request) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NoSuchCategoryException(id));
        category.setName(request.name());
        category.setDescription(request.description());
        return categoryMapper.toDetailResponse(category);
    }

}
