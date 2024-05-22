package server.model.order;

import shared.utils.Request;
import shared.utils.menuItem.MenuItem;
import shared.utils.order.Order;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class OrderHandlerManager implements OrderHandler {
    private final List<Order> orders;
    private final PropertyChangeSupport support;

    public OrderHandlerManager() {
        orders = new ArrayList<>();
        support = new PropertyChangeSupport(this);
    }

    @Override
    public Request addOrder(Order order) {
        orders.add(order);
        support.firePropertyChange("orderAdded", null, order);
        return null;
    }

    @Override
    public void removeOrder(Order order) {
        if (orders.remove(order)) {
            support.firePropertyChange("orderRemoved", order, null);
        }
    }

    @Override
    public void updateOrder(Order order) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderID() == order.getOrderID()) {
                Order oldOrder = orders.set(i, order);
                support.firePropertyChange("orderUpdated", oldOrder, order);
                return;
            }
        }
    }

    @Override
    public Order getOrder(int id) {
        for (Order order : orders) {
            if (order.getOrderID() == id) {
                return order;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Order> getOrders() {
        return (ArrayList<Order>) orders;
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
