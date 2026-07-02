package com.example.SpringbootProject.orderItems.model;

import com.example.SpringbootProject.common.model.BaseEntity;
import com.example.SpringbootProject.order.model.Order;
import com.example.SpringbootProject.product.model.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ORDER_ITEMS")
public class OrderItems extends BaseEntity {
    @Column(nullable = false)
    @NotNull
    private Integer quantity;
    @Column(nullable = false)
    @NotNull
    private BigDecimal unit_price;
    @Column(nullable = false)
    @NotNull
    private BigDecimal subtotal;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @NotNull
    private Product product;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @NotNull
    private Order order;
}
