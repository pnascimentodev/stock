package dev.pndev.stock.controller;

import dev.pndev.stock.dto.ProductRequestDTO;
import dev.pndev.stock.dto.ProductResponseDTO;
import dev.pndev.stock.model.ProductEntity;
import dev.pndev.stock.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping()
    public ResponseEntity<ProductResponseDTO> registerProduct(@RequestBody @Valid ProductRequestDTO productRequestDTO) {
        ProductEntity productEntity = productService.registerProduct(productRequestDTO.toProduct());
        return ResponseEntity.created(null).body(
                ProductResponseDTO.from(productEntity)
        );
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductEntity> productEntities = productService.getAllProducts();
        List<ProductResponseDTO> response = productEntities.stream()
                .map(ProductResponseDTO::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequestDTO productRequestDTO) {
        ProductEntity productEntity = productService.updateProduct(id, productRequestDTO.toProduct());
        return ResponseEntity.ok(ProductResponseDTO.from(productEntity));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findProduct(@PathVariable Long id) {
        ProductEntity productEntity = productService.findProduct(id);
        return ResponseEntity.ok(ProductResponseDTO.from(productEntity));
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<ProductResponseDTO>> getLowStockProducts() {
        List<ProductEntity> lowStockProducts = productService.getAllLowStockProducts();
        List<ProductResponseDTO> response = lowStockProducts.stream()
                .map(ProductResponseDTO::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/replenish/{id}")
    public ResponseEntity<Boolean> isProductReplenish(@PathVariable Long id) {
        boolean isReplenish = productService.isProductLowStock(id);
        return ResponseEntity.ok(isReplenish);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
