package client;

import client.core.ClientFactory;
import client.core.ModelFactory;
import client.core.ViewState;
import client.model.customer.CustomerModel;
import client.model.customer.CustomerModelManager;
import client.model.menuItem.MenuItemModel;
import client.model.menuItem.MenuItemModelManager;
import client.networking.customer.CustomerInfoClient;
import client.networking.customer.CustomerInfoClientManager;
import client.networking.menuItem.MenuItemClient;
import client.networking.menuItem.MenuItemClientManager;
import client.networking.tables.TablesClientManager;
import client.view.staff.Table.StaffTableViewModel;
import shared.utils.user.Customer;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public class RunClientApp {
    public static void main(String[] args) throws RemoteException, SQLException {

        TablesClientManager manager = new TablesClientManager();
        //       TableModelManager tableManager = new TableModelManager(manager);
//        List<Table> tableList = tableManager.getTables();
//        for (Table table : tableList) {
//            System.out.println("Table name: "+table.getTableName()+"\n");
//        }

//        ClientFactory clientFactory = new ClientFactory();
//
        ViewState viewState = new ViewState();

        StaffTableViewModel tableViewModel = new StaffTableViewModel((new ModelFactory(new ClientFactory())), viewState);


        MenuItemClient menuItemClient = new MenuItemClientManager();
        MenuItemModel menuItemModel = new MenuItemModelManager(menuItemClient);
        System.out.println(menuItemModel.getMenuItems().toString());

        CustomerInfoClient customerInfoClient = new CustomerInfoClientManager();
        CustomerModel customerModel = new CustomerModelManager(customerInfoClient);
        List<Customer> customers = customerModel.getCustomers();
        for (Customer customer : customers) {
            System.out.println(customer.toString());
        }

        App.launch(App.class);




    }
}
