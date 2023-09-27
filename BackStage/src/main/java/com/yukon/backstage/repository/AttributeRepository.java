package com.yukon.backstage.repository;

import com.yukon.backstage.entity.AttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRepository extends JpaRepository<AttributeEntity, Long> {
    AttributeEntity findByTitle(String title);
}
