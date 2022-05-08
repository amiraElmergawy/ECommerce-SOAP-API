package gov.iti.jets.service.servicesImpl;

import java.util.Optional;

import gov.iti.jets.persistence.daosImpl.UserDaoImpl;
import gov.iti.jets.persistence.entities.CustomerEntity;
import gov.iti.jets.persistence.entities.UserEntity;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public class UserServiceImpl {

    @WebMethod
    public Optional<? extends UserEntity> login(String username, String password) {
        return UserDaoImpl.INSTANCE.login(username, password);
    }

    @WebMethod
    public boolean register(String username, String password, String email, String phone) {
        CustomerEntity customer = new CustomerEntity();
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setUsername(username);
        customer.setPhone(phone);
        return UserDaoImpl.INSTANCE.createUser(customer);
    }

}
