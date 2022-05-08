package gov.iti.jets.persistence.daos;

import java.util.List;
import java.util.Optional;

import gov.iti.jets.persistence.entities.OrderEntity;

public interface OrderDao {

    public boolean createOrder(OrderEntity orderEntity);
    public boolean approveOrder(int id);
    public boolean updateOrder(OrderEntity orderEntity);
    public boolean deleteOrder(int id);
    public Optional<OrderEntity> gOrderById(int id);
    public List<OrderEntity> getOrders();
    public List<OrderEntity> getOrdersByCustomer(int customerId);
    public boolean cancelOrder(int id);

}
