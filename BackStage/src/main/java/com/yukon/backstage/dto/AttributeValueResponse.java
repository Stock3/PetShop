package com.yukon.backstage.dto;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttributeValueResponse {
    Long id;
    AttributeResponse attribute;
    String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public AttributeResponse getAttribute() {
        return attribute;
    }

    public void setAttribute(AttributeResponse attribute) {
        this.attribute = attribute;
    }
}
