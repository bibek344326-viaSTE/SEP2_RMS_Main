package server.model.order;

import server.database.order.OrderDAO;
import server.database.order.OrderDAOManager;
import shared.utils.Request;
import shared.utils.menuItem.MenuItem;
import shared.utils.order.Order;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class OrderHandlerManager implements OrderHandler {
    private OrderDAO orderDAO;
    private final PropertyChangeSupport support;

    public OrderHandlerManager() {
        orderDAO = new OrderDAOManager();
        support = new PropertyChangeSupport(this);
    }

    @Override
    public Request addOrder(Order order) {
        orderDAO.addMenuToOrder(order.getOrderID(), order.getOrderItemsList(order.getOrderID()));
        support.firePropertyChange("orderAdded", null, order);
        return null;
    }

    @Override
    public void removeOrder(Order order) {
        //
    }

    @Override
    public void updateOrder(Order order) {
       //
    }

    @Override
    public Order getOrder(int id) {
        for (Order order : orderDAO.getOrders()) {
            if (order.getOrderID() == id) {
                return order;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Order> getOrders() {
        return orderDAO.getOrders();
    }

    @Override
    public void addMenuItemToOrder(int orderId, MenuItem menuItem) {
        Order order = getOrder(orderId);
        if (order != null) {
            order.addOrderItemsList(menuItem);
            support.firePropertyChange("menuItemAddedToOrder", null, order);
        }
    }

    @Override
    public void removeMenuItemFromOrder(int orderId, MenuItem menuItem) {
        Order order = getOrder(orderId);
        if (order != null) {
            order.removeOrderItemsList(menuItem);
            support.firePropertyChange("menuItemRemovedFromOrder", null, order);
        }
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
