package shared.networking.serverInterfaces;

import shared.utils.Request;
import shared.utils.menuItem.MenuItem;
import shared.utils.order.Order;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface OrderServer extends Remote {
    ArrayList<Order> getOrders() throws RemoteException;
    Order getOrder(int orderId) throws RemoteException;
    Request createOrder(Order order) throws RemoteException;
    void deleteOrder(Order order) throws RemoteException;
    void removeMenuItemFromOrder(Order order, MenuItem menuItem) throws RemoteException;
    void addMenuItemFromOrder(Order order, MenuItem menuItem) throws RemoteException;

}
