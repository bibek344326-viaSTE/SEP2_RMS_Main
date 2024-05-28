package server;

import server.database.menuItem.MenuItemDAO;
import server.database.menuItem.MenuItemDAOManager;
import server.database.order.OrderDAO;
import server.database.order.OrderDAOManager;
import server.database.reservation.ReservationDAO;
import server.model.chat.ChatHandler;
import server.model.chat.ChatHandlerManager;
import server.model.createUser.CreateUserHandler;
import server.model.createUser.CreateUserHandlerManager;
import server.model.customer.CustomerListHandler;
import server.model.customer.CustomerListHandlerManager;
import server.model.login.LoginHandler;
import server.model.login.LoginHandlerManager;
import server.model.menuItem.MenuItemHandler;
import server.model.menuItem.MenuItemHandlerManager;
import server.model.order.OrderHandler;
import server.model.order.OrderHandlerManager;
import server.model.reservation.ReservationHandler;
import server.model.reservation.ReservationHandlerManager;
import server.model.tables.TablesHandler;
import server.model.tables.TablesHandlerManager;
import server.networking.ServerManager;
import server.networking.chat.ChatServerManager;
import server.networking.customer.CustomerListServerManager;
import server.networking.login.CreateUserServerManager;
import server.networking.login.LoginServerManager;
import server.networking.menuItems.MenuItemServerManager;
import server.networking.orders.OrdersServerManager;
import server.networking.reservation.ReservationListServerManager;
import server.networking.tables.TablesServerManager;
import shared.networking.serverInterfaces.*;

import shared.utils.table.Table;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class RunServer {
    public static void main(String[] args) throws Exception {

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

        ReservationHandler reservationHandler = new ReservationHandlerManager();
        ReservationServer reservationServer = new ReservationListServerManager(reservationHandler);

        MenuItemHandler menuItemHandler = new MenuItemHandlerManager();
        MenuServer menuServer = new MenuItemServerManager(menuItemHandler);

        OrderHandler orderHandler = new OrderHandlerManager();
        OrderServer orderServer = new OrdersServerManager(orderHandler);


        Server server = new ServerManager(loginServer, createAccountServer, tablesServerManager, customerListServer, chatServer, menuServer, orderServer, reservationServer);
        server.startServer();


//        ReservationDAO reservationDAO = new ReservationDAOManager();
//        for(int i=0; i<reservationDAO.().size(); i++){
//            System.out.println(reservationDAO.getReservations().get(i).getCustomer().getUsername()+"\n");;
//        }

        OrderDAO orderDAO = new OrderDAOManager();
        for (int i = 0; i < orderDAO.getAllOrders().size(); i++) {
            System.out.println(orderDAO.getAllOrders().get(i).getCustomer().getUsername() + "\n");
        }
//        MenuItemDAO menuItemDAO = new MenuItemDAOManager();
//        for(int i=0; i<menuItemDAO.getMenuItems().size(); i++){
//            System.out.println(menuItemDAO.getMenuItems().get(i).getMenuItemID() + " "+ menuItemDAO.getMenuItems().get(i)+"\n");;
//        }

    }
}
