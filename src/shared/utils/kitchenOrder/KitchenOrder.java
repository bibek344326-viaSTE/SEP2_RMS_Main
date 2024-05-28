package shared.utils.kitchenOrder;

import javafx.scene.control.Tab;
import shared.utils.menuItem.MenuItem;
import shared.utils.order.Order;
import shared.utils.reservation.Reservation;
import shared.utils.table.Table;
import shared.utils.user.Customer;

import java.io.Serializable;

public class KitchenOrder implements Serializable {
    private String tableName;
    private String customerName;
    private int orderId;
    private int reservationID;
    private String menuItemName;
    private String preparationStatus;

    public KitchenOrder(String tableName, String customerName, int orderId, int reservationID, String menuItemName, String preparationStatus) {
        this.tableName = tableName;
        this.customerName = customerName;
        this.orderId = orderId;
        this.reservationID = reservationID;
        this.menuItemName = menuItemName;
        this.preparationStatus = preparationStatus;

    }

    public String getTableName() {
        return tableName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getReservationID() {
        return reservationID;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public String getPreparationStatus() {
        return preparationStatus;
    }

    public void setPreparationStatus(String preparationStatus) {
        this.preparationStatus = preparationStatus;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public String toString() {
        return "Table: " + tableName + " Customer: " + customerName + " Order ID: " + orderId + " Reservation ID: " + reservationID + " Menu Item: " + menuItemName + " Status: " + preparationStatus;
    }
}

