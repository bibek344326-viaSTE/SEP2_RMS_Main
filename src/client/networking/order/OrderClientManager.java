package client.networking.order;

import client.networking.GetServer;
import shared.networking.serverInterfaces.Server;
import shared.utils.menuItem.MenuItem;
import shared.utils.order.Order;

import java.rmi.RemoteException;
import java.util.List;

public class OrderClientManager implements OrderClient {
    private Server server;

    public OrderClientManager() {
        server = GetServer.getServerFromRmi();
    }

    @Override
    public void createOrder(Order order) throws RemoteException {
        server.getOrderServer().createOrder(order);
    }

    @Override
    public void cancelOrder(Order order) throws RemoteException {
        server.getOrderServer().deleteOrder(order);
    }

    @Override
    public Order getOrder(int id) throws RemoteException {
        return server.getOrderServer().getOrder(id);
    }

    @Override
    public List<Order> getAllOrders() throws RemoteException {
        return server.getOrderServer().getOrders();
    }

    @Override
    public void removeItemFromOrder(MenuItem menuItem, Order order) throws RemoteException {
        server.getOrderServer().addMenuItemFromOrder(order, menuItem);
    }

    @Override
    public void addItemToOrder(MenuItem menuItem, Order order) throws RemoteException {
        server.getOrderServer().addMenuItemFromOrder(order, menuItem);

    }
}
