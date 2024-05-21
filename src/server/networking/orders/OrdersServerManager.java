package server.networking.orders;

import server.model.order.OrderHandler;
import shared.networking.serverInterfaces.OrderServer;
import shared.utils.Request;
import shared.utils.menuItem.MenuItem;
import shared.utils.order.Order;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class OrdersServerManager implements OrderServer {
    private ArrayList<Order> orders;
    private OrderHandler orderHandler;

    public OrdersServerManager(OrderHandler orderHandler) throws RemoteException {
        this.orders = orderHandler.getOrders();
        this.orderHandler = orderHandler;
        UnicastRemoteObject.exportObject(this, 0);
    }

    @Override
    public ArrayList<Order> getOrders() throws RemoteException {
        return orders;
    }

    @Override
    public Order getOrder(int orderId) throws RemoteException {
        return orderHandler.getOrder(orderId);
    }

    @Override
    public Request createOrder(Order order) throws RemoteException {
        return orderHandler.addOrder(order);
    }

    @Override
    public void deleteOrder(Order order) throws RemoteException {
        orderHandler.removeOrder(order);
    }


    @Override
    public void removeMenuItemFromOrder(Order order, MenuItem menuItem) throws RemoteException {
        orderHandler.removeMenuItemFromOrder(order.getOrderID(), menuItem);
    }

    @Override
    public void addMenuItemFromOrder(Order order, MenuItem menuItem) throws RemoteException {
        orderHandler.addMenuItemToOrder(order.getOrderID(), menuItem);
    }
}
