package server.model.menuItem;

import shared.utils.Request;
import shared.utils.Subject;
import shared.utils.menuItem.MenuItem;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MenuItemHandler extends Subject {
    Request addMenuItem(MenuItem menuItem);
    void removeMenuItem(int menuItemID);
    void updateMenuItem(MenuItem menuItem, String menuItemName, String menuItemType, int menuItemID) throws SQLException;
    MenuItem getMenuItem(int id) throws SQLException;
    ArrayList<MenuItem> getMenuItems() throws SQLException;
}
