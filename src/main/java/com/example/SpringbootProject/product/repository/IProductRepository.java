package com.example.SpringbootProject.product.repository;

import com.example.SpringbootProject.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {
}
