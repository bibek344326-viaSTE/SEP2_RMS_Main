package client.view.kitchenstaff;

import javafx.beans.property.*;
import shared.utils.order.Order;

import java.time.LocalDateTime;

public class SimpleKitchenViewModel {
    private final IntegerProperty orderID;
    private final StringProperty tablename;
    private final StringProperty customerName;
    //private final ObjectProperty<LocalDateTime> time;

    public SimpleKitchenViewModel(Order order) {
        this.orderID = new SimpleIntegerProperty(order.getOrderID());
        this.tablename = new SimpleStringProperty(order.getTable().getTableName());
        this.customerName = new SimpleStringProperty(order.getCustomer().getUsername());
        //this.time = new SimpleObjectProperty<TimeStamp>(order.getOrderDateTime());
    }
    public IntegerProperty orderIDProperty() {return orderID;}

    public StringProperty tablenameProperty() {return tablename;}

    public StringProperty customerNameProperty() {return customerName;}
}