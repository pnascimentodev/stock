package dev.pndev.stock.controller;

import dev.pndev.stock.dto.ProductRequestDTO;
import dev.pndev.stock.dto.ProductResponseDTO;
import dev.pndev.stock.model.ProductEntity;
import dev.pndev.stock.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping()
    public ResponseEntity<ProductResponseDTO> registerProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        ProductEntity productEntity = productService.registerProduct(productRequestDTO.toProduct());
        return ResponseEntity.created(null).body(
                ProductResponseDTO.from(productEntity)
        );
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
