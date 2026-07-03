package com.example.SpringbootProject.product.service;

import com.example.SpringbootProject.category.model.Category;
import com.example.SpringbootProject.category.repository.ICategoryRepository;
import com.example.SpringbootProject.exceptions.CategoryNotAssignedException;
import com.example.SpringbootProject.exceptions.NoSuchCategoryException;
import com.example.SpringbootProject.exceptions.NoSuchProductException;
import com.example.SpringbootProject.product.dto.request.ProductCreateRequest;
import com.example.SpringbootProject.product.dto.response.ProductCreateResponse;
import com.example.SpringbootProject.product.dto.response.ProductResponse;
import com.example.SpringbootProject.product.model.Product;
import com.example.SpringbootProject.product.repository.IProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final IProductRepository productRepository;
    private final ICategoryRepository categoryRepository;

    public ProductService(IProductRepository productRepository, ICategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public ProductCreateResponse saveProduct(ProductCreateRequest productToAdd) {
        Product product = new Product(
                productToAdd.name(),
                productToAdd.description(),
                productToAdd.stock()
        );
        Product productCreated = productRepository.save(product);
        return new ProductCreateResponse(
                productCreated.getId(),
                productCreated.getName(),
                productCreated.getDescription()
        );
    }

    public void deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new NoSuchProductException(id);
        }
    }

    public ProductResponse getProduct(Long id) {
        Product productToReturn = productRepository.findById(id).orElseThrow(() -> new NoSuchProductException(id));
        return new ProductResponse(
                productToReturn.getId(),
                productToReturn.getName(),
                productToReturn.getDescription(),
                productToReturn.getCategories(),
                productToReturn.getImages()
        );
    }

    public ProductCreateResponse updateProduct(Long id, ProductCreateRequest productUpdate) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NoSuchProductException(id));
        product.setName(productUpdate.name());
        product.setDescription(productUpdate.description());
        productRepository.save(product);
        return new ProductCreateResponse(
                product.getId(),
                product.getName(),
                product.getDescription()
        );
    }

    public void addCategory(Long productId, Long category_id) {
        Category category = categoryRepository.findById(category_id).orElseThrow(() -> new NoSuchCategoryException(category_id));
        Product product = productRepository.findById(productId).orElseThrow(() -> new NoSuchProductException(productId));
        product.addCategory(category);
        productRepository.save(product);
    }

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
