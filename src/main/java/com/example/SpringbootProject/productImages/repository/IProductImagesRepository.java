package com.example.SpringbootProject.productImages.repository;

import com.example.SpringbootProject.productImages.model.ProductImages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductImagesRepository extends JpaRepository<ProductImages, Long> {
}
