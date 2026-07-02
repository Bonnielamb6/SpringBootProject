package com.example.SpringbootProject.category.repository;

import com.example.SpringbootProject.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
