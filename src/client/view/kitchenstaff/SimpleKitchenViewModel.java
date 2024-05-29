package client.view.kitchenstaff;

import javafx.beans.property.*;
import shared.utils.kitchenOrder.KitchenOrder;
import shared.utils.menuItem.MenuItem;
import shared.utils.order.Order;

import java.time.LocalDateTime;
import java.util.List;

public class SimpleKitchenViewModel {
    private final IntegerProperty orderID;
    private final StringProperty tablename;
    private final StringProperty customerName;
    //private final StringProperty ordertime;
    private final StringProperty menuitemname;
    private final StringProperty status;

    public SimpleKitchenViewModel(KitchenOrder kitchenOrder) {
        this.orderID = new SimpleIntegerProperty(kitchenOrder.getOrderId());
        this.tablename = new SimpleStringProperty(kitchenOrder.getTableName());
        this.customerName = new SimpleStringProperty(kitchenOrder.getCustomerName());
        //this.ordertime = new SimpleStringProperty();
        this.status = new SimpleStringProperty(kitchenOrder.getPreparationStatus());
        this.menuitemname = new SimpleStringProperty(kitchenOrder.getMenuItemName());
    }

    public IntegerProperty orderIDProperty() {return orderID;}

    public StringProperty tablenameProperty() {return tablename;}

    public StringProperty customerNameProperty() {return customerName;}

    //public StringProperty ordertimeProperty() {return ordertime;}

    public StringProperty menuitemnameProperty() {return menuitemname;}

    public StringProperty statusProperty() {return status;}


}