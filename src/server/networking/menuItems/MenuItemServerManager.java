package server.networking.menuItems;

import server.model.menuItem.MenuItemHandler;
import shared.networking.serverInterfaces.MenuServer;
import shared.utils.Request;
import shared.utils.menuItem.MenuItem;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuItemServerManager implements MenuServer {

    private MenuItemHandler menuItemHandler;

    public MenuItemServerManager(MenuItemHandler menuItemHandler) throws RemoteException {
        this.menuItemHandler = menuItemHandler;
        UnicastRemoteObject.exportObject(this, 0);
    }

    @Override
    public ArrayList<MenuItem> getMenus() throws RemoteException, SQLException {
        return menuItemHandler.getMenuItems();
    }

    @Override
    public void updateMenu(MenuItem menu, String menuName, String menuType) throws RemoteException, SQLException {
        menuItemHandler.updateMenuItem(menu, menuName, menuType, menu.getMenuItemID());
    }

    @Override
    public Request createMenu(String menuName, String menuType) throws RemoteException {
        return menuItemHandler.addMenuItem(new MenuItem(menuName, menuType));
    }

    @Override
    public void deleteMenu(int menuItemID) throws RemoteException {
        menuItemHandler.removeMenuItem(menuItemID);
    }
}
