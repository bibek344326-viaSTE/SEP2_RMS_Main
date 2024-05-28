package shared.networking.serverInterfaces;

import shared.utils.Request;
import shared.utils.menuItem.MenuItem;
import shared.utils.order.Order;
import shared.utils.order.OrderStatus;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface OrderServer extends Remote {
   Request addNewOrder(Order order) throws RemoteException;
    Order getOrder(int orderId) throws RemoteException;
    void addMenuToOrder(int orderId, int menuItemId) throws RemoteException;
    void removeMenuFromOrder(int orderId, int menuItemId) throws RemoteException;
    ArrayList<Order> getAllOrders() throws RemoteException;
    void deleteOrder(int orderId) throws RemoteException;
    ArrayList<MenuItem> getOrderItems(int orderId) throws RemoteException;
    void updateOrderStatus(int orderId, OrderStatus orderStatus) throws RemoteException;

}
