package client.networking.order;

import shared.utils.menuItem.MenuItem;
import shared.utils.order.Order;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface OrderClient extends Remote {
    void createOrder(Order order) throws RemoteException;
    void cancelOrder(Order order) throws RemoteException;
    Order getOrder(int id) throws RemoteException;
    List<Order> getAllOrders() throws RemoteException;
    void removeItemFromOrder(MenuItem menuItem, Order order) throws RemoteException ;
    void addItemToOrder(MenuItem menuItem, Order order) throws RemoteException;
}
