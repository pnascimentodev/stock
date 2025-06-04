package dev.pndev.stock.service;

import dev.pndev.stock.exception.DomainException;
import dev.pndev.stock.model.ProductEntity;
import dev.pndev.stock.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final  ProductRepository productRepository;

    public boolean isProductLowStock(Long id) {
        return productRepository.isProductReplenishById(id);
    }

    public List<ProductEntity> getAllLowStockProducts() {
        return productRepository.findAllByQuantityLessThan(5);
    }

    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductEntity findProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new DomainException(DomainException.ErrorType.PRODUCT_NOT_FOUND, "Product not found"));
    }

    public ProductEntity registerProduct(ProductEntity productEntity) {
        return productRepository.findByName(productEntity.getName())
                .map(existingProduct -> {
                    if (!existingProduct.getPrice().equals(productEntity.getPrice())) {
                        throw new DomainException(DomainException.ErrorType.INVALID_PRODUCT_OPERATION,
                                "Product price mismatch");
                    }
                    existingProduct.setStock(existingProduct.getStock() + productEntity.getStock());
                    return productRepository.save(existingProduct);
                })
                .orElseGet(() -> productRepository.save(productEntity));
    }

    public ProductEntity updateProduct(Long id, ProductEntity productEntity) {
        ProductEntity existingProductEntity = findProduct(id);
        existingProductEntity.setName(productEntity.getName());
        existingProductEntity.setPrice(productEntity.getPrice());
        existingProductEntity.setStock(productEntity.getStock());
        return productRepository.save(existingProductEntity);
    }

    public void deleteProduct(Long id) {
        ProductEntity productEntity = findProduct(id);
        productRepository.delete(productEntity);
    }

}
