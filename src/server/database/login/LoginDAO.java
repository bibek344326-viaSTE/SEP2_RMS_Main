package server.database.login;

import shared.utils.Request;

public interface LoginDAO {
    Request login(String username, String password);

}
