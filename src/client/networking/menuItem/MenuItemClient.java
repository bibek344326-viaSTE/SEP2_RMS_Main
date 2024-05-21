package client.networking.menuItem;

import shared.utils.menuItem.MenuItem;

import java.rmi.RemoteException;
import java.util.List;

public interface MenuItemClient {
    void updateMenuItem(MenuItem menuItem, String newName, String newType);
    void removeMenuItem(MenuItem menuItem);
    MenuItem getMenuItem(int id) throws RemoteException;
    List<MenuItem> getMenuItems() throws RemoteException;
}
