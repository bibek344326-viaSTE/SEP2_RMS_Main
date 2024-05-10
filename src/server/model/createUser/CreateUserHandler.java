package server.model.createUser;

public interface CreateUserHandler {
    String addUser(String username, String password, String userType);
}
