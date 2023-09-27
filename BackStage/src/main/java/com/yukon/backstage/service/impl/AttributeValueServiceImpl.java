package com.yukon.backstage.service.impl;

import com.yukon.backstage.dto.AttributeValueRequest;
import com.yukon.backstage.entity.*;
import com.yukon.backstage.exception.ObjectNotFoundException;
import com.yukon.backstage.mapper.AttributeToAttributeDtoMapper;
import com.yukon.backstage.repository.AttributeValueRepository;
import com.yukon.backstage.service.AttributeService;
import com.yukon.backstage.service.AttributeValueService;
import jakarta.transaction.Transactional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;

@Service
public class AttributeValueServiceImpl implements AttributeValueService {

    AttributeValueRepository attributeRepository;
    AttributeService attributeService;

    private AttributeToAttributeDtoMapper mapper = Mappers.getMapper(AttributeToAttributeDtoMapper.class);

    @Autowired
    public AttributeValueServiceImpl(AttributeService attributeValueService,
                                     AttributeValueRepository attributeRepository) {
        this.attributeService = attributeValueService;
        this.attributeRepository = attributeRepository;
    }

    @Override
    public List<AttributeValueEntity> getAll() {
        return attributeRepository.findAll();
    }

    @Override
    public AttributeValueEntity getById(Long id) {
        return attributeRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Entity was not found  ID: ", id));
    }

    @Override
    public List<AttributeValueEntity> getAllAttributesById(Long id) {
        return attributeRepository.findByAttribute_Id(id);
    }

    @Transactional
    public void delete(Long id) {
        attributeRepository.deleteById(id);
    }

    @Transactional
    public AttributeValueEntity createAttributeValue(AttributeValueEntity attributeValue) throws InstanceAlreadyExistsException {
        AttributeEntity attribute = attributeService.getById(attributeValue.getAttribute().getId());
        if (null != attributeRepository.findByValueAndAttribute(attributeValue.getValue(), attribute.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Attribute value already exists");
        }
        attributeValue.setAttribute(attribute);
        attributeRepository.save(attributeValue);
        return attributeValue;
    }

    @Transactional
    public AttributeValueEntity updateAttributeValue(Long id, AttributeValueEntity attributeValue) {
        AttributeValueEntity existingAttributeValue = attributeRepository.findById(id).orElseThrow(RuntimeException::new);
        if (existingAttributeValue != null) {
            existingAttributeValue.setValue(attributeValue.getValue());
            attributeRepository.save(existingAttributeValue);
            return existingAttributeValue;
        } else {
            attributeValue.setAttribute(attributeService.getById(attributeValue.getAttribute().getId()));
            attributeRepository.save(attributeValue);
            return attributeValue;
        }
    }
}
