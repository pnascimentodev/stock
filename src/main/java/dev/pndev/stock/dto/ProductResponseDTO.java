package dev.pndev.stock.dto;

import dev.pndev.stock.model.ProductEntity;

public record ProductResponseDTO(
        Long id,
        String name,
        Double price,
        int quantity,
        boolean replenish
) {

    public static ProductResponseDTO from(ProductEntity productEntity) {
        return new ProductResponseDTO(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getPrice(),
                productEntity.getStock(),
                productEntity.isReplenish()
        );
    }

    public static ProductResponseDTO from(ProductRequestDTO requestDTO) {
        int quantity = requestDTO.getQuantity();
        boolean replenish = quantity < 5;

        return new ProductResponseDTO(
                null,
                requestDTO.getName(),
                requestDTO.getPrice(),
                quantity,
                replenish
        );
    }
}
