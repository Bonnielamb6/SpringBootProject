package com.example.SpringbootProject.category.model;

import com.example.SpringbootProject.common.model.BaseEntity;
import com.example.SpringbootProject.product.model.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "CATEGORIES")
public class Category extends BaseEntity {
    @Column(nullable = false)
    @NotBlank
    private String name;
    @Column(nullable = false)
    @NotBlank
    private String description;
    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();

    public void addProduct(Product product) {
        product.addCategory(this);
    }

    public void removeProduct(Product product) {
        product.removeCategory(this);
    }
}
