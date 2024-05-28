package client.model.order;

import client.networking.order.OrderClient;
import shared.utils.menuItem.MenuItem;
import shared.utils.order.Order;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class OrderModelManager implements OrderModel {
    private OrderClient orderClient;
    private PropertyChangeSupport support;

    public OrderModelManager(OrderClient orderClient) {
        support = new PropertyChangeSupport(this);
        this.orderClient = orderClient;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void createOrder(Order order) throws RemoteException {
        orderClient.createOrder(order);
    }

    @Override
    public void removeOrder(int orderId) throws RemoteException {
        orderClient.cancelOrder(orderId);
    }

    @Override
    public void updateOrder(Order order) throws RemoteException {

    }

    @Override
    public Order getOrder(int id) throws RemoteException {
        return orderClient.getOrder(id);
    }

    @Override
    public ArrayList<Order> getOrders() throws RemoteException {
        return (ArrayList<Order>) orderClient.getAllOrders();
    }

    @Override
    public void addMenuItemToOrder(int orderId, MenuItem menuItem) throws RemoteException {
        orderClient.getOrder(orderId).addOrderItemsList(menuItem);
    }

    @Override
    public void removeMenuItemFromOrder(int orderId, MenuItem menuItem) throws RemoteException {
        orderClient.getOrder(orderId).removeOrderItemsList(menuItem);
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
