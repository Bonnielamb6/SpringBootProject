package com.example.SpringbootProject.product.model;

import com.example.SpringbootProject.category.model.Category;
import com.example.SpringbootProject.common.model.BaseEntity;
import com.example.SpringbootProject.exceptions.CategoryAlreadyAssignedException;
import com.example.SpringbootProject.exceptions.CategoryNotAssignedException;
import com.example.SpringbootProject.orderItem.model.OrderItem;
import com.example.SpringbootProject.productImages.model.ProductImages;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "PRODUCTS")
public class Product extends BaseEntity {
    @Column(nullable = false)
    @NotBlank
    private String name;
    @Column(nullable = false)
    @NotBlank
    private String description;
    @ManyToMany
    @JoinTable(
            name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();
    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderLines = new ArrayList<>();
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImages> images = new ArrayList<>();
    @Column(name = "unit_price", nullable = false)
    @NotNull
    private BigDecimal unitPrice;
    private Integer stock;

    @Column(nullable = false)
    public void addCategory(Category category) {
        if (this.categories.contains(category)) {
            throw new CategoryAlreadyAssignedException(this.getId(), category.getId());
        }
        this.categories.add(category);
        category.getProducts().add(this);
    }

    public void removeCategory(Category category) {
        if (!this.categories.contains(category)) {
            throw new CategoryNotAssignedException(this.getId(), category.getId());
        }
        this.categories.remove(category);
        category.getProducts().remove(this);
    }

    public Product(String name, String description, Integer stock) {
        this.name = name;
        this.description = description;
        this.stock = stock;
    }
}
