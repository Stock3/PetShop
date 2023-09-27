package com.yukon.backstage.controller;

import com.yukon.backstage.dto.AttributeValueRequest;
import com.yukon.backstage.dto.AttributeValueResponse;
import com.yukon.backstage.entity.AttributeEntity;
import com.yukon.backstage.entity.AttributeValueEntity;
import com.yukon.backstage.mapper.AttributeToAttributeDtoMapper;
import com.yukon.backstage.service.AttributeValueService;
import com.yukon.backstage.service.impl.AttributeValueServiceImpl;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/api/attributeValues", produces = MediaType.APPLICATION_JSON_VALUE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AttributeValueController {
    AttributeValueService attributeValueService;
    AttributeToAttributeDtoMapper mapper = Mappers.getMapper(AttributeToAttributeDtoMapper.class);

    public AttributeValueController(AttributeValueServiceImpl attributeValueService) {
        this.attributeValueService = attributeValueService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AttributeValueResponse>> getAll() {
        List<AttributeValueResponse> attributeValues = new ArrayList<>();
        attributeValueService.getAll().forEach(attributeValue ->
                attributeValues.add(mapper.attributeValueToAttributeValueDto(attributeValue)));
        return ResponseEntity.ok(attributeValues);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AttributeValueResponse> getAttributeValueById(Long id) {
        AttributeValueResponse attributeValue = mapper.attributeValueToAttributeValueDto(attributeValueService.getById(id));
        return ResponseEntity.ok(attributeValue);
    }

    @GetMapping("attribute/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AttributeValueResponse>> getAllByAttributeId(@PathVariable("id") Long id) {
        List<AttributeValueResponse> attributeValues = new ArrayList<>();
        attributeValueService.getAllAttributesById(id).forEach(attributeValue ->
                attributeValues.add(mapper.attributeValueToAttributeValueDto(attributeValue)));
        return ResponseEntity.ok(attributeValues);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AttributeValueResponse> createAttributeValue(@RequestBody AttributeValueRequest attribute) throws InstanceAlreadyExistsException {
        return ResponseEntity.ok(mapper.attributeValueToAttributeValueDto(attributeValueService.createAttributeValue(Convert(attribute))));
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AttributeValueResponse> updateAttributeValue(@PathVariable("id") Long id,
                                                                       @RequestBody AttributeValueRequest attribute) {
        return ResponseEntity.ok(mapper.attributeValueToAttributeValueDto(attributeValueService.updateAttributeValue(id, Convert(attribute))));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAttributeValue(@PathVariable("id") Long id) {
        attributeValueService.delete(id);
    }

    private AttributeValueEntity Convert(AttributeValueRequest request) {
        if ( request == null ) {
            return null;
        }

        AttributeValueEntity attributeValueEntity = new AttributeValueEntity();

        attributeValueEntity.setValue( request.getValue() );
        attributeValueEntity.setAttribute(new AttributeEntity(request.getAttribute_id(),null));
        return attributeValueEntity;
    }
}
