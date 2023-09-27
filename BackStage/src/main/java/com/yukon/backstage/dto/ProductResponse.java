package com.yukon.backstage.dto;


import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class ProductResponse {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private Instant createdAt;
    private Instant modifiedAt;
    private Long adminId;

    public List<AttributeValueResponse> getProductAttributeRequests() {
        return productAttributeRequests;
    }

    public void setProductAttributeRequests(List<AttributeValueResponse> productAttributeRequests) {
        this.productAttributeRequests = productAttributeRequests;
    }

List<AttributeValueResponse> productAttributeRequests;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
