package com.example.SpringbootProject.product;

import com.example.SpringbootProject.product.dto.request.ProductCreateRequest;
import com.example.SpringbootProject.product.dto.request.ProductUpdateRequest;
import com.example.SpringbootProject.product.dto.response.ProductCreateResponse;
import com.example.SpringbootProject.product.dto.response.ProductDetailResponse;
import com.example.SpringbootProject.product.dto.response.ProductResponse;
import com.example.SpringbootProject.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductCreateResponse createProduct(
            @Valid @RequestBody ProductCreateRequest request) {
        return productService.createProduct(request);
    }

    @GetMapping("/{id}")
    public ProductDetailResponse getProduct(
            @PathVariable Long id) {
        return productService.getProduct(id);
    }

    @GetMapping
    public Page<ProductResponse> getProducts(
            Pageable pageable) {
        return productService.getProducts(pageable);
    }

    @PutMapping("/{id}")
    public ProductDetailResponse updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductUpdateRequest request) {
        return productService.updateProduct(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(
            @PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @PostMapping("/{productId}/categories/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addCategory(
            @PathVariable Long productId,
            @PathVariable Long categoryId) {
        productService.addCategory(productId, categoryId);
    }

    @DeleteMapping("/{productId}/categories/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeCategory(
            @PathVariable Long productId,
            @PathVariable Long categoryId) {
        productService.removeCategory(productId, categoryId);
    }

}
