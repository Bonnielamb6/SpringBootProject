package com.example.SpringbootProject.exceptions;

import com.example.SpringbootProject.order.model.OrderStatus;

public class InvalidOrderStatusException extends RuntimeException {
    public InvalidOrderStatusException(OrderStatus currentStatus, String operation) {
        super("Cannot " + operation + " an order with status " + currentStatus + ".");
    }
}
