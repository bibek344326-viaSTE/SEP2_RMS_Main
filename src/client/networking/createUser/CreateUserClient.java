package client.networking.createUser;

public interface CreateUserClient {
    String addUser(String username, String password, String userType);
}
