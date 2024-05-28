package server.model.order;

import server.database.order.OrderDAO;
import server.database.order.OrderDAOManager;
import shared.utils.Request;
import shared.utils.menuItem.MenuItem;
import shared.utils.order.Order;
import shared.utils.order.OrderStatus;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;

public class OrderHandlerManager implements OrderHandler, Serializable {
    private OrderDAO orderDAO;
    private final PropertyChangeSupport support;

    public OrderHandlerManager() {
        orderDAO = new OrderDAOManager();
        support = new PropertyChangeSupport(this);
    }


    @Override
    public Request createOrder(Order order) {
        orderDAO.createOrder(order);
        return null;
    }

    @Override
    public Order getOrder(int orderId) {
        return orderDAO.getOrder(orderId);
    }

    @Override
    public void addMenuToOrder(int orderId, int menuItemId) {
        orderDAO.addMenuToOrder(orderId, menuItemId);
    }

    @Override
    public void removeMenuFromOrder(int orderId, int menuItemId) {
        orderDAO.removeMenuFromOrder(orderId, menuItemId);
    }

    @Override
    public ArrayList<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    @Override
    public void deleteOrder(int orderId) {
        orderDAO.deleteOrder(orderId);
    }

    @Override
    public ArrayList<MenuItem> getOrderItems(int orderId) {
        return orderDAO.getOrderItems(orderId);
    }

    @Override
    public void updateOrderStatus(int orderId, OrderStatus orderStatus) {
        orderDAO.updateOrderStatus(orderId, orderStatus);
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }
}
