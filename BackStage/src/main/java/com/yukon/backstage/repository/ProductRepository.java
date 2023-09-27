package com.yukon.backstage.repository;

import com.yukon.backstage.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findAllByTitle(String title);

    List<ProductEntity> findAllByPrice(BigDecimal price);

    /*Magic method. Only team understand :D*/
    @Query(value = "SELECT * FROM products WHERE (:fieldName) = :value", nativeQuery = true)
    List<ProductEntity> findProductEntityBy(@Param("fieldName") String fieldName, @Param("value") String value);


}
