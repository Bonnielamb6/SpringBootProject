package com.example.SpringbootProject.order.service;

import com.example.SpringbootProject.order.dto.response.OrderCreateResponse;
import com.example.SpringbootProject.order.repository.IOrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final IOrderRepository orderRepository;

    public OrderService(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public OrderCreateResponse saveOrder(OrderCreateRequest orderRequest, Long userId){

        return new
    }
}
