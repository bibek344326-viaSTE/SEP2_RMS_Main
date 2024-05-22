package client.model.menuItem;

import client.networking.menuItem.MenuItemClient;
import shared.utils.Request;
import shared.utils.menuItem.MenuItem;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class MenuItemModelManager implements MenuItemModel {
    private MenuItemClient menuItemClient;
    private PropertyChangeSupport support;

    public MenuItemModelManager(MenuItemClient menuItemClient) {
        this.menuItemClient = menuItemClient;
        support = new PropertyChangeSupport(menuItemClient);
    }

    private void fireProperty(PropertyChangeEvent evt) {
        support.firePropertyChange(evt);
    }

    @Override
    public ArrayList<MenuItem> getMenuItems() throws RemoteException {
        return (ArrayList<MenuItem>) menuItemClient.getMenuItems();
    }

    @Override
    public MenuItem getMenuItem(int id) throws RemoteException {
        return menuItemClient.getMenuItem(id);
    }

    @Override
    public Request createMenuItem(MenuItem menuItem) throws RemoteException {
        return menuItemClient.createMenuItem(menuItem);
    }

    @Override
    public void removeMenuItem(MenuItem menuItem) {
        menuItemClient.removeMenuItem(menuItem);
    }

    @Override
    public void updateMenuItem(MenuItem menuItem, String newName, String newType) {
        menuItemClient.updateMenuItem(menuItem, newName, newType);
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }
}
