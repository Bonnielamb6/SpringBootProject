package com.example.SpringbootProject.order.service;

import com.example.SpringbootProject.exceptions.*;
import com.example.SpringbootProject.order.dto.request.OrderCreateRequest;
import com.example.SpringbootProject.order.dto.response.OrderCreateResponse;
import com.example.SpringbootProject.order.dto.response.OrderDetailResponse;
import com.example.SpringbootProject.order.dto.response.OrderSummaryResponse;
import com.example.SpringbootProject.order.mapper.OrderMapper;
import com.example.SpringbootProject.order.model.Order;
import com.example.SpringbootProject.order.model.OrderStatus;
import com.example.SpringbootProject.order.repository.IOrderRepository;
import com.example.SpringbootProject.orderItem.dto.request.OrderItemRequest;
import com.example.SpringbootProject.orderItem.model.OrderItem;
import com.example.SpringbootProject.product.model.Product;
import com.example.SpringbootProject.product.repository.IProductRepository;
import com.example.SpringbootProject.user.model.User;
import com.example.SpringbootProject.user.repository.IUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderService {
    private final IOrderRepository orderRepository;
    private final IUserRepository userRepository;
    private final IProductRepository productRepository;
    private final OrderMapper orderMapper;

    public OrderService(IOrderRepository orderRepository, IUserRepository userRepository, IProductRepository productRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderMapper = orderMapper;
    }

    @Transactional
    public OrderCreateResponse createOrder(OrderCreateRequest orderRequest) {
        User user = userRepository.findById(orderRequest.userId()).orElseThrow(() -> new NoSuchUserException(orderRequest.userId()));
        Order orderCreated = new Order(user);
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItemRequest item : orderRequest.items()) {
            Product product = productRepository.findById(item.productId()).orElseThrow(() -> new NoSuchProductException(item.productId()));
            if (product.getStock() < item.quantity()) {
                throw new InsufficientStockException(product.getId());
            }
            product.setStock(product.getStock() - item.quantity());
            OrderItem orderItem = new OrderItem(
                    orderCreated,
                    product,
                    item.quantity(),
                    product.getUnitPrice()
            );
            orderCreated.addItem(orderItem);
            total = total.add(product.getUnitPrice().multiply(BigDecimal.valueOf(item.quantity())));
        }
        orderCreated.setTotal(total);
        orderCreated.setStatus(OrderStatus.PENDING);
        orderRepository.save(orderCreated);
        return orderMapper.toCreateResponse(orderCreated);
    }

    public OrderDetailResponse getOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new NoSuchOrderException(id));

        return orderMapper.toDetailResponse(order);
    }

    public Page<OrderSummaryResponse> getOrdersByUserId(Long userId, Pageable pageable) {
        if (!userRepository.existsById(userId)) {
            throw new NoSuchUserException(userId);
        }
        Page<Order> userOrders = orderRepository.findByUserIdOrderByCreatedAtDesc(userId, pageable);
        return userOrders.map(orderMapper::toSummaryResponse);
    }

    @Transactional
    public OrderDetailResponse cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NoSuchOrderException(orderId));
        if (!order.getStatus().canBeCancelled()) {
            throw new InvalidOrderStatusException(
                    order.getStatus(),
                    "cancel"
            );
        }
        for (OrderItem item : order.getItems()) {
            Product product = item.getProduct();
            product.setStock(product.getStock() + item.getQuantity());
        }
        order.setStatus(OrderStatus.CANCELLED);
        return orderMapper.toDetailResponse(order);
    }

    public Page<OrderSummaryResponse> getOrders(Pageable pageable) {
        return orderRepository
                .findAll(pageable)
                .map(orderMapper::toSummaryResponse);
    }
}
