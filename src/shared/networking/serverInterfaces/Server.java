package shared.networking.serverInterfaces;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server extends Remote {
    ChatServer getChatServer() throws RemoteException;

    CreateAccountServer getCreateAccountServer() throws RemoteException;

    CustomerListServer getCustomerListServer() throws RemoteException;

    LoginServer getLoginServer() throws RemoteException;

    TableServer getTableServer() throws RemoteException;

    MenuServer getMenuServer() throws RemoteException;

    OrderServer getOrderServer() throws RemoteException;

    ReservationServer getReservationServer() throws RemoteException;

    KitchenOrdersServer getKitchenOrdersServer() throws RemoteException;

    void startServer() throws RemoteException, AlreadyBoundException;
}
