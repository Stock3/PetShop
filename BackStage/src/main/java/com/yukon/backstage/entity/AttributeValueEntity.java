package com.yukon.backstage.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "attribute_values")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttributeValueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    AttributeEntity attribute;

    @Column(name = "value")
    @NotEmpty(message = "This field shouldn't be empty")
    String value;

    public AttributeValueEntity(Long id, AttributeEntity attribute, String value) {
        this.id = id;
        this.attribute = attribute;
        this.value = value;
    }

    public AttributeValueEntity() {
    }

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

    public AttributeEntity getAttribute() {
        return attribute;
    }

    public void setAttribute(AttributeEntity attribute) {
        this.attribute = attribute;
    }
}