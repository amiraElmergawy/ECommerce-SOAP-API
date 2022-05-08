package gov.iti.jets.persistence.daos;

import java.util.List;
import java.util.Optional;

import gov.iti.jets.persistence.entities.ClerkEntity;

public interface ClerkDao {

    public boolean createClerk(ClerkEntity clerkEntity);
    public boolean updateClerk(ClerkEntity clerkEntity);
    public boolean deleteClerk(int id);
    public Optional<ClerkEntity> getClerkById(int id);
    public List<ClerkEntity> getClerks();

}
