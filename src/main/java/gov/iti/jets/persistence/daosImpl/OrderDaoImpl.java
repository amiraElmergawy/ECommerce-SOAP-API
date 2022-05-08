package gov.iti.jets.persistence.daosImpl;

import java.util.List;
import java.util.Optional;

import gov.iti.jets.persistence.daos.OrderDao;
import gov.iti.jets.persistence.entities.OrderEntity;
import gov.iti.jets.persistence.util.EntityManagerProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public enum OrderDaoImpl implements OrderDao {

    INSTANCE;

    private static final EntityManager entityManager = EntityManagerProvider.INSTANCE.getEntityManager();

    @Override
    public boolean createOrder(OrderEntity orderEntity) {
        try {
            if (!entityManager.getTransaction().isActive())
                entityManager.getTransaction().begin();
            entityManager.persist(orderEntity);
            entityManager.getTransaction().commit();
            return true;
        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean approveOrder(int id) {
        OrderEntity orderEntity = entityManager.find(OrderEntity.class, id);
        if (orderEntity != null) {
            orderEntity.setIsSubmitted(true);
            entityManager.getTransaction().begin();
            entityManager.merge(orderEntity);
            entityManager.getTransaction().commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean updateOrder(OrderEntity orderEntity) {
        if (!entityManager.getTransaction().isActive())
            entityManager.getTransaction().begin();
        try {
            entityManager.merge(orderEntity);
            entityManager.getTransaction().commit();
            return true;
        } catch (IllegalArgumentException exception) {
            return false;
        }
    }

    @Override
    public boolean deleteOrder(int id) {
        OrderEntity orderEntity = entityManager.find(OrderEntity.class, id);
        if (orderEntity != null) {
            if (!entityManager.getTransaction().isActive())
                entityManager.getTransaction().begin();
            entityManager.remove(orderEntity);
            entityManager.getTransaction().commit();
            return true;
        }
        return false;
    }

    @Override
    public Optional<OrderEntity> gOrderById(int id) {
        return Optional.of(entityManager.find(OrderEntity.class, id));
    }

    @Override
    public List<OrderEntity> getOrders() {
        return entityManager.createQuery("from OrderEntity", OrderEntity.class).getResultList();
    }

    @Override
    public List<OrderEntity> getOrdersByCustomer(int customerId) {
        String select = "from OrderEntity o where o.users.id=:id";
        Query query = entityManager.createQuery(select);
        return query.setParameter("id", customerId).getResultList();
    }

    @Override
    public boolean cancelOrder(int id) {
        OrderEntity orderEntity = entityManager.find(OrderEntity.class, id);
        if (orderEntity != null) {
            orderEntity.setIsCanceled(true);
            if (!entityManager.getTransaction().isActive())
                entityManager.getTransaction().begin();
            entityManager.merge(orderEntity);
            entityManager.getTransaction().commit();
            return true;
        }
        return false;
    }

}
