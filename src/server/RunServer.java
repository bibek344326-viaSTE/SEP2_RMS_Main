package server;

import server.model.chat.ChatHandler;
import server.model.chat.ChatHandlerManager;
import server.model.createUser.CreateUserHandler;
import server.model.createUser.CreateUserHandlerManager;
import server.model.customer.CustomerListHandler;
import server.model.customer.CustomerListHandlerManager;
import server.model.login.LoginHandler;
import server.model.login.LoginHandlerManager;
import server.model.tables.TablesHandler;
import server.model.tables.TablesHandlerManager;
import server.networking.ServerManager;
import server.networking.chat.ChatServerManager;
import server.networking.customer.CustomerListServerManager;
import server.networking.login.CreateUserServerManager;
import server.networking.login.LoginServerManager;
import server.networking.tables.TablesServerManager;
import shared.networking.serverInterfaces.*;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class RunServer {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, SQLException {

        LoginHandler loginHandler = new LoginHandlerManager();
        LoginServer loginServer = new LoginServerManager(loginHandler);

        CreateUserHandler createUserHandler = new CreateUserHandlerManager();
        CreateAccountServer createAccountServer = new CreateUserServerManager(createUserHandler);

        TablesHandler tablesHandler = new TablesHandlerManager();
        TablesServerManager tablesServerManager = new TablesServerManager(tablesHandler);

        CustomerListHandler customerListHandler = new CustomerListHandlerManager();
        CustomerListServer customerListServer = new CustomerListServerManager(customerListHandler);

        ChatHandler chatHandler = new ChatHandlerManager();
        ChatServer chatServer = new ChatServerManager(chatHandler);

        Server server = new ServerManager(loginServer, createAccountServer, tablesServerManager, customerListServer, chatServer);
        server.startServer();
    }
}
