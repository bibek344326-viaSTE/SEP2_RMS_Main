package server.database.order;

import server.database.DatabaseConnection;
import server.database.customer.CustomerDAO;
import server.database.customer.CustomerDAOManager;
import server.database.menuItem.MenuItemDAO;
import server.database.menuItem.MenuItemDAOManager;
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void createOrder(Order order) {
        String sql = "INSERT INTO orders (orderid, tableid, customerid, ordertimestamp) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, order.getOrderID());
            pstmt.setString(2, order.getTable().getTableName());
            pstmt.setString(3, order.getCustomer().getUsername());
            pstmt.setTimestamp(4, order.getOrderDateTime());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order getOrder(int orderId) {
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE orderid = ?;");
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int orderID = resultSet.getInt("orderid");
                String tableID = resultSet.getString("tableid");
                String customerID = resultSet.getString("customerid");
                Timestamp orderDateTime = resultSet.getTimestamp("ordertimestamp");
                TableDAO tableDAO = new TableDAOManager();
                Table table = tableDAO.getTable(tableID);
                CustomerDAO customerDAO = new CustomerDAOManager();
                Customer customer = customerDAO.getCustomer(customerID);
                Order order = new Order(orderID, table, customer, orderDateTime);
                order.setCustomer(customer); // Set the Customer object
                return order;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public void addMenuToOrder(int orderId, int menuItemid) {
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO orderitems (orderid, menuitemid,orderstatus) VALUES (?, ?,?);");
            statement.setInt(1, orderId);
            statement.setInt(2, menuItemid);
            statement.setString(3, OrderStatus.ORDERED.name());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeMenuFromOrder(int orderId, int menuItemID) {
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM orderitems WHERE orderid = ? AND menuitemid = ?;");
            statement.setInt(1, orderId);
            statement.setInt(2, menuItemID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Order> getAllOrders() {
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders;");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Order> orderArrayList = new ArrayList<>();
            while (resultSet.next()) {
                int orderID = resultSet.getInt("orderid");
                String tableID = resultSet.getString("tableid");
                String customerID = resultSet.getString("customerID");
                Customer customer = getCustomer(customerID); // Get the Customer object
                System.out.println("Customer ID: " + customerID); // Add this line
                System.out.println("Customer: " + customer); // Add this line

                Timestamp orderDateTime = resultSet.getTimestamp("ordertimestamp");
                TableDAO tableDAO = new TableDAOManager();
                Table table = tableDAO.getTable(tableID);
                Order order = new Order(orderID, table, customer, orderDateTime);
                order.setCustomer(customer); // Set the Customer object
                orderArrayList.add(order);
            }
            return orderArrayList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteOrder(int orderId) {
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE orderid = ?;");
            statement.setInt(1, orderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<MenuItem> getOrderItems(int orderId) {
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM orderitems WHERE orderid = ?;");
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<MenuItem> menuItems = new ArrayList<>();
            while (resultSet.next()) {
                int menuItemID = resultSet.getInt("menuitemid");
                MenuItemDAO menuItemDAO = new MenuItemDAOManager();
                MenuItem menuItem = menuItemDAO.getMenuItem(String.valueOf(menuItemID));
                menuItems.add(menuItem);
            }
            return menuItems;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateOrderStatus(int orderId, OrderStatus orderStatus) {
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE orderitems SET orderstatus = ? WHERE orderid = ?;");
            statement.setString(1, orderStatus.name());
            statement.setInt(2, orderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Customer getCustomer(String customerID) {
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ?;");
            statement.setString(1, customerID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                return new Customer(username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
