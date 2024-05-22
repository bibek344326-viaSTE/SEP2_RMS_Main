package client.model.order;

import shared.utils.Subject;
import shared.utils.menuItem.MenuItem;
import shared.utils.order.Order;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface OrderModel extends Subject, Remote {
    void createOrder(Order order) throws RemoteException;
    void removeOrder(Order order) throws RemoteException;
    void updateOrder(Order order) throws RemoteException;
    Order getOrder(int id) throws RemoteException;
    ArrayList<Order> getOrders() throws RemoteException;
    void addMenuItemToOrder(int orderId, MenuItem menuItem) throws RemoteException;
    void removeMenuItemFromOrder(int orderId, MenuItem menuItem) throws RemoteException;
 }
