package com.yukon.backstage.mapper;

import com.yukon.backstage.dto.ProductRequest;
import com.yukon.backstage.dto.ProductResponse;
import com.yukon.backstage.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ProductToProductDtoMapper {
    ProductEntity productDtoToProduct(ProductRequest productDto);
    ProductResponse productToDtoProduct(ProductEntity productEntity);


}
