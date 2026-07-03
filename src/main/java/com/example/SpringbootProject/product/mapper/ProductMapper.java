package com.example.SpringbootProject.product.mapper;

import com.example.SpringbootProject.product.dto.response.ProductCreateResponse;
import com.example.SpringbootProject.product.dto.response.ProductDetailResponse;
import com.example.SpringbootProject.product.dto.response.ProductResponse;
import com.example.SpringbootProject.product.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponse toResponse(Product product);

    ProductDetailResponse toDetailResponse(Product product);

    ProductCreateResponse toCreateResponse(Product product);
}
