package gov.iti.jets.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.util.*;

@Entity
@Table(name = "products", catalog = "ecommerce", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class ProductEntity implements java.io.Serializable {

    private Integer id;
    private String name;
    private Integer quantity = 0;
    private double amount;
    private Category category;
    private Set<OrderEntity> orderses = new HashSet<OrderEntity>(0);

    public ProductEntity() {
    }

    public ProductEntity(String name, double amount, Category category) {
        this.name = name;
        this.amount = amount;
        this.category = category;
    }

    public ProductEntity(String name, Integer quantity, double amount, Category category, Set<OrderEntity> orderses) {
        this.name = name;
        this.quantity = quantity;
        this.amount = amount;
        this.category = category;
        this.orderses = orderses;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name", unique = true, nullable = false, length = 100)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "quantity")
    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Column(name = "amount", nullable = false)
    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Column(name = "category",nullable = false)
    @Enumerated(EnumType.STRING)
    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @XmlTransient
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "orders_has_products", catalog = "ecommerce", joinColumns = {
            @JoinColumn(name = "products_id", nullable = false, updatable = false) }, inverseJoinColumns = {
                    @JoinColumn(name = "orders_id", nullable = false, updatable = false) })
    public Set<OrderEntity> getOrderses() {
        return this.orderses;
    }

    public void setOrderses(Set<OrderEntity> orderses) {
        this.orderses = orderses;
    }

    @Override
    public String toString() {
        return "Products [amount=" + amount + ", category=" + category + ", id=" + id + ", name=" + name + ", orderses="
                + orderses + ", quantity=" + quantity + "]";
    }

}
