package com.yukon.backstage.service;

import com.yukon.backstage.dto.AttributeValueRequest;
import com.yukon.backstage.entity.*;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;

public interface AttributeValueService {
    List<AttributeValueEntity> getAll();

    AttributeValueEntity getById(Long id);

    List<AttributeValueEntity> getAllAttributesById(Long id);

    void delete(Long id);

    AttributeValueEntity createAttributeValue(AttributeValueEntity attributeValue) throws InstanceAlreadyExistsException;

    AttributeValueEntity updateAttributeValue(Long id, AttributeValueEntity attributeValue);
}
