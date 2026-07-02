package com.example.SpringbootProject.payment.model;

import com.example.SpringbootProject.common.model.BaseEntity;
import com.example.SpringbootProject.order.model.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "PAYMENTS")
public class Payment extends BaseEntity {
    @Column(nullable = false)
    @NotBlank
    private String provider;
    @Column(nullable = false)
    @NotNull
    private BigDecimal amount;
    @Column(nullable = false)
    @NotBlank
    private String status;
    @Column(nullable = false)
    @NotNull
    private Instant created_at;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
