package com.yukon.backstage.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "products")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @Column(name = "title")
    @NotEmpty(message = "This field shouldn't be empty")
    String title;

    @Column(name = "description")
    @NotEmpty(message = "This field shouldn't be empty")
    String description;

    @Column(name = "product_price")
    @NotNull(message = "This field shouldn't be empty")
    BigDecimal price;

    @Column(name = "quantity")
    @NotNull(message = "This field shouldn't be empty")
    Integer quantity;

    @CreationTimestamp
    @Column(name = "created_at")
    Instant createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at")
    Instant modifiedAt;
    @Column(name = "admin_id")
    @NotNull(message = "This field shouldn't be empty")
    Long adminId;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(joinColumns = @JoinColumn(name = "product_id"))
    List<AttributeValueEntity> productAttributeRequests;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<AttributeValueEntity> getProductAttributeRequests() {
        return this.productAttributeRequests == null ? null : new ArrayList<>(this.productAttributeRequests);
    }

    public void setProductAttributeRequests(List<AttributeValueEntity> attributeValueEntityList) {
        if (attributeValueEntityList == null) {
            this.productAttributeRequests = null;
        } else {
            this.productAttributeRequests = Collections.unmodifiableList(attributeValueEntityList);
        }
        this.productAttributeRequests = attributeValueEntityList;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                ", adminId=" + adminId +
                ", attributeValueEntityList=" + productAttributeRequests +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductEntity)) return false;
        ProductEntity that = (ProductEntity) o;
        return getId().equals(that.getId()) && Objects.equals(getTitle(), that.getTitle())
                && Objects.equals(getDescription(), that.getDescription())
                && getPrice().equals(that.getPrice())
                && getQuantity().equals(that.getQuantity())
                && Objects.equals(getCreatedAt(), that.getCreatedAt())
                && Objects.equals(getModifiedAt(), that.getModifiedAt())
                && getAdminId().equals(that.getAdminId())
                && Objects.equals(getProductAttributeRequests(), that.getProductAttributeRequests());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getPrice(), getQuantity(), getCreatedAt(), getModifiedAt(), getAdminId(), getProductAttributeRequests());
    }
}
