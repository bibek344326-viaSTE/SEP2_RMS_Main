package server.database.order;

import server.database.DatabaseConnection;
import server.database.customer.CustomerDAO;
import server.database.customer.CustomerDAOManager;
import server.database.table.TableDAO;
import server.database.table.TableDAOManager;
import shared.utils.menuItem.MenuItem;
import shared.utils.order.Order;
import shared.utils.order.OrderStatus;
import shared.utils.table.Table;
import shared.utils.user.Customer;

import java.sql.*;
import java.util.ArrayList;

public class OrderDAOManager implements OrderDAO {

    public OrderDAOManager() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createOrder(Order order) {
        String sql = "INSERT INTO orders (order_date) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setTimestamp(1, order.getOrderDateTime());
            pstmt.executeUpdate();

            // Retrieve the generated order ID
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                order.setOrderID(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order getOrder(int orderId) {
        String sqlOrder = "SELECT * FROM orders WHERE id = ?";
        String sqlMenuItems = "SELECT mi.id, mi.name, mi.type FROM menu_items mi " +
                "JOIN order_menu_items omi ON mi.id = omi.menu_item_id " +
                "WHERE omi.order_id = ?";
        Order order = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmtOrder = conn.prepareStatement(sqlOrder);
             PreparedStatement pstmtMenuItems = conn.prepareStatement(sqlMenuItems)) {

            // Retrieve order details
            pstmtOrder.setInt(1, orderId);
            ResultSet rsOrder = pstmtOrder.executeQuery();
            if (rsOrder.next()) {
                int id = rsOrder.getInt("id");
                String tableID = rsOrder.getString("tableName");
                String customerID = rsOrder.getString("customer_id");
                String orderStatus = rsOrder.getString("orderStatus");
                Timestamp orderTimestamp = rsOrder.getTimestamp("orderTimestamp");
                Table table = getTableByID(tableID); // Method to retrieve Table object by ID
                Customer customer = getCustomerByID(customerID); // Method to retrieve Customer object by ID

                order = new Order(id, table, customer);
                order.setOrderStaus(OrderStatus.valueOf(orderStatus));
                order.setOrderDateTime(orderTimestamp);
            }

            // Retrieve menu items associated with the order
            if (order != null) {
                pstmtMenuItems.setInt(1, orderId);
                ResultSet rsMenuItems = pstmtMenuItems.executeQuery();
                while (rsMenuItems.next()) {
                    int menuItemId = rsMenuItems.getInt("id");
                    String name = rsMenuItems.getString("name");
                    String type = rsMenuItems.getString("type");
                    MenuItem menuItem = new MenuItem(name, type);
                    order.addOrderItemsList(menuItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }


    @Override
    public void addMenuToOrder(int orderId, MenuItem menuItem) {
        String sql = "INSERT INTO order_menu_items (order_id, menu_item_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            pstmt.setInt(2, menuItem.getMenuItemID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeMenuFromOrder(int orderId, MenuItem menuItem) {
        String sql = "DELETE FROM order_menu_items WHERE order_id = ? AND menu_item_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            pstmt.setInt(2, menuItem.getMenuItemID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Order> getOrders() {
        String sqlOrders = "SELECT * FROM orders";
        String sqlMenuItems = "SELECT mi.id, mi.name, mi.type, omi.order_id FROM menu_item mi " +
                "JOIN order_menu_item omi ON mi.id = omi.menu_item_id";
        ArrayList<Order> orders = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmtOrders = conn.createStatement();
             Statement stmtMenuItems = conn.createStatement();
             ResultSet rsOrders = stmtOrders.executeQuery(sqlOrders);
             ResultSet rsMenuItems = stmtMenuItems.executeQuery(sqlMenuItems)) {

            // Retrieve all orders
            while (rsOrders.next()) {
                int id = rsOrders.getInt("id");
                String tableID = rsOrders.getString("table_id");
                String customerID = rsOrders.getString("customer_id");
                String orderStatus = rsOrders.getString("status");
                Timestamp orderTimestamp = rsOrders.getTimestamp("orderTimestamp");
                Table table = getTableByID(tableID); // Method to retrieve Table object by ID
                Customer customer = getCustomerByID(customerID); // Method to retrieve Customer object by ID

                Order order = new Order(id, table, customer);
                order.setOrderStaus(OrderStatus.valueOf(orderStatus));
                order.setOrderDateTime(orderTimestamp);
                orders.add(order);
            }

            // Retrieve all menu items and map them to the appropriate orders
            while (rsMenuItems.next()) {
                int orderId = rsMenuItems.getInt("order_id");
                int menuItemId = rsMenuItems.getInt("id");
                String name = rsMenuItems.getString("name");
                String type = rsMenuItems.getString("type");
                MenuItem menuItem = new MenuItem(name, type);

                for (Order order : orders) {
                    if (order.getOrderID() == orderId) {
                        order.addOrderItemsList(menuItem);
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    private Table getTableByID(String tableName) throws SQLException {
        // Implement this method to retrieve a Table object from the database based on the tableID
        TableDAO tableDAO = new TableDAOManager();

        return tableDAO.getTable(tableName); // Replace with actual implementation
    }

    private Customer getCustomerByID(String customerID) {
        // Implement this method to retrieve a Customer object from the database based on the customerID
        CustomerDAO customerDAO = new CustomerDAOManager();

        return customerDAO.getCustomer(customerID); // Replace with actual implementation
    }

}
