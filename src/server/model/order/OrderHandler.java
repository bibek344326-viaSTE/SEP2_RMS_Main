package server.model.order;

import shared.utils.Request;
import shared.utils.Subject;
import shared.utils.menuItem.MenuItem;
import shared.utils.order.Order;
import shared.utils.order.OrderStatus;

import java.util.ArrayList;

public interface OrderHandler extends Subject {
   Request createOrder(Order order);
    Order getOrder(int orderId);
    void addMenuToOrder(int orderId, int menuItem);
    void removeMenuFromOrder(int orderId, int menuItemId);
    ArrayList<Order> getAllOrders();
    void deleteOrder(int orderId);
    ArrayList<MenuItem> getOrderItems(int orderId);
    void updateOrderStatus(int orderId, OrderStatus orderStatus);
}
