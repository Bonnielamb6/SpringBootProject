package com.example.SpringbootProject.order.model;

public enum OrderStatus {
    PENDING,
    PAID,
    PROCESSING,
    SHIPPED,
    DELIVERED,
    CANCELLED;

    public boolean canBeCancelled() {
        return switch (this) {
            case PENDING, PAID, PROCESSING -> true;
            case SHIPPED, DELIVERED, CANCELLED -> false;
        };
    }
}
