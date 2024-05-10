package client.core;

import client.model.chat.ChatModel;
import client.model.chat.ChatModelManager;
import client.model.createUser.CreateUserModel;
import client.model.createUser.CreateUserModelManager;
import client.model.customer.CustomerModel;
import client.model.customer.CustomerModelManager;
import client.model.login.LoginModel;
import client.model.login.LoginModelManager;
import client.model.tables.TableModel;
import client.model.tables.TableModelManager;

import java.rmi.RemoteException;

public class ModelFactory {
    private final ClientFactory client;
    private LoginModel loginModel;
    private TableModel tableManagementModel;
    private ChatModel chatModel;
    private CreateUserModel createUserModel;
    private CustomerModel customerModel;

    public ModelFactory(ClientFactory client) {
        this.client = client;
    }
    public LoginModel getLoginModel() {
        if (loginModel == null) {
            try {
                loginModel = new LoginModelManager(client.getLoginClient(), client.getChatClient());
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
        return loginModel;
    }
    public ChatModel getChatModel() {
        if (chatModel == null) {
            try {
                chatModel = new ChatModelManager(client.getChatClient());
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
        return chatModel;
    }
    public TableModel getTableModel() {
        if (tableManagementModel == null) {
            try {
                tableManagementModel = new TableModelManager(client.getTableClient());
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
        return tableManagementModel;
    }
    public CreateUserModel getCreateUserModel() {
        if (createUserModel == null) {
            try {
                createUserModel = new CreateUserModelManager(client.getCreateUserClient());
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
        return createUserModel;
    }
    public CustomerModel getCustomerModel() {
        if (customerModel == null) {
            try {
                customerModel = new CustomerModelManager(client.getCustomerInfoClient());
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
        return customerModel;
    }


}
