package gov.iti.jets.service.servicesImpl;

import java.util.List;
import java.util.Optional;

import gov.iti.jets.persistence.daosImpl.OrderDaoImpl;
import gov.iti.jets.persistence.daosImpl.ProductDaoImpl;
import gov.iti.jets.persistence.daosImpl.UserDaoImpl;
import gov.iti.jets.persistence.entities.CustomerEntity;
import gov.iti.jets.persistence.entities.OrderEntity;
import gov.iti.jets.persistence.entities.ProductEntity;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public class OrderServiceImpl {

    @WebMethod
    public boolean createOrder(int customerId, double totalPrice, int[] products) {
        CustomerEntity customer = UserDaoImpl.INSTANCE.getUserById(customerId).get();
        OrderEntity order = new OrderEntity(customer, totalPrice);
        for (int productId : products) {
            ProductEntity product = ProductDaoImpl.INSTANCE.getProductById(productId).get();
            order.getProductses().add(product);
        }
        return OrderDaoImpl.INSTANCE.createOrder(order);

    }

    @WebMethod
    public boolean approveOrder(int id) {
        return OrderDaoImpl.INSTANCE.approveOrder(id);
    }

    @WebMethod
    public boolean updateOrder(int customerId, double totalPrice, int[] products, int orderId) {
        return false;
    }

    @WebMethod
    public boolean deleteOrder(int id) {
        return false;
    }

    @WebMethod
    public Optional<OrderEntity> gOrderById(int id) {
        return OrderDaoImpl.INSTANCE.gOrderById(id);
    }

    @WebMethod
    public List<OrderEntity> getOrders() {
        return OrderDaoImpl.INSTANCE.getOrders();
    }

    @WebMethod
    public List<OrderEntity> getOrdersByCustomer(int customerId) {
        return OrderDaoImpl.INSTANCE.getOrdersByCustomer(customerId);
    }

    @WebMethod
    public boolean cancelOrder(int id) {
        return OrderDaoImpl.INSTANCE.cancelOrder(id);
    }

}
