package client.networking.login;

import shared.utils.Request;

public interface LoginClient {
    Request login(String username, String password);

}
