package com.example.SpringbootProject.orderItem.model;

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
public class OrderItem extends BaseEntity {
    @Column(nullable = false)
    @NotNull
    private Integer quantity;
    @Column(name = "unit_price", nullable = false)
    @NotNull
    private BigDecimal unitPrice;
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

    public OrderItem(Order order, Product product, Integer quantity, BigDecimal unitPrice) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
