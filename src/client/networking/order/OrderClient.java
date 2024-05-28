package client.networking.order;

import shared.utils.menuItem.MenuItem;
import shared.utils.order.Order;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface OrderClient{

    void createOrder(Order order) throws RemoteException;
    void cancelOrder(int orderId) throws RemoteException;
    Order getOrder(int id) throws RemoteException;
    List<Order> getAllOrders() throws RemoteException;
    void removeItemFromOrder(int menuItemid, int orderId) throws RemoteException;
    void addItemToOrder(int menuItemid, int orderId) throws RemoteException;}
