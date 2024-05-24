package shared.utils.order;

import shared.utils.menuItem.MenuItem;
import shared.utils.table.Table;
import shared.utils.user.Customer;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {
    private int orderID;
    private Table table;
    private Customer customer;
    private ArrayList<MenuItem> orderItemsList;
    private String orderStatus;
    private Timestamp orderDateTime;

    public Order(int orderID, Table table, Customer customer) {
        this.orderID = orderID;
        this.table = table;
        this.customer = customer;
        orderItemsList = new ArrayList<MenuItem>();
        orderStatus = OrderStatus.ORDERED.toString();
        this.orderDateTime = Timestamp.valueOf(LocalDateTime.now());
    }

    public Timestamp getOrderDateTime() {
        return orderDateTime;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public MenuItem getOrderItemsList(int menuItemId) {
        return orderItemsList.get(menuItemId);
    }

    public void addOrderItemsList(MenuItem menuItem) {
        this.orderItemsList.add(menuItem);
    }

    public void removeOrderItemsList(MenuItem menuItem) {
        this.orderItemsList.remove(menuItem);
    }

    public String getOrderStaus() {
        return orderStatus;
    }

    public void setOrderStaus(OrderStatus orderStaus) {
        this.orderStatus = orderStaus.toString();
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", table=" + table +
                ", customer=" + customer +
                ", orderItemsList=" + orderItemsList +
                '}'
                + "orderTime = " + orderDateTime + ", orderStatus = " + orderStatus + '}';
    }

    public ArrayList<MenuItem> getMenuItems() {
        return orderItemsList;
    }

    public void setMenuItems(ArrayList<MenuItem> menuItems) {
        this.orderItemsList = menuItems;
    }

    public void setOrderDateTime(Timestamp orderTimestamp) {
        this.orderDateTime = orderTimestamp;
    }
}
