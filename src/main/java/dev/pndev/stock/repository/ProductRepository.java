package dev.pndev.stock.repository;

import dev.pndev.stock.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository <ProductEntity, Long>{


    List<ProductEntity> findAllByQuantityLessThan(int threshold);

    @Query("SELECT CASE WHEN p.quantity < 5 THEN true ELSE false END FROM ProductEntity p WHERE p.id = :id")
    boolean isProductReplenishById(@Param("id") Long id);

}
