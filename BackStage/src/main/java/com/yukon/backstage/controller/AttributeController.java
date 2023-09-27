package com.yukon.backstage.controller;

import com.yukon.backstage.dto.AttributeRequest;
import com.yukon.backstage.dto.AttributeResponse;
import com.yukon.backstage.mapper.AttributeToAttributeDtoMapper;
import com.yukon.backstage.service.AttributeService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/api/attributes", produces = MediaType.APPLICATION_JSON_VALUE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AttributeController {
    AttributeService attributeService;
    AttributeToAttributeDtoMapper mapper = Mappers.getMapper(AttributeToAttributeDtoMapper.class);

    public AttributeController(AttributeService attributeValueService) {
        this.attributeService = attributeValueService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AttributeResponse>> getAll() {
        List<AttributeResponse> attributes = new ArrayList<>();
        attributeService.getAll().forEach(attribute ->
                attributes.add(mapper.attributeToAttributeDto(attribute)));
        return ResponseEntity.ok(attributes);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AttributeResponse> getAttributeById(Long id) {
        AttributeResponse attribute = mapper.attributeToAttributeDto(attributeService.getById(id));
        return ResponseEntity.ok(attribute);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AttributeResponse> createAttribute(@RequestBody AttributeRequest attribute) throws Exception {
        return ResponseEntity.ok(mapper.attributeToAttributeDto(attributeService.createAttribute(mapper.attributeDtoToAttribute(attribute))));
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AttributeResponse> updateAttribute(@PathVariable("id") Long id,
                                                             @RequestBody AttributeRequest attribute) {
        return ResponseEntity.ok(mapper.attributeToAttributeDto(attributeService.updateAttribute(id, mapper.attributeDtoToAttribute(attribute))));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAttribute(@PathVariable("id") Long id) {
        attributeService.delete(id);
    }
}
