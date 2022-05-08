package gov.iti.jets.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders", catalog = "ecommerce")
public class OrderEntity implements java.io.Serializable {

    private Integer id;
    private UserEntity users;
    private double totalPrice;
    private Boolean isCanceled = false;
    private Boolean isSubmitted = false;
    private Set<ProductEntity> productses = new HashSet<ProductEntity>(0);

    public OrderEntity() {
    }

    public OrderEntity(UserEntity users, double totalPrice) {
        this.users = users;
        this.totalPrice = totalPrice;
    }

    public OrderEntity(UserEntity users, double totalPrice, Set<ProductEntity> productses) {
        this.users = users;
        this.totalPrice = totalPrice;
        this.productses = productses;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", nullable = false)
    public UserEntity getUsers() {
        return this.users;
    }

    public void setUsers(UserEntity users) {
        this.users = users;
    }

    @Column(name = "totalPrice", nullable = false, precision = 22, scale = 0)
    public double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Column(name = "isSubmitted")
    public boolean getIsSubmitted() {
        return this.isSubmitted;
    }

    public void setIsSubmitted(Boolean isSubmitted) {
        this.isSubmitted = isSubmitted;
    }

    @Column(name = "isCanceled")
    public boolean getIsCanceled() {
        return this.isCanceled;
    }

    public void setIsCanceled(Boolean isCanceled) {
        this.isCanceled = isCanceled;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "orders_has_products", catalog = "ecommerce", joinColumns = {
            @JoinColumn(name = "orders_id", nullable = false, updatable = false) }, inverseJoinColumns = {
                    @JoinColumn(name = "products_id", nullable = false, updatable = false) })
    public Set<ProductEntity> getProductses() {
        return this.productses;
    }

    public void setProductses(Set<ProductEntity> productses) {
        this.productses = productses;
    }

}
