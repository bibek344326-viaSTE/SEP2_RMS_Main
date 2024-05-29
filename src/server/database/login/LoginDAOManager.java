package server.database.login;

import server.database.DatabaseConnection;
import shared.utils.Request;
import shared.utils.user.Customer;
import shared.utils.user.KitchenChef;
import shared.utils.user.StaffMember;
import shared.utils.user.Usertype;

import java.sql.*;

public class LoginDAOManager implements LoginDAO {
    public LoginDAOManager() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());

    }

    @Override
    public Request login(String username, String password) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username =? and password =?;");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String accessType = resultSet.getString("access_type");
                connection.close();
                return getUserType(username, accessType);
            } else {
                connection.close();
                return new Request("Username or password incorrect", null);
            }
        } catch (SQLException e) {
            return new Request(e.getMessage(), null);
        }
    }

    private Request getUserType(String username, String accessType) {
        if (accessType.toUpperCase().equals(Usertype.STAFFMEMBERS.toString())) {
            return new Request(Usertype.STAFFMEMBERS.toString(), new StaffMember(username));
        } else if (accessType.toUpperCase().equals(Usertype.CUSTOMER.toString())) {
            return new Request(Usertype.CUSTOMER.toString(), new Customer(username));
        } else if (accessType.toUpperCase().equals(Usertype.KITCHENCHEF.toString())) {
            return new Request(Usertype.KITCHENCHEF.toString(), new KitchenChef(username));
        } else
            return new Request("Something went wrong in database", null);
    }
}
