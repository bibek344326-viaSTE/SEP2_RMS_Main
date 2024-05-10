package client.model.login;

import shared.utils.Request;

public interface LoginModel {
    Request login(String username, String password);

    void setUsername(String userName);

    String getUsername();

    void setUserType(String userType);

    String getUserType();
}
