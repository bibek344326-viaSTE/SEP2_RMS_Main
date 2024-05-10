package server.database.createCustomerAccount;

import server.database.DatabaseConnection;

import java.sql.*;

public class CreateDAOManager implements CreateDAO {

    public CreateDAOManager() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String addUser(String username, String password, String userType) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * from \"User\" where \"username\"=?;");
            statement.setString(1,username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                connection.close();
                return "Username is already taken";
            } else {
                PreparedStatement statement2 = connection.prepareStatement("INSERT INTO \"User\"(\"username\", \"password\", \"access_type\") VALUES (?,?,?);");
                statement2.setString(1, username);
                statement2.setString(2, password);
                statement2.setString(3, userType);
                statement2.executeUpdate();
                connection.close();
                return "User created successfully";
            }
        } catch (SQLException throwables) {
            return throwables.getMessage();
        }
    }
}
