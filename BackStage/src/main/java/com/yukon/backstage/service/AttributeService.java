package com.yukon.backstage.service;

import com.yukon.backstage.dto.AttributeRequest;
import com.yukon.backstage.entity.AttributeEntity;

import java.util.List;

public interface AttributeService {
    List<AttributeEntity> getAll();

    AttributeEntity getById(Long id);

    void delete(Long id);

    AttributeEntity createAttribute(AttributeEntity attribute) throws Exception;

    AttributeEntity updateAttribute(Long id, AttributeEntity attribute);
}
