package client.core;

import client.networking.tables.TablesClient;
import client.view.staff.TableViewModel;

import java.rmi.RemoteException;

public class ViewModelFactory {

    private final ModelFactory modelFactory;
    private TableViewModel tableViewModel;
    private ViewState viewState;



    public ViewModelFactory(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
        this.viewState = new ViewState();
    }
    public TableViewModel getTableViewModel() throws RemoteException {
        if (tableViewModel  == null){
            tableViewModel = new TableViewModel(modelFactory, viewState);
        }
        return tableViewModel;
    }


}



