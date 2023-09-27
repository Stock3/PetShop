package com.yukon.backstage.service.impl;

import com.yukon.backstage.entity.AttributeValueEntity;
import com.yukon.backstage.entity.ProductEntity;
import com.yukon.backstage.mapper.ProductToProductDtoMapper;
import com.yukon.backstage.repository.ProductRepository;
import com.yukon.backstage.service.AttributeValueService;
import com.yukon.backstage.service.ProductService;
import jakarta.transaction.Transactional;
import org.hibernate.ObjectNotFoundException;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final AttributeValueService attributeValueService;

    private final ProductToProductDtoMapper mapper = Mappers.getMapper(ProductToProductDtoMapper.class);

    public ProductServiceImpl(ProductRepository productRepository, AttributeValueService attributeValueService) {
        this.productRepository = productRepository;
        this.attributeValueService = attributeValueService;
    }

    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Page<ProductEntity> getAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public ProductEntity getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Not found object with such id: ", id));
    }

    @Override
    public List<ProductEntity> getProductByTitle(String title) {
        return productRepository.findAllByTitle(title);
    }

    @Transactional
    public ProductEntity createProduct(ProductEntity productEntity, List<Long> attrValueId) {
        List<AttributeValueEntity> attributeValueEntityList = new ArrayList<>();
        for (var item : attrValueId){
            attributeValueEntityList.add(attributeValueService.getById(item));
        }
        productEntity.setProductAttributeRequests(attributeValueEntityList);
        return productRepository.save(productEntity);
    }

    @Transactional
    public ProductEntity updateProduct(Long id, ProductEntity newProduct, List<Long> attrValueId) {
        ProductEntity product = productRepository.findById(id).orElseThrow(RuntimeException::new);
        List<AttributeValueEntity> attributeValueEntityList = new ArrayList<>();
        for (var item : attrValueId){
            attributeValueEntityList.add(attributeValueService.getById(item));
        }
        newProduct.setProductAttributeRequests(attributeValueEntityList);
        newProduct.setId(id);
        newProduct.setCreatedAt(product.getCreatedAt());
        return productRepository.save(newProduct);

    }


    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductEntity> findProductByField(String fieldName, String value) {
//        return productRepository.findProductEntityBy(fieldName, value);
        return productRepository.findProductEntityBy(fieldName, value);
    }

    @Override
    public List<ProductEntity> getProductByPrice(BigDecimal price) {
        return productRepository.findAllByPrice(price);
    }
}
