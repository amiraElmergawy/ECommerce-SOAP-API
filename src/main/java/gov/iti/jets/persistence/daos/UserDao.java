package gov.iti.jets.persistence.daos;

import java.util.Optional;

import gov.iti.jets.persistence.entities.CustomerEntity;
import gov.iti.jets.persistence.entities.UserEntity;

public interface UserDao {

    public  Optional<? extends UserEntity> login(String username, String password);
    public boolean createUser(CustomerEntity customerEntity);
    public Optional<CustomerEntity> getUserById(int id);

}
