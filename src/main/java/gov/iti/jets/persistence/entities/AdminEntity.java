package gov.iti.jets.persistence.entities;

import jakarta.persistence.Entity;

@Entity(name="admins")
public class AdminEntity extends UserEntity {

    public AdminEntity() {
        super();
    }
}
