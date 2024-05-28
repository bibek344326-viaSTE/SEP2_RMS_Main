package client.view.staff.Order;

import javafx.beans.property.*;
import shared.utils.menuItem.MenuItem;
import shared.utils.order.Order;

import java.util.List;

public class SimpleStaffOrderViewModel {
    private final IntegerProperty orderID;
    private final StringProperty tablename;
    private final StringProperty customerName;
    private final StringProperty ordertime;
    private final StringProperty menuitemname;
    private final StringProperty status;

    public SimpleStaffOrderViewModel(Order order) {
        this.orderID = new SimpleIntegerProperty(order.getOrderID());
        this.tablename = new SimpleStringProperty(order.getTable().getTableName());
        this.customerName = new SimpleStringProperty(order.getCustomer().getUsername());
        this.ordertime = new SimpleStringProperty(order.getOrderDateTime().toString());
        this.status = new SimpleStringProperty(order.getOrderStaus());

        List<MenuItem> menuItemList = order.getOrderMenuItemsList();
        if (menuItemList != null && !menuItemList.isEmpty()) {
            StringBuilder menuItems = new StringBuilder();
            for (MenuItem menuItem : menuItemList) {
                menuItems.append(menuItem.getMenuItemName()).append(", ");
            }
            String menuItemsString = menuItems.substring(0, menuItems.length() - 2); // Remove the last comma and space
            this.menuitemname = new SimpleStringProperty(menuItemsString);
        } else {
            this.menuitemname = new SimpleStringProperty("No menu items"); // Provide a default value
        }
    }

    public IntegerProperty orderIDProperty() {
        return orderID;
    }

    public StringProperty tablenameProperty() {
        return tablename;
    }

    public StringProperty customerNameProperty() {
        return customerName;
    }

    public StringProperty ordertimeProperty() {
        return ordertime;
    }

    public StringProperty menuitemnameProperty() {
        return menuitemname;
    }

    public StringProperty statusProperty() {
        return status;
    }
}