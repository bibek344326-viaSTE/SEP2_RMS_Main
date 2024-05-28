package server.networking.orders;

import server.model.order.OrderHandler;
import shared.networking.serverInterfaces.OrderServer;
import shared.utils.Request;
import shared.utils.menuItem.MenuItem;
import shared.utils.order.Order;
import shared.utils.order.OrderStatus;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class OrdersServerManager implements OrderServer {

    private ArrayList<Order> orders;
    private OrderHandler orderHandler;

    public OrdersServerManager(OrderHandler orderHandler) throws RemoteException {
        this.orderHandler = orderHandler;
        UnicastRemoteObject.exportObject(this, 0);
    }

    @Override
    public Request addNewOrder(Order order) throws RemoteException {
        return orderHandler.createOrder(order);
    }

    @Override
    public Order getOrder(int orderId) throws RemoteException {
        return orderHandler.getOrder(orderId);
    }

    @Override
    public void addMenuToOrder(int orderId, int menuItemId) throws RemoteException {
        orderHandler.addMenuToOrder(orderId, menuItemId);
    }

    @Override
    public void removeMenuFromOrder(int orderId, int menuItemId) throws RemoteException {
        orderHandler.removeMenuFromOrder(orderId, menuItemId);
    }

    @Override
    public ArrayList<Order> getAllOrders() throws RemoteException {
        return orderHandler.getAllOrders();
    }

    @Override
    public void deleteOrder(int orderId) throws RemoteException {
        orderHandler.deleteOrder(orderId);
    }

    @Override
    public ArrayList<MenuItem> getOrderItems(int orderId) throws RemoteException {
        return orderHandler.getOrderItems(orderId);
    }

    @Override
    public void updateOrderStatus(int orderId, OrderStatus orderStatus) throws RemoteException {
        orderHandler.updateOrderStatus(orderId, orderStatus);
    }


}
