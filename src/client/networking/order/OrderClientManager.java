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
        try {
           this.server = GetServer.getServerFromRmi();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createOrder(Order order) throws RemoteException {
       try {
           server.getOrderServer().addNewOrder(order);
       } catch (RemoteException e) {
           throw new RuntimeException(e);
       }
    }

    @Override
    public void cancelOrder(int orderid) throws RemoteException {
        try {
            server.getOrderServer().deleteOrder(orderid);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order getOrder(int id) throws RemoteException {
        return server.getOrderServer().getOrder(id);
    }

    @Override
    public List<Order> getAllOrders() throws RemoteException {
        return server.getOrderServer().getAllOrders();
    }

    @Override
    public void removeItemFromOrder(int menuItemid, int orderId) throws RemoteException {
        server.getOrderServer().removeMenuFromOrder(orderId, menuItemid);
    }

    @Override
    public void addItemToOrder(int menuItemid, int orderId) throws RemoteException {
        server.getOrderServer().addMenuToOrder(orderId, menuItemid);

    }
}
