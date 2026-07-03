package com.example.SpringbootProject.order.model;

import com.example.SpringbootProject.common.model.BaseEntity;
import com.example.SpringbootProject.orderItem.model.OrderItem;
import com.example.SpringbootProject.payment.model.Payment;
import com.example.SpringbootProject.shipment.model.Shipment;
import com.example.SpringbootProject.user.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order extends BaseEntity {
    @Column(nullable = false)
    @NotNull
    private BigDecimal total;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private OrderStatus status;
    @Column(name = "created_at", nullable = false)
    @NotNull
    private Instant createdAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();
    @OneToOne(mappedBy = "order")
    private Shipment shipment;
    @OneToMany(mappedBy = "order")
    private Set<Payment> payments = new HashSet<>();

    public Order(User user) {
        this.user = user;
        this.status = OrderStatus.PENDING;
        this.total = BigDecimal.ZERO;
        this.createdAt = Instant.now();
    }

    public void addItem(OrderItem item) {
        if (item == null) {
            return;
        }
        items.add(item);
        item.setOrder(this);
    }
}
