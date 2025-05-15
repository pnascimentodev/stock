package dev.pndev.stock.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;
    private int quantity;

    public int getStock() {
        return quantity;
    }

    public boolean isReplenish() {
        return quantity < 5;
    }

    public void setStock(int stock) {
        this.quantity = stock;
    }
}
