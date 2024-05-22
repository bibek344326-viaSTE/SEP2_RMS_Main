package server.model.menuItem;

import shared.utils.Request;
import shared.utils.Subject;
import shared.utils.menuItem.MenuItem;

import java.util.ArrayList;

public interface MenuItemHandler extends Subject {
    Request addMenuItem(MenuItem menuItem);
    void removeMenuItem(MenuItem menuItem);
    void updateMenuItem(MenuItem menuItem, String menuItemName, String menuItemType);
    MenuItem getMenuItem(int id);
    ArrayList<MenuItem> getMenuItems();
}
