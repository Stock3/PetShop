package com.yukon.backstage.service.impl;

import com.yukon.backstage.dto.AttributeRequest;
import com.yukon.backstage.entity.AttributeEntity;
import com.yukon.backstage.exception.ObjectNotFoundException;
import com.yukon.backstage.mapper.AttributeToAttributeDtoMapper;
import com.yukon.backstage.repository.AttributeRepository;
import com.yukon.backstage.service.AttributeService;
import jakarta.transaction.Transactional;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttributeServiceImpl implements AttributeService {

    AttributeRepository attributeRepository;

    private AttributeToAttributeDtoMapper mapper = Mappers.getMapper(AttributeToAttributeDtoMapper.class);
    public AttributeServiceImpl(AttributeRepository attributeRepository) {
        this.attributeRepository = attributeRepository;
    }


    @Override
    public List<AttributeEntity> getAll() {
        return attributeRepository.findAll();
    }

    @Override
    public AttributeEntity getById(Long id) {
        return attributeRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Entity was not found  ID: ", id));
    }

    @Transactional
    public void delete(Long id) {
        attributeRepository.deleteById(id);
    }

    @Transactional
    public AttributeEntity createAttribute(AttributeEntity attribute) throws Exception {
        AttributeEntity existed = attributeRepository.findByTitle(attribute.getTitle());
        if (existed == null){
            attributeRepository.save(attribute);
            return attribute;
        }
        else {
            throw new Exception("Attribute already exists");
        }
    }

    @Transactional
    public AttributeEntity updateAttribute(Long id, AttributeEntity attribute) {
        AttributeEntity existedAttribute = attributeRepository.findById(id).orElseThrow(RuntimeException::new);
        if (existedAttribute != null){
            existedAttribute.setTitle(attribute.getTitle());
            attributeRepository.save(existedAttribute);
            return existedAttribute;
        }
        else {
            attributeRepository.save(attribute);
            return attribute;
        }
    }
}
