package server.database.customer;

import server.database.DatabaseConnection;
import shared.utils.user.Customer;
import shared.utils.user.Usertype;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOManager implements CustomerDAO {
    public CustomerDAOManager() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createCustomer(Customer customer) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users VALUES (?, ?, ?)");
            statement.setString(1, customer.getUsername());
            statement.setString(2, customer.getPassword());
            statement.setString(3, Usertype.CUSTOMER.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCustomer(Customer customer, String oldUsername) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE \"User\" SET (username,password)=(?,?,?,?) WHERE \"username\"=?;");
            statement.setString(3, customer.getUsername());
            statement.setString(4, customer.getPassword());
            statement.setString(5, oldUsername);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeCustomer(String customerid) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM \"User\" WHERE \"username\"=?;");
            statement.setString(1, customerid);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Customer getCustomer(String username) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"User\" WHERE \"username\"=?;");
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String password = resultSet.getString("password");
                connection.close();
                return new Customer(username, password);
            } else {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Customer> getCustomers() {
        ArrayList<Customer> list = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from users WHERE accesstype='CUSTOMER'");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                list.add(new Customer(username, password));
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(list);
        return list;
    }
}

