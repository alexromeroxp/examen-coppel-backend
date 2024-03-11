package com.app.backend.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Policy")
public class PolicyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PolicyId")
    private Long policyId;

    @Column(name = "EmployeeId")
    public int employeeId;

    @Column(name = "SKU")
    public Long sku;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "Date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SKU", referencedColumnName = "SKU", insertable = false, updatable = false)
    private InventoryEntity inventory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EmployeeId", referencedColumnName = "EmployeeId", insertable = false, updatable = false)
    private EmployeeEntity employee;

    // Constructors
    public PolicyEntity() {
        // Default constructor
    }

    public PolicyEntity(int employeeId, Long sku, int quantity, Date date) {
        this.employeeId = employeeId;
        this.sku = sku;
        this.quantity = quantity;
        this.date = date;
    }

    // Getters and setters
    public Long getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Long policyId) {
        this.policyId = policyId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Long getSku() {
        return sku;
    }

    public void setSku(Long sku) {
        this.sku = sku;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public InventoryEntity getInventory() {
        return inventory;
    }

    public void setInventory(InventoryEntity inventory) {
        this.inventory = inventory;
    }

    public EmployeeEntity getEmployee(int employeeId) {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

    // toString(), equals(), and hashCode() methods
    // Additional methods as needed
}
