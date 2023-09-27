package com.yukon.backstage.mapper;

import com.yukon.backstage.dto.*;
import com.yukon.backstage.entity.AttributeEntity;
import com.yukon.backstage.entity.AttributeValueEntity;
import org.mapstruct.Mapper;

@Mapper
public interface AttributeToAttributeDtoMapper {
    AttributeValueEntity attributeValueDtoToAttributeValue(AttributeValueRequest attributeDto);

//    AttributeValueEntity productAttributeDtoToAttributeValue(ProductAttributeRequest attributeRequest);

    AttributeValueResponse attributeValueToAttributeValueDto(AttributeValueEntity attributeValue);

    AttributeEntity attributeDtoToAttribute(AttributeRequest attributeDto);

    AttributeResponse attributeToAttributeDto(AttributeEntity attribute);
}
