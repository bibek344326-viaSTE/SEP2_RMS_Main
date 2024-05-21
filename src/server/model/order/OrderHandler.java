package server.model.order;

import shared.utils.Request;
import shared.utils.Subject;
import shared.utils.menuItem.MenuItem;
import shared.utils.order.Order;

import java.util.ArrayList;

public interface OrderHandler extends Subject {
    Request addOrder(Order order);
    void removeOrder(Order order);
    void updateOrder(Order order);
    Order getOrder(int id);
    ArrayList<Order> getOrders();
    void addMenuItemToOrder(int orderId, MenuItem menuItem);
    void removeMenuItemFromOrder(int orderId, MenuItem menuItem);
}
