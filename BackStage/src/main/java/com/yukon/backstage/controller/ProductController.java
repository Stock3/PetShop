package com.yukon.backstage.controller;

import com.yukon.backstage.dto.ProductRequest;
import com.yukon.backstage.dto.ProductResponse;
import com.yukon.backstage.entity.ProductEntity;
import com.yukon.backstage.mapper.ProductToProductDtoMapper;
import com.yukon.backstage.service.ProductService;
import com.yukon.backstage.service.impl.ProductServiceImpl;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    ProductService productService;
    private ProductToProductDtoMapper mapper = Mappers.getMapper(ProductToProductDtoMapper.class);

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Long id) {
        ProductResponse product = mapper.productToDtoProduct(productService.getProductById(id));
        return ResponseEntity.ok(product);
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> productList = new ArrayList<>();
        productService.getAllProducts().forEach(product -> productList.add(mapper.productToDtoProduct(product)));
        return ResponseEntity.ok(productList);
    }

    /*magic*/
    @GetMapping(params = {"fieldName", "value"})
    public ResponseEntity<List<ProductEntity>> getProductsByTitle(@RequestParam final String fieldName, @RequestParam final String value) {
        List<ProductEntity> productEntityList = productService.findProductByField(fieldName, value);
        return new ResponseEntity<>(productEntityList, HttpStatus.OK);
    }

    @GetMapping(value = "/title/{title}")
    public ResponseEntity<List<ProductResponse>> getProductsByTitle(@PathVariable("title") String title) {
        List<ProductResponse> productList = new ArrayList<>();
        productService.getProductByTitle(title).forEach(product -> productList.add(mapper.productToDtoProduct(product)));
        return ResponseEntity.ok(productList);
    }

    @GetMapping(value = "/price/{price}")
    public ResponseEntity<List<ProductResponse>> getProductByPrice(@PathVariable("price") BigDecimal price) {
        List<ProductResponse> productList = new ArrayList<>();
        productService.getProductByPrice(price).forEach(product -> productList.add(mapper.productToDtoProduct(product)));
        return ResponseEntity.ok(productList);
    }

    @GetMapping(value = "/page", params = {"page", "size"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductResponse>> getPaginated(@RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<ProductResponse> productList = new ArrayList<>();
        productService.getAll(pageable).forEach(product -> productList.add(mapper.productToDtoProduct(product)));
        return ResponseEntity.ok(productList);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse createProduct(@RequestBody ProductRequest product) {
        return mapper.productToDtoProduct(productService.createProduct(mapper.productDtoToProduct(product), product.getAttributeValueId()));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse updateUser(@PathVariable("id") Long id,
                                      @RequestBody ProductRequest product) {
        return mapper.productToDtoProduct(productService.updateProduct(id, mapper.productDtoToProduct(product), product.getAttributeValueId()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }

}
