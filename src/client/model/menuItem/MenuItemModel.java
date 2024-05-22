package client.model.menuItem;

import shared.utils.Request;
import shared.utils.Subject;
import shared.utils.menuItem.MenuItem;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface MenuItemModel extends Subject {
    ArrayList<MenuItem> getMenuItems() throws RemoteException;
    MenuItem getMenuItem(int id) throws RemoteException;
    Request createMenuItem(MenuItem menuItem) throws RemoteException;
    void removeMenuItem(MenuItem menuItem);
    void updateMenuItem(MenuItem menuItem, String newName, String newType);

}
