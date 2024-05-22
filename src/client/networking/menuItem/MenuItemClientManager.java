package client.networking.menuItem;

import client.networking.GetServer;
import shared.networking.serverInterfaces.Server;
import shared.utils.Request;
import shared.utils.menuItem.MenuItem;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class MenuItemClientManager implements MenuItemClient {
    private Server server;
    private PropertyChangeSupport support;

    public MenuItemClientManager() {
        try {
            UnicastRemoteObject.exportObject(this, 0);
            support = new PropertyChangeSupport(this);
            this.server = GetServer.getServerFromRmi();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public Request createMenuItem(MenuItem menuItem) throws RemoteException {
        return this.server.getMenuServer().createMenu(menuItem.getMenuItemName(), menuItem.getMenuItemType());
    }

    @Override
    public void updateMenuItem(MenuItem menuItem, String newName, String newType) {
        try {
            server.getMenuServer().updateMenu(menuItem, newName, newType);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeMenuItem(MenuItem menuItem) {
        try {
            server.getMenuServer().deleteMenu(menuItem);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MenuItem getMenuItem(int id) throws RemoteException {
        return server.getMenuServer().getMenus().get(id);
    }

    @Override
    public List<MenuItem> getMenuItems() throws RemoteException {
        return server.getMenuServer().getMenus();
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
