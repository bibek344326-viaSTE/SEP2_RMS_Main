package client.core;

import client.networking.chat.ChatClient;
import client.networking.chat.ChatClientManager;
import client.networking.createUser.CreateUserClient;
import client.networking.createUser.CreateUserClientManager;
import client.networking.customer.CustomerInfoClient;
import client.networking.customer.CustomerInfoClientManager;
import client.networking.login.LoginClient;
import client.networking.login.LoginClientManager;
import client.networking.tables.TablesClient;
import client.networking.tables.TablesClientManager;

import java.rmi.RemoteException;

public class ClientFactory {
    private LoginClient loginClient;
    private TablesClient tableClient;
    private ChatClient chatClient;
    private CreateUserClient createUserClient;
    private CustomerInfoClient customerInfoClient;

    public LoginClient getLoginClient() throws RemoteException {
        if(loginClient == null){
            loginClient = new LoginClientManager();
        }
        return loginClient;
    }

    public ChatClient getChatClient() throws RemoteException{
        if(chatClient == null){
            chatClient = new ChatClientManager();
        }
        return chatClient;
    }

    public TablesClient getTableClient() throws RemoteException {
        if(tableClient == null){
            tableClient = new TablesClientManager();
        }
        return tableClient;
    }
    public CreateUserClient getCreateUserClient() throws RemoteException {
        if(createUserClient == null){
            createUserClient = new CreateUserClientManager();
        }
        return createUserClient;
    }
    public CustomerInfoClient getCustomerInfoClient() throws RemoteException {
        if(customerInfoClient == null){
            customerInfoClient = new CustomerInfoClientManager();
        }
        return customerInfoClient;
    }

}

