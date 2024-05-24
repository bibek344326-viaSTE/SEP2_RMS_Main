package shared.utils.menuItem;

import java.io.Serial;
import java.io.Serializable;

public class MenuItem implements Serializable {
    private String menuItemName;
    private String menuItemType;
    private int menuItemID;

    public MenuItem(String menuItemName, String menuItemCategory) {
        this.menuItemName = menuItemName;
        this.menuItemType = menuItemCategory;
    }

    public int getMenuItemID() {
        return menuItemID;
    }

    public void setMenuItemID(int menuItemID) {
        this.menuItemID = menuItemID;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public String getMenuItemType() {
        return menuItemType;
    }

    public void setMenuItemType(MenuItemCategory menuItemCategory) {
        this.menuItemType = menuItemCategory.toString();
    }

    public String toString() {
        return "Item name: " + menuItemName + " Item type" + menuItemType;
    }
}
