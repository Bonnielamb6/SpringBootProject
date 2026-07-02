package com.example.SpringbootProject.product.model;

import com.example.SpringbootProject.category.model.Category;
import com.example.SpringbootProject.common.model.BaseEntity;
import com.example.SpringbootProject.exceptions.CategoryAlreadyAssignedException;
import com.example.SpringbootProject.exceptions.CategoryNotAssignedException;
import com.example.SpringbootProject.orderItems.model.OrderItems;
import com.example.SpringbootProject.productImages.model.ProductImages;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private List<OrderItems> ordersItems = new ArrayList<>();
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImages> images = new ArrayList<>();

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
}
