package gov.iti.jets.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name = "customers")
public class CustomerEntity extends UserEntity {
    private String address;

    @Column(name = "address", length = 100)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customers [address=" + address + "]";
    }

}
