package client;

import client.core.ClientFactory;
import client.core.ModelFactory;
import client.core.ViewState;
import client.model.tables.TableModelManager;
import client.networking.tables.TablesClientManager;
import client.view.staff.TableViewModel;
import shared.utils.table.Table;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class RunClientApp {
    public static void main(String[] args) throws RemoteException {

        TablesClientManager manager = new TablesClientManager();
 //       TableModelManager tableManager = new TableModelManager(manager);
//        List<Table> tableList = tableManager.getTables();
//        for (Table table : tableList) {
//            System.out.println("Table name: "+table.getTableName()+"\n");
//        }

//        ClientFactory clientFactory = new ClientFactory();
//
        ViewState viewState = new ViewState();

        TableViewModel tableViewModel = new TableViewModel((new ModelFactory(new ClientFactory())), viewState);


        App.launch(App.class);



    }
}
