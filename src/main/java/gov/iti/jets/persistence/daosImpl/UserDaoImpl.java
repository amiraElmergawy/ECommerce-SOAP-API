package gov.iti.jets.persistence.daosImpl;

import java.util.Optional;

import gov.iti.jets.persistence.daos.UserDao;
import gov.iti.jets.persistence.entities.AdminEntity;
import gov.iti.jets.persistence.entities.ClerkEntity;
import gov.iti.jets.persistence.entities.CustomerEntity;
import gov.iti.jets.persistence.entities.UserEntity;
import gov.iti.jets.persistence.util.EntityManagerProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

public enum UserDaoImpl implements UserDao {

    INSTANCE;

    private static final EntityManager entityManager = EntityManagerProvider.INSTANCE.getEntityManager();

    @Override
    public  Optional<? extends UserEntity> login(String username, String password) {
        String select = "select  u from UserEntity u where u.username=:username and u.password=:password";
        Query query = entityManager.createQuery(select);
        query.setParameter("username", username);
        query.setParameter("password", password);
        try {
            String entityType = query.getSingleResult().getClass().getName().substring(query.getSingleResult().getClass().getName().lastIndexOf(".")+1);
            System.out.println(entityType);
            if(entityType.equals("AdminEntity")){
                return Optional.of((AdminEntity) query.getSingleResult());
            }else if(entityType.equals("ClerkEntity")){
                return Optional.of((ClerkEntity) query.getSingleResult());
            }else {
                return Optional.of((CustomerEntity) query.getSingleResult());
            }
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean createUser(CustomerEntity customerEntity) {
        try {
            if (!entityManager.getTransaction().isActive())
                entityManager.getTransaction().begin();
            entityManager.persist(customerEntity);
            entityManager.getTransaction().commit();
            return true;
        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public Optional<CustomerEntity> getUserById(int id) {
        return Optional.of(entityManager.find(CustomerEntity.class, id));
    }

}
