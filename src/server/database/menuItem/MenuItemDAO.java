package server.database.menuItem;

import shared.utils.menuItem.MenuItem;

import java.util.ArrayList;

public interface MenuItemDAO {
    void addMenuItem(MenuItem menuItem);
    void removeMenuItem(int menuItemId);
    void updateMenuItem(MenuItem menuItem, String newName, String newType, int menuItemID);
    MenuItem getMenuItem(String menuItemName);
    ArrayList<MenuItem> getMenuItems();
}
