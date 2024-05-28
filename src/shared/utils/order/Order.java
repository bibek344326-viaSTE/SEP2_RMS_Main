package shared.utils.order;

import shared.utils.menuItem.MenuItem;
import shared.utils.table.Table;
import shared.utils.user.Customer;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order implements Serializable {
    private static final long serialVersionUID = 8832161865413793103L;

    private int orderID;
    private Table table;
    private Customer customer;
    private ArrayList<MenuItem> orderMenuItemsList;
    private String orderStatus;
    private Timestamp orderDateTime;

    public ArrayList<MenuItem> getOrderMenuItemsList() {
        return orderMenuItemsList;
    }

    public Order(int orderID, Table table, Customer customer) {
        this.orderID = orderID;
        this.table = table;
        this.customer = customer;
        this.orderMenuItemsList = new ArrayList<>();
        this.orderDateTime = Timestamp.valueOf(LocalDateTime.now());
    }

    public Order(int orderID, Table table, Customer customer, Timestamp orderDateTime) {
        this.orderID = orderID;
        this.table = table;
        this.customer = customer;
        this.orderDateTime = orderDateTime;
        this.orderMenuItemsList = new ArrayList<>();
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
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        if (customer == null) {
            this.customer = new Customer(customer.getUsername(), customer.getPassword());
        }
        this.customer = customer;
    }

    public void addOrderItemsList(MenuItem menuItem) {
        this.orderMenuItemsList.add(menuItem);
    }

    public void removeOrderItemsList(MenuItem menuItem) {
        this.orderMenuItemsList.remove(menuItem);
    }

    public String getOrderStaus() {
        return orderStatus;
    }


    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", table=" + table +
                ", customer=" + customer +
                ", orderItemsList=" + orderMenuItemsList +
                '}'
                + "orderTime = " + orderDateTime + ", orderStatus = " + orderStatus + '}';
    }

    public ArrayList<MenuItem> getMenuItems() {
        return orderMenuItemsList;
    }

    public void setMenuItems(ArrayList<MenuItem> menuItems) {
        this.orderMenuItemsList = menuItems;
    }

    public void setOrderDateTime(Timestamp orderTimestamp) {
        this.orderDateTime = orderTimestamp;
    }
}
