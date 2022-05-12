package gov.iti.jets;

// import gov.iti.jets.persistence.ProductDao;
import gov.iti.jets.persistence.daosImpl.OrderDaoImpl;
import gov.iti.jets.persistence.daosImpl.ProductDaoImpl;
import gov.iti.jets.persistence.daosImpl.UserDaoImpl;
import gov.iti.jets.persistence.entities.AdminEntity;
import gov.iti.jets.persistence.entities.Category;
import gov.iti.jets.persistence.entities.CustomerEntity;
import gov.iti.jets.persistence.entities.OrderEntity;
import gov.iti.jets.persistence.entities.ProductEntity;
import gov.iti.jets.persistence.entities.UserEntity;
// import gov.iti.jets.service.dtos.product.Product;
import gov.iti.jets.service.servicesImpl.ProductServiceImpl;
// import gov.iti.jets.persistence.entities.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
        Persistence.createEntityManagerFactory("aae");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        AdminEntity user = new AdminEntity();
        user.setEmail("email2");
        // user.setAddress("address");
        user.setPassword("password");
        user.setPhone("phone2");
        user.setUsername("username2");
        // entityManager.persist(user);
        ProductEntity product = new ProductEntity();
        product.setName("name");
        // product.setQuantity(5);
        product.setAmount(400);
        product.setCategory(Category.BATHROOM);
        entityManager.merge(product);
        System.out.println(entityManager.find(ProductEntity.class, 1));
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();

        System.out.println(ProductDaoImpl.INSTANCE.getProductById(3).get());

        System.out.println(ProductDaoImpl.INSTANCE.createProduct(new
        ProductEntity("product", 600, Category.KITCHEN)));

    //     CustomerEntity user = UserDaoImpl.INSTANCE.getUserById(1).get();
    //     System.out.println(OrderDaoImpl.INSTANCE.createOrder(new OrderEntity(user,
    //     5000)));

    //     System.out.println(OrderDaoImpl.INSTANCE.cancelOrder(1));

    // System.out.println(ProductServiceImpl.INSTANCE.createProduct("product name", 5, 3, 1));
    }
}
