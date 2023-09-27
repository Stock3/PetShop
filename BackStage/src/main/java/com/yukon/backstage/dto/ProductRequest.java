package com.yukon.backstage.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class ProductRequest {
    private String title;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private Long adminId;

    public List<Long> getAttributeValueId() {
        return attributeValueId;
    }

    public void setAttributeValueId(List<Long> attributeValueId) {
        this.attributeValueId = attributeValueId;
    }
    private List<Long> attributeValueId;


//    private List<ProductAttributeRequest> productAttributeRequests;

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

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }


//    public List<ProductAttributeRequest> getProductAttributeRequests() {
//        return productAttributeRequests;
//    }
//
//    public void setProductAttributeRequests(List<ProductAttributeRequest> productAttributeRequests) {
//        this.productAttributeRequests = productAttributeRequests;
//    }
}
