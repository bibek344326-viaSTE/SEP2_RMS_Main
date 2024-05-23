package server.model.menuItem;

import server.database.menuItem.MenuItemDAO;
import server.database.menuItem.MenuItemDAOManager;
import shared.utils.Request;
import shared.utils.menuItem.MenuItem;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuItemHandlerManager implements MenuItemHandler {

    private MenuItemDAO menuItemDAO;

    private PropertyChangeSupport support;

    public MenuItemHandlerManager() {
        menuItemDAO = new MenuItemDAOManager();
        support = new PropertyChangeSupport(this);
    }

    @Override
    public Request addMenuItem(MenuItem menuItem) {
        menuItemDAO.addMenuItem(menuItem);
        support.firePropertyChange("menuItemAdded", null, menuItem);
        return null;
    }

    @Override
    public void removeMenuItem(MenuItem menuItem) {
        menuItemDAO.removeMenuItem(menuItem.getMenuItemID());
        support.firePropertyChange("menuItemRemoved", menuItem, null);

    }

    @Override
    public void updateMenuItem(MenuItem menuItem, String newName, String newType, int menuItemID) throws SQLException {
        for (int i = 0; i < menuItemDAO.getMenuItems().size(); i++) {
            if (menuItemDAO.getMenuItem(menuItem.getMenuItemName()).getMenuItemID() == menuItem.getMenuItemID()) {
                MenuItem oldMenuItem = menuItemDAO.updateMenuItem(menuItem,newName,newType,menuItem.getMenuItemID());
                support.firePropertyChange("menuItemUpdated", oldMenuItem, menuItem);
                return;
            }
        }
    }

    @Override
    public MenuItem getMenuItem(int id) throws SQLException {
        for (MenuItem menuItem : menuItemDAO.getMenuItems()) {
            if (menuItem.getMenuItemID() == id) {
                return menuItem;
            }
        }
        return null;
    }

    @Override
    public ArrayList<MenuItem> getMenuItems() throws SQLException {
        return menuItemDAO.getMenuItems();
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
