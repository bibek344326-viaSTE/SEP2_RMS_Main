package server.database.order;

import shared.utils.menuItem.MenuItem;
import shared.utils.order.Order;

import java.util.ArrayList;

public interface OrderDAO {
    void createOrder(Order order);
    Order getOrder(int orderId);
    void addMenuToOrder(int orderId, MenuItem menuItem);
    void removeMenuFromOrder(int orderId, MenuItem menuItem);
    ArrayList<Order> getOrders();
}
