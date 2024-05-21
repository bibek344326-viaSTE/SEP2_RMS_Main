package shared.utils.order;

import java.util.ArrayList;

public class OrdersList {
    private ArrayList<Order> orders;

    public OrdersList() {
        this.orders = new ArrayList<>();
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public void removeOrder(Order order) {
        this.orders.remove(order);
    }

    public void printOrders() {
        for (Order order : orders) {
            System.out.println(order.toString());
        }
    }
}
