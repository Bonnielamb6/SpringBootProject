package com.example.SpringbootProject.product.service;

import com.example.SpringbootProject.category.model.Category;
import com.example.SpringbootProject.category.repository.ICategoryRepository;
import com.example.SpringbootProject.exceptions.CategoryNotAssignedException;
import com.example.SpringbootProject.exceptions.NoSuchCategoryException;
import com.example.SpringbootProject.exceptions.NoSuchProductException;
import com.example.SpringbootProject.product.dto.request.ProductCreateRequest;
import com.example.SpringbootProject.product.dto.request.ProductUpdateRequest;
import com.example.SpringbootProject.product.dto.response.ProductCreateResponse;
import com.example.SpringbootProject.product.dto.response.ProductDetailResponse;
import com.example.SpringbootProject.product.mapper.ProductMapper;
import com.example.SpringbootProject.product.model.Product;
import com.example.SpringbootProject.product.repository.IProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final IProductRepository productRepository;
    private final ICategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public ProductService(IProductRepository productRepository, ICategoryRepository categoryRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    @Transactional
    public ProductCreateResponse saveProduct(ProductCreateRequest productToAdd) {
        Product product = new Product(
                productToAdd.name(),
                productToAdd.description(),
                productToAdd.stock()
        );
        Product productCreated = productRepository.save(product);
        return productMapper.toCreateResponse(productCreated);
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NoSuchProductException(id));
        productRepository.delete(product);
    }

    public ProductDetailResponse getProduct(Long id) {
        Product productToReturn = productRepository.findById(id).orElseThrow(() -> new NoSuchProductException(id));
        return productMapper.toDetailResponse(productToReturn);
    }

    @Transactional
    public ProductDetailResponse updateProduct(Long id, ProductUpdateRequest request) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NoSuchProductException(id));
        product.setName(request.name());
        product.setDescription(request.description());
        product.setUnitPrice(request.unitPrice());
        product.setStock(request.stock());
        return productMapper.toDetailResponse(product);
    }

    @Transactional
    public void addCategory(Long productId, Long category_id) {
        Category category = categoryRepository.findById(category_id).orElseThrow(() -> new NoSuchCategoryException(category_id));
        Product product = productRepository.findById(productId).orElseThrow(() -> new NoSuchProductException(productId));
        product.addCategory(category);
        productRepository.save(product);
    }

    @Transactional
    public void removeCategory(Long productId, Long category_id) {
        Category category = categoryRepository.findById(category_id).orElseThrow(() -> new NoSuchCategoryException(category_id));
        Product product = productRepository.findById(productId).orElseThrow(() -> new NoSuchProductException(productId));
        if (!product.getCategories().contains(category)) {
            throw new CategoryNotAssignedException(productId, category_id);
        }
        product.removeCategory(category);
        productRepository.save(product);
    }


}
