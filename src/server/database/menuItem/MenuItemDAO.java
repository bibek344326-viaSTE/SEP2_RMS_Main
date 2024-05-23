package server.database.menuItem;

import shared.utils.menuItem.MenuItem;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MenuItemDAO {
    void addMenuItem(MenuItem menuItem);
    void removeMenuItem(int menuItemId);
    MenuItem updateMenuItem(MenuItem menuItem, String newName, String newType, int menuItemID);
    MenuItem getMenuItem(String menuItemName);
    ArrayList<MenuItem> getMenuItems() throws SQLException;
}
