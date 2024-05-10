package server.model.login;

import shared.utils.Request;

public interface LoginHandler {
    Request login(String username, String password);
}
