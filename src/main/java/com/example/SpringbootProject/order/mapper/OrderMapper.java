package com.example.SpringbootProject.order.mapper;

import com.example.SpringbootProject.order.dto.response.OrderCreateResponse;
import com.example.SpringbootProject.order.dto.response.OrderDetailResponse;
import com.example.SpringbootProject.order.dto.response.OrderSummaryResponse;
import com.example.SpringbootProject.order.model.Order;
import com.example.SpringbootProject.user.mapper.UserMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface OrderMapper {
    OrderCreateResponse toCreateResponse(Order order);

    OrderDetailResponse toDetailResponse(Order order);

    OrderSummaryResponse toSummaryResponse(Order order);
}
