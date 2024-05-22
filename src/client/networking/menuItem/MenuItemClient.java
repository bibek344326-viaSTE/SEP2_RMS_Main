package client.networking.menuItem;

import shared.utils.Request;
import shared.utils.Subject;
import shared.utils.menuItem.MenuItem;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MenuItemClient extends Subject, Remote {
    Request createMenuItem(MenuItem menuItem) throws RemoteException;
    void updateMenuItem(MenuItem menuItem, String newName, String newType);
    void removeMenuItem(MenuItem menuItem);
    MenuItem getMenuItem(int id) throws RemoteException;
    List<MenuItem> getMenuItems() throws RemoteException;
}
