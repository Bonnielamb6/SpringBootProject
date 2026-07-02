package com.example.SpringbootProject.productImages.model;

import com.example.SpringbootProject.common.model.BaseEntity;
import com.example.SpringbootProject.product.model.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ProductImages extends BaseEntity {
    @NotBlank
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
