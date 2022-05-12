package gov.iti.jets.service.servicesImpl;

import java.util.Optional;

import gov.iti.jets.persistence.daosImpl.UserDaoImpl;
import gov.iti.jets.persistence.entities.CustomerEntity;
import gov.iti.jets.persistence.entities.UserEntity;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService
public class UserServiceImpl {

    @WebMethod
    public UserEntity login(@WebParam(name = "username")String username,@WebParam(name = "password") String password) {
        Optional<? extends UserEntity> userEntity =  UserDaoImpl.INSTANCE.login(username, password);
        if(userEntity.isPresent())
            return userEntity.get();
        throw new RuntimeException("Wrong credintals");
    }

    @WebMethod
    public boolean register(@WebParam(name = "username")String username,@WebParam(name = "password") String password,@WebParam(name = "email") String email,
    @WebParam(name = "phone") String phone) {
        CustomerEntity customer = new CustomerEntity();
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setUsername(username);
        customer.setPhone(phone);
        return UserDaoImpl.INSTANCE.createUser(customer);
    }

}
