package dev.pndev.stock.repository;

import dev.pndev.stock.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository <Product, Long>{

    boolean existsProductById(Long id);

    boolean productReplenishById(Long id);

    Product findProductById(Long id);

    List<Product> findAllByReplenishTrue();

}
