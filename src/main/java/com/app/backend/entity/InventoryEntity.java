package com.app.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Inventory")
public class InventoryEntity {

    @Id
    @Column(name = "SKU")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_sequence")
    @SequenceGenerator(name = "inventory_sequence", sequenceName = "inventory_sequence", allocationSize = 1)
    private Long sku;

    @Column(name = "NAME")
    private String name;

    @Column(name = "QUANTITY")
    private int quantity;

    // Constructors
    public InventoryEntity() {
        // Default constructor
    }

    public InventoryEntity(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    // Getters and setters
    public Long getSku() {
        return sku;
    }

    public void setSku(Long sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "InventoryEntity{" +
                "sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
