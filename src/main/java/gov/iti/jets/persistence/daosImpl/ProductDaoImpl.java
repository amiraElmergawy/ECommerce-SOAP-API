package gov.iti.jets.persistence.daosImpl;

import java.util.List;
import java.util.Optional;

import gov.iti.jets.persistence.daos.ProductDao;
import gov.iti.jets.persistence.entities.Category;
import gov.iti.jets.persistence.entities.ProductEntity;
import gov.iti.jets.persistence.util.EntityManagerProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public enum ProductDaoImpl implements ProductDao {

    INSTANCE;

    private static final EntityManager entityManager = EntityManagerProvider.INSTANCE.getEntityManager();

    @Override
    public Optional<ProductEntity> getProductById(int id) {
        return Optional.of(entityManager.find(ProductEntity.class, id));
    }

    @Override
    public List<ProductEntity> getProducts() {
        return entityManager.createQuery("from ProductEntity", ProductEntity.class).getResultList();
    }

    @Override
    public boolean updateProduct(ProductEntity productEntity) {
        //check firstly for product existence return false if it dosn't exist
        try {
            if (!entityManager.getTransaction().isActive())
                entityManager.getTransaction().begin();
            System.out.println(productEntity);
            entityManager.merge(productEntity);
            entityManager.getTransaction().commit();
            return true;
        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean createProduct(ProductEntity productEntity) {
        try {
            if (!entityManager.getTransaction().isActive())
                entityManager.getTransaction().begin();
            System.out.println(productEntity);
            entityManager.persist(productEntity);
            entityManager.getTransaction().commit();
            // entityManager.getTransaction();
            return true;
        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public List<ProductEntity> search(String productName) {
        Query query = entityManager.createQuery("from ProductEntity where productName like :value", ProductEntity.class)
                .setParameter("value", "%" + productName + "%");
        return query.getResultList();
    }

    @Override
    public boolean deleteProduct(int id) {
        ProductEntity productEntity = entityManager.find(ProductEntity.class, id);
        if (productEntity != null) {
            if (!entityManager.getTransaction().isActive())
                entityManager.getTransaction().begin();
            System.out.println(productEntity);
            entityManager.remove(productEntity);
            entityManager.getTransaction().commit();
            return true;
        }
        return false;
    }

    @Override
    public List<ProductEntity> getProductsByCategory(Category category) {
        String select = "from ProductEntity p where p.category=:category";
        Query query = entityManager.createQuery(select);
        System.out.println(category.name());
        System.out.println(query.toString());
        return query.setParameter("category", category).getResultList();
    }

    @Override
    public List<ProductEntity> getProductsPage(int pageNo) {
        Query query = entityManager.createQuery("from ProductEntity", ProductEntity.class);
        query.setFirstResult((pageNo - 1) * 10);
        query.setMaxResults(10);
        return query.getResultList();
    }

}
