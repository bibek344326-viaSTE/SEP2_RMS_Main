package client.model.login;

import client.networking.chat.ChatClient;
import client.networking.login.LoginClient;
import shared.utils.Request;

public class LoginModelManager implements LoginModel {

    private LoginClient loginClient;
    private String userName;
    private String userType;
    private String password;


    public LoginModelManager(LoginClient loginClient) {
        this.loginClient = loginClient;
    }

    @Override
    public Request login(String username, String password) {
        return loginClient.login(username, password);
    }

    @Override
    public void setUsername(String userName) {
        this.userName = userName;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public void setUserType(String userType) {
        this.userType = userType;
        System.out.println("UserType set as :" + this.userType);
    }

    @Override
    public String getUserType() {
        System.out.println("Returned usertype as " + userType);
        return userType;
    }
}
