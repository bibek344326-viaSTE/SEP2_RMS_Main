package client.model.menuItem;

import shared.utils.Request;
import shared.utils.Subject;
import shared.utils.menuItem.MenuItem;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface MenuItemModel extends Subject {
    ArrayList<MenuItem> getMenuItems() throws RemoteException, SQLException;
    MenuItem getMenuItem(int id) throws RemoteException, SQLException;
    void createMenuItem(MenuItem menuItem) throws RemoteException;
    void removeMenuItem(int menuItemId) throws RemoteException;
    void updateMenuItem(MenuItem menuItem, String newName, String newType);

}
