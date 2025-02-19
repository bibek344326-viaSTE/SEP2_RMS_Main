package client.networking.login;

import client.networking.GetServer;
import shared.networking.serverInterfaces.Server;
import shared.utils.Request;

public class LoginClientManager implements LoginClient {

    private Server server;

    public LoginClientManager() {
        server = GetServer.getServerFromRmi();
    }

    @Override
    public Request login(String username, String password) {
        try {
            return server.getLoginServer().isLoginPossible(username, password);
        } catch (Exception e) {
            e.printStackTrace();
            return new Request("Cannot connect to server", null);
        }
    }
}
