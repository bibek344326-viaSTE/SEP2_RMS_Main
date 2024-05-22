package shared.networking.serverInterfaces;

import shared.utils.Request;
import shared.utils.menuItem.MenuItem;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface MenuServer extends Remote {
    ArrayList<MenuItem> getMenus() throws RemoteException;
    void updateMenu(MenuItem menu, String menuName, String menuType) throws RemoteException;
    Request createMenu(String menuName, String menuType) throws RemoteException;
    void deleteMenu(MenuItem menuItem) throws RemoteException;
}
