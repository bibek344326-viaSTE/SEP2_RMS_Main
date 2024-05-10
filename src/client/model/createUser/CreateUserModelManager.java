package client.model.createUser;

import client.networking.createUser.CreateUserClient;

public class CreateUserModelManager implements CreateUserClient {
    public CreateUserClient createUserClient;

    public CreateUserModelManager(CreateUserClient createUserClient) {
        this.createUserClient = createUserClient;
    }

    @Override
    public String addUser(String username, String password, String userType) {
        return createUserClient.addUser(username, password, userType);
    }
}
