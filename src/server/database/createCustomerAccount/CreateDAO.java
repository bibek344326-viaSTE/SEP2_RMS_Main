package server.database.createCustomerAccount;

public interface CreateDAO {
    String addUser(String username, String password, String userType);
}
