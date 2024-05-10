package server.model.login;

import server.database.login.LoginDAO;
import server.database.login.LoginDAOManager;
import shared.utils.Request;
import shared.utils.user.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginHandlerManager implements LoginHandler {
    private List<User> allUsers;
    private List<String> allUserNames;
    private LoginDAO loginDAO;

    public LoginHandlerManager() throws SQLException {
        allUsers = new ArrayList<>();
        allUserNames = new ArrayList<>();
        loginDAO = new LoginDAOManager();
    }

    @Override
    public Request login(String username, String password) {
        try {
            return loginDAO.login(username, password);
        } catch (Exception e) {
            return new Request("Error connecting to database", null);
        }
    }
}
