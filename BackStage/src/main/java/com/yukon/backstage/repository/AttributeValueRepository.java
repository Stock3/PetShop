package com.yukon.backstage.repository;

import com.yukon.backstage.entity.AttributeValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttributeValueRepository extends JpaRepository<AttributeValueEntity, Long> {

    List<AttributeValueEntity> findByAttribute_Id(Long id);
    @Query(value = "SELECT * from attribute_values p where p.value like :value and p.attribute=:attributeId",nativeQuery = true)
    AttributeValueEntity findByValueAndAttribute(String value, Long attributeId);


}

