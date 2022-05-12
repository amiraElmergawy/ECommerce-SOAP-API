package gov.iti.jets.service.servicesImpl;

import java.util.List;
import java.util.Optional;

import gov.iti.jets.persistence.daosImpl.ClerkDaoImpl;
import gov.iti.jets.persistence.entities.ClerkEntity;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService
public class ClerkServiceImpl {

    @WebMethod
    public boolean createClerk(@WebParam(name = "usernmae") String username, @WebParam(name = "password") String password, @WebParam(name = "email") String email,
    @WebParam(name = "phone") String phone) {
        ClerkEntity clerk = new ClerkEntity();
        clerk.setEmail(email);
        clerk.setPassword(password);
        clerk.setUsername(username);
        clerk.setPhone(phone);
        return ClerkDaoImpl.INSTANCE.createClerk(clerk);
    }

    @WebMethod
    public boolean updateClerk(@WebParam(name = "username") String username,@WebParam(name = "password") String password,@WebParam(name = "email") String email,
    @WebParam(name = "phone") String phone,@WebParam(name = "id") int id) {
        ClerkEntity clerk = new ClerkEntity();
        clerk.setEmail(email);
        clerk.setPassword(password);
        clerk.setUsername(username);
        clerk.setPhone(phone);
        clerk.setId(id);
        return ClerkDaoImpl.INSTANCE.updateClerk(clerk);
    }

    @WebMethod
    public boolean deleteClerk(@WebParam(name = "id")int id) {
        return ClerkDaoImpl.INSTANCE.deleteClerk(id);
    }

    @WebMethod
    public ClerkEntity getClerkById(@WebParam(name = "id")int id) {
        Optional<ClerkEntity> clerk = ClerkDaoImpl.INSTANCE.getClerkById(id);
        if(clerk.isPresent())
            return clerk.get();
        else
            throw new RuntimeException("Clerk is not found");
    }

    @WebMethod
    public List<ClerkEntity> getClerks() {
        return ClerkDaoImpl.INSTANCE.getClerks();
    }

}
