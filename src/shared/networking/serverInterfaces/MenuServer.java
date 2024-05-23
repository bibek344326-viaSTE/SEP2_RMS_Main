package shared.networking.serverInterfaces;

import shared.utils.Request;
import shared.utils.menuItem.MenuItem;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface MenuServer extends Remote {
    ArrayList<MenuItem> getMenus() throws RemoteException, SQLException;
    void updateMenu(MenuItem menu, String menuName, String menuType) throws RemoteException, SQLException;
    Request createMenu(String menuName, String menuType) throws RemoteException;
    void deleteMenu(MenuItem menuItem) throws RemoteException;
}
