package client.networking.menuItem;

import client.networking.GetServer;
import shared.networking.serverInterfaces.Server;
import shared.utils.menuItem.MenuItem;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuItemClientManager implements MenuItemClient {
    private Server server;
    //private PropertyChangeSupport support;

    public MenuItemClientManager() {
        try {
           this.server = GetServer.getServerFromRmi();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void createMenuItem(MenuItem menuItem) throws RemoteException {
        this.server.getMenuServer().createMenu(menuItem.getMenuItemName(), menuItem.getMenuItemType());
    }

    @Override
    public void updateMenuItem(MenuItem menuItem, String newName, String newType) {
        try {
            server.getMenuServer().updateMenu(menuItem, newName, newType);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeMenuItem(int menuItemID) {
        try {
            server.getMenuServer().deleteMenu(menuItemID);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MenuItem getMenuItem(int id) throws RemoteException, SQLException {
        return server.getMenuServer().getMenus().get(id);
    }

    @Override
    public ArrayList<MenuItem> getMenuItems() throws RemoteException, SQLException {
        return server.getMenuServer().getMenus();
    }

}
