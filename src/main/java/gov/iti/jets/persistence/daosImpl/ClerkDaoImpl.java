package gov.iti.jets.persistence.daosImpl;

import java.util.List;
import java.util.Optional;

import gov.iti.jets.persistence.daos.ClerkDao;
import gov.iti.jets.persistence.entities.ClerkEntity;
import gov.iti.jets.persistence.util.EntityManagerProvider;
import jakarta.persistence.EntityManager;

public enum ClerkDaoImpl implements ClerkDao {

    INSTANCE;

    private static final EntityManager entityManager = EntityManagerProvider.INSTANCE.getEntityManager();

    @Override
    public boolean createClerk(ClerkEntity clerkEntity) {
        try {
            if (!entityManager.getTransaction().isActive())
                entityManager.getTransaction().begin();
            entityManager.persist(clerkEntity);
            entityManager.getTransaction().commit();
            return true;
        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateClerk(ClerkEntity clerkEntity) {
        if (!entityManager.getTransaction().isActive())
            entityManager.getTransaction().begin();
        try {
            entityManager.merge(clerkEntity);
            entityManager.getTransaction().commit();
            return true;
        } catch (IllegalArgumentException exception) {
            return false;
        }
    }

    @Override
    public boolean deleteClerk(int id) {
        ClerkEntity clerkEntity = entityManager.find(ClerkEntity.class, id);
        if (clerkEntity != null) {
            if (!entityManager.getTransaction().isActive())
                entityManager.getTransaction().begin();
            entityManager.remove(clerkEntity);
            entityManager.getTransaction().commit();
            return true;
        }
        return false;
    }

    @Override
    public Optional<ClerkEntity> getClerkById(int id) {
        return Optional.of(entityManager.find(ClerkEntity.class, id));
    }

    @Override
    public List<ClerkEntity> getClerks() {
        return entityManager.createQuery("SELECT * FROM ecommerce.users where userType='clerks'", ClerkEntity.class).getResultList();
    }

}
