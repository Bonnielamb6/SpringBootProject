package com.example.SpringbootProject.category.service;

import com.example.SpringbootProject.category.dto.request.CategoryCreateRequest;
import com.example.SpringbootProject.category.dto.response.CategoryResponse;
import com.example.SpringbootProject.category.model.Category;
import com.example.SpringbootProject.category.repository.ICategoryRepository;
import com.example.SpringbootProject.exceptions.NoSuchCategoryException;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final ICategoryRepository categoryRepository;

    public CategoryService(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponse saveCategory(CategoryCreateRequest categoryCreateRequest) {
        Category category = new Category(
                categoryCreateRequest.name(),
                categoryCreateRequest.description(),
                null
        );
        Category categoryCreated = categoryRepository.save(category);
        return new CategoryResponse(
                categoryCreated.getId(),
                categoryCreated.getName(),
                categoryCreated.getDescription()
        );
    }

    public CategoryResponse getCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NoSuchCategoryException(id));
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }

    public void deleteCategory(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new NoSuchCategoryException(id);
        }
    }

    public CategoryResponse updateCategory(Long id, CategoryCreateRequest categoryRequest) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NoSuchCategoryException(id));
        category.setName(categoryRequest.name());
        category.setDescription(categoryRequest.description());
        categoryRepository.save(category);
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }

}
