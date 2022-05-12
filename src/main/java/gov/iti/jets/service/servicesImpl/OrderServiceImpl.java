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
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService
public class OrderServiceImpl {

    @WebMethod
    public boolean createOrder(@WebParam(name = "customerId")int customerId,@WebParam(name = "totalPrice") double totalPrice,
    @WebParam(name = "products") int[] products) {
        CustomerEntity customer = UserDaoImpl.INSTANCE.getUserById(customerId).get();
        OrderEntity order = new OrderEntity(customer, totalPrice);
        for (int productId : products) {
            ProductEntity product = ProductDaoImpl.INSTANCE.getProductById(productId).get();
            order.getProductses().add(product);
        }
        return OrderDaoImpl.INSTANCE.createOrder(order);

    }

    @WebMethod
    public boolean approveOrder(@WebParam(name = "id")int id) {
        return OrderDaoImpl.INSTANCE.approveOrder(id);
    }

    @WebMethod
    public boolean updateOrder(@WebParam(name = "customerId")int customerId,@WebParam(name = "totalPrice") double totalPrice,
    @WebParam(name = "products") int[] products,@WebParam(name = "orderId") int orderId) {
        CustomerEntity customer = UserDaoImpl.INSTANCE.getUserById(customerId).get();
        if(customer == null)
            return false;
        OrderEntity order = new OrderEntity(customer, totalPrice);
        for (int productId : products) {
            ProductEntity product = ProductDaoImpl.INSTANCE.getProductById(productId).get();
            order.getProductses().add(product);
        }
        order.setId(orderId);
        return OrderDaoImpl.INSTANCE.updateOrder(order);
    }

    @WebMethod
    public boolean deleteOrder(@WebParam(name = "id")int id) {
        return OrderDaoImpl.INSTANCE.deleteOrder(id);
    }

    @WebMethod
    public OrderEntity gOrderById(@WebParam(name = "id")int id) {
        Optional<OrderEntity> order = OrderDaoImpl.INSTANCE.gOrderById(id);
        System.out.println(order);
        if(order.isPresent())
            return order.get();
        throw new RuntimeException("order is not found");
    }

    @WebMethod
    public List<OrderEntity> getOrders() {
        return OrderDaoImpl.INSTANCE.getOrders();
    }

    @WebMethod
    public List<OrderEntity> getOrdersByCustomer(@WebParam(name = "customerId")int customerId) {
        return OrderDaoImpl.INSTANCE.getOrdersByCustomer(customerId);
    }

    @WebMethod
    public boolean cancelOrder(@WebParam(name = "id")int id) {
        return OrderDaoImpl.INSTANCE.cancelOrder(id);
    }

}
