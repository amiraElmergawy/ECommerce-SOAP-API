package gov.iti.jets.persistence.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public enum EntityManagerProvider {
    INSTANCE;
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("aae");

    public EntityManager getEntityManager(){
        return entityManagerFactory.createEntityManager();
    }
}
