package client.core;

import client.model.kitchenOrders.KitchenOrdersModel;
import client.model.kitchenOrders.KitchenOrdersModelManager;
import client.model.Reservation.ReservationModel;
import client.model.Reservation.ReservationModelManager;
import client.model.chat.ChatModel;
import client.model.chat.ChatModelManager;
import client.model.createUser.CreateUserModel;
import client.model.createUser.CreateUserModelManager;
import client.model.customer.CustomerModel;
import client.model.customer.CustomerModelManager;
import client.model.login.LoginModel;
import client.model.login.LoginModelManager;
import client.model.menuItem.MenuItemModel;
import client.model.menuItem.MenuItemModelManager;
import client.model.order.OrderModel;
import client.model.order.OrderModelManager;
import client.model.tables.TableModel;
import client.model.tables.TableModelManager;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class ModelFactory {
    private ClientFactory client;
    private LoginModel loginModel;
    private TableModel tableManagementModel;
    private ChatModel chatModel;
    private CreateUserModel createUserModel;
    private CustomerModel customerModel;
    private MenuItemModel menuItemModel;
    private OrderModel orderModel;
    private ReservationModel reservationModel;
    private KitchenOrdersModel kitchenOrdersModel;

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

    public TableModel getTableModel() throws RemoteException {
        if (tableManagementModel == null) {
            tableManagementModel = new TableModelManager(client.getTableClient());
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

    public OrderModel getOrderModel() throws RemoteException {
        if (orderModel == null) {
            orderModel = new OrderModelManager(client.getOrderClient());
        }
        return orderModel;
    }

    public ReservationModel getReservationModel() throws RemoteException, SQLException {
        if (reservationModel == null) {
            reservationModel = new ReservationModelManager(client.getReservationClient());
        }
        return reservationModel;
    }

    public MenuItemModel getMenuItemModel() throws RemoteException {
        if (menuItemModel == null) {
            menuItemModel = new MenuItemModelManager(client.getMenuItemClient());
        }
        return menuItemModel;
    }

    public KitchenOrdersModel getKitchenOrdersModel() throws RemoteException {
        if (kitchenOrdersModel == null) {
            kitchenOrdersModel = new KitchenOrdersModelManager(client.getKitchenOrdersClient());
        }
        return kitchenOrdersModel;
    }
}
