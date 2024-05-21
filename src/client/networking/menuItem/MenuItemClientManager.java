package client.networking.menuItem;

import client.networking.GetServer;
import shared.networking.serverInterfaces.Server;
import shared.utils.menuItem.MenuItem;

import java.rmi.RemoteException;
import java.util.List;

public class MenuItemClientManager implements MenuItemClient{
    private Server server;
    public MenuItemClientManager() {
        try {
            this.server = GetServer.getServerFromRmi();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
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
}
