package server.model.createUser;

import server.database.createCustomerAccount.CreateDAO;
import server.database.createCustomerAccount.CreateDAOManager;

public class CreateUserHandlerManager implements CreateUserHandler {

    private CreateDAO createDAO;

    public CreateUserHandlerManager() {
        createDAO = new CreateDAOManager();
    }

    @Override
    public String addUser(String username, String password, String userType) {
        return createDAO.addUser(username, password, userType);
    }
}
