package gov.iti.jets.service.servicesImpl;

import java.util.List;
import java.util.Optional;

import gov.iti.jets.persistence.daosImpl.ClerkDaoImpl;
import gov.iti.jets.persistence.entities.ClerkEntity;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public class ClerkServiceImpl {

    @WebMethod
    public boolean createClerk(String username, String password, String email, String phone) {
        ClerkEntity clerk = new ClerkEntity();
        clerk.setEmail(email);
        clerk.setPassword(password);
        clerk.setUsername(username);
        clerk.setPhone(phone);
        return ClerkDaoImpl.INSTANCE.createClerk(clerk);
    }

    @WebMethod
    public boolean updateClerk(String username, String password, String email, String phone, int id) {
        ClerkEntity clerk = new ClerkEntity();
        clerk.setEmail(email);
        clerk.setPassword(password);
        clerk.setUsername(username);
        clerk.setPhone(phone);
        clerk.setId(id);
        return ClerkDaoImpl.INSTANCE.updateClerk(clerk);
    }

    @WebMethod
    public boolean deleteClerk(int id) {
        return ClerkDaoImpl.INSTANCE.deleteClerk(id);
    }

    @WebMethod
    public Optional<ClerkEntity> getClerkById(int id) {
        return ClerkDaoImpl.INSTANCE.getClerkById(id);
    }

    @WebMethod
    public List<ClerkEntity> getClerks() {
        return ClerkDaoImpl.INSTANCE.getClerks();
    }

}
