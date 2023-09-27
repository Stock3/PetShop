package com.yukon.backstage.service;

import com.yukon.backstage.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    List<ProductEntity> getAllProducts();

    Page<ProductEntity> getAll(Pageable pageable);

    ProductEntity getProductById(Long id);

    List<ProductEntity> getProductByTitle(String title);

    ProductEntity createProduct(ProductEntity productEntity, List<Long> attributeValueId);

    ProductEntity updateProduct(Long id, ProductEntity newProduct, List<Long> attrValueId);

    void deleteProduct(Long todoId);

    List<ProductEntity> findProductByField(String fieldName, String value);

    List<ProductEntity> getProductByPrice(BigDecimal price);


}
