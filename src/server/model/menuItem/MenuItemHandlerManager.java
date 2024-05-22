package server.model.menuItem;

import shared.utils.Request;
import shared.utils.menuItem.MenuItem;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class MenuItemHandlerManager implements MenuItemHandler {

    private List<MenuItem> menuItems;
    private PropertyChangeSupport support;

    public MenuItemHandlerManager() {
        menuItems = new ArrayList<>();
        support = new PropertyChangeSupport(this);
    }

    @Override
    public Request addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
        support.firePropertyChange("menuItemAdded", null, menuItem);
        return null;
    }

    @Override
    public void removeMenuItem(MenuItem menuItem) {
        if (menuItems.remove(menuItem)) {
            support.firePropertyChange("menuItemRemoved", menuItem, null);
        }
    }

    @Override
    public void updateMenuItem(MenuItem menuItem, String newName, String newType) {
        for (int i = 0; i < menuItems.size(); i++) {
            if (menuItems.get(i).getMenuItemID() == menuItem.getMenuItemID()) {
                MenuItem oldMenuItem = menuItems.set(i, menuItem);
                support.firePropertyChange("menuItemUpdated", oldMenuItem, menuItem);
                return;
            }
        }
    }

    @Override
    public MenuItem getMenuItem(int id) {
        for (MenuItem menuItem : menuItems) {
            if (menuItem.getMenuItemID() == id) {
                return menuItem;
            }
        }
        return null;
    }

    @Override
    public ArrayList<MenuItem> getMenuItems() {
        return (ArrayList<MenuItem>) menuItems;
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
