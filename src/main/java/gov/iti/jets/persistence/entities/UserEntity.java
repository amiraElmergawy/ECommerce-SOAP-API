package gov.iti.jets.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", catalog = "ecommerce", uniqueConstraints = { @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "phone"), @UniqueConstraint(columnNames = "username") })
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "userType")
public class UserEntity implements java.io.Serializable {

    private Integer id;
    private String email;
    private String password;
    private String username;
    private String phone;
    // private String userType;
    private Set<OrderEntity> orderses = new HashSet<OrderEntity>(0);

    public UserEntity() {
    }

    public UserEntity(String email, String password, String username, String phone) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.phone = phone;
    }

    public UserEntity(String email, String password, String username, String phone, Set<OrderEntity> orderses) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.phone = phone;
        // this.address = address;
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

    @Column(name = "email", unique = true, nullable = false, length = 100)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password", nullable = false, length = 100)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "username", unique = true, nullable = false, length = 100)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "phone", unique = true, nullable = false, length = 11)
    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
    public Set<OrderEntity> getOrderses() {
        return this.orderses;
    }

    public void setOrderses(Set<OrderEntity> orderses) {
        this.orderses = orderses;
    }

}
