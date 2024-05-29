package client.core;

import client.networking.chat.ChatClient;
import client.networking.chat.ChatClientManager;
import client.networking.createUser.CreateUserClient;
import client.networking.createUser.CreateUserClientManager;
import client.networking.customer.CustomerInfoClient;
import client.networking.customer.CustomerInfoClientManager;
import client.networking.kitchenOrders.KitchenOrdersClient;
import client.networking.kitchenOrders.KitchenOrdersClientManager;
import client.networking.login.LoginClient;
import client.networking.login.LoginClientManager;
import client.networking.menuItem.MenuItemClient;
import client.networking.menuItem.MenuItemClientManager;
import client.networking.order.OrderClient;
import client.networking.order.OrderClientManager;
import client.networking.reservation.ReservationClient;
import client.networking.reservation.ReservationClientManager;
import client.networking.tables.TablesClient;
import client.networking.tables.TablesClientManager;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class ClientFactory {
    private LoginClient loginClient;
    private TablesClient tableClient;
    private CreateUserClient createUserClient;
    private CustomerInfoClient customerInfoClient;
    private ReservationClient reservationClient;
    private MenuItemClient menuItemClient;
    private OrderClient orderClient;

    private KitchenOrdersClient kitchenOrdersClient;

    public LoginClient getLoginClient() throws RemoteException {
        if (loginClient == null) {
            loginClient = new LoginClientManager();
        }
        return loginClient;
    }

    public TablesClient getTableClient() throws RemoteException {
        if (tableClient == null) {
            tableClient = new TablesClientManager();
        }
        return tableClient;
    }

    public CreateUserClient getCreateUserClient() throws RemoteException {
        if (createUserClient == null) {
            createUserClient = new CreateUserClientManager();
        }
        return createUserClient;
    }

    public CustomerInfoClient getCustomerInfoClient() throws RemoteException {
        if (customerInfoClient == null) {
            customerInfoClient = new CustomerInfoClientManager();
        }
        return customerInfoClient;
    }

    public ReservationClient getReservationClient() throws RemoteException, SQLException {
        if (reservationClient == null) {
            reservationClient = new ReservationClientManager();
        }
        return reservationClient;
    }

    public OrderClient getOrderClient() throws RemoteException {
        if (orderClient == null) {
            orderClient = new OrderClientManager();
        }
        return orderClient;
    }

    public MenuItemClient getMenuItemClient() throws RemoteException {
        if (menuItemClient == null) {
            menuItemClient = new MenuItemClientManager();
        }
        return menuItemClient;
    }

    public KitchenOrdersClient getKitchenOrdersClient() throws RemoteException {
        if (kitchenOrdersClient == null) {
            kitchenOrdersClient = new KitchenOrdersClientManager();
        }
        return kitchenOrdersClient;
    }
}

