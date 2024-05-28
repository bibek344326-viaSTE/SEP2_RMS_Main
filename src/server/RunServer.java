package server;

import server.database.order.OrderDAO;
import server.database.order.OrderDAOManager;
import server.database.reservation.ReservationDAO;
import server.database.reservation.ReservationDAOManager;
import server.model.chat.ChatHandler;
import server.model.chat.ChatHandlerManager;
import server.model.createUser.CreateUserHandler;
import server.model.createUser.CreateUserHandlerManager;
import server.model.customer.CustomerListHandler;
import server.model.customer.CustomerListHandlerManager;
import server.model.kitchenOrders.KitchenOrderListHandlerManager;
import server.model.kitchenOrders.KitchenOrdersListHandler;
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
import server.networking.kitchenOrders.KitchenOrdersServerManager;
import server.networking.login.CreateUserServerManager;
import server.networking.login.LoginServerManager;
import server.networking.menuItems.MenuItemServerManager;
import server.networking.orders.OrdersServerManager;
import server.networking.reservation.ReservationListServerManager;
import server.networking.tables.TablesServerManager;
import shared.networking.serverInterfaces.*;

import shared.utils.kitchenOrder.KitchenOrder;
import shared.utils.order.Order;

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

        KitchenOrdersListHandler kitchenOrdersListHandler = new KitchenOrderListHandlerManager();
        KitchenOrdersServer kitchenOrdersServer = new KitchenOrdersServerManager(kitchenOrdersListHandler);


        Server server = new ServerManager(loginServer, createAccountServer, tablesServerManager, customerListServer, chatServer, menuServer, orderServer, reservationServer, kitchenOrdersServer);
        server.startServer();


        ReservationDAO reservationDAO = new ReservationDAOManager();
        for (int i = 0; i < reservationDAO.getAllReservations().size(); i++) {
            System.out.println(reservationDAO.getAllReservations().get(i) + "\n");
            ;
        }

//        OrderDAO orderDAO = new OrderDAOManager();
//        for (int i = 0; i < orderDAO.getAllOrders().size(); i++) {
//            Order order = orderDAO.getOrder(10);
//            if (order != null) {
//                System.out.println(order.getOrderMenuItemsList());
//            } else {
//                System.out.println("Order with ID 6 not found");
//            }
//        }
//
        KitchenOrder kitchenOrder = new KitchenOrder("Table 1", "Customer 1", 1, 1, "Menu Item 1", "Preparing");
        System.out.println(kitchenOrder.getTableName() + " " + kitchenOrder.getCustomerName() + " " + kitchenOrder.getOrderId() + " " + kitchenOrder.getReservationID() + " " + kitchenOrder.getMenuItemName() + " " + kitchenOrder.getPreparationStatus());
//
//
//
//
////        MenuItemDAO menuItemDAO = new MenuItemDAOManager();
////        for(int i=0; i<menuItemDAO.getMenuItems().size(); i++){
////            System.out.println(menuItemDAO.getMenuItems().get(i).getMenuItemID() + " "+ menuItemDAO.getMenuItems().get(i)+"\n");;
////        }

    }
}
