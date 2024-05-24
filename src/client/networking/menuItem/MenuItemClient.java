package client.networking.menuItem;

import shared.utils.Request;
import shared.utils.Subject;
import shared.utils.menuItem.MenuItem;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface MenuItemClient {
    void createMenuItem(MenuItem menuItem) throws RemoteException;
    void updateMenuItem(MenuItem menuItem, String newName, String newType);
    void removeMenuItem(int menuItemID);
    MenuItem getMenuItem(int id) throws RemoteException, SQLException;
    ArrayList<MenuItem> getMenuItems() throws RemoteException, SQLException;
}
