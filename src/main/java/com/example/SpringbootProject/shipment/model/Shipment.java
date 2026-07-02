package com.example.SpringbootProject.shipment.model;

import com.example.SpringbootProject.common.model.BaseEntity;
import com.example.SpringbootProject.order.model.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "SHIPMENTS")
public class Shipment extends BaseEntity {
    @Column(nullable = false)
    @NotBlank
    private String carrier;
    private String tracking_number;
    @Column(nullable = false)
    @NotBlank
    private String status;
    private Instant shipped_at;
    private Instant delivered_at;
    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    @NotNull
    private Order order;
}
