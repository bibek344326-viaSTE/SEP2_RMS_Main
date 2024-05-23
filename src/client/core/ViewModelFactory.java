package client.core;

import client.view.staff.MenuViewModel;
import client.view.staff.TableViewModel;
import client.view.staff.UpdateTableViewModel;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class ViewModelFactory {
    private final ModelFactory modelFactory;
    private TableViewModel tableViewModel;
    private UpdateTableViewModel updateTableViewModel;
    private ViewState viewState;
    private MenuViewModel menuViewModel;

    public ViewModelFactory(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
        this.viewState = new ViewState();
    }

    public TableViewModel getTableViewModel() throws RemoteException {
        if (tableViewModel == null) {
            tableViewModel = new TableViewModel(modelFactory, viewState);
        }
        return tableViewModel;
    }

    public UpdateTableViewModel getUpdateTableViewModel() throws RemoteException {
        if (updateTableViewModel == null) {
            updateTableViewModel = new UpdateTableViewModel(modelFactory, viewState);
        }
        return updateTableViewModel;
    }
    public MenuViewModel getMenuViewModel() throws RemoteException, SQLException {
        if (menuViewModel== null) {
            menuViewModel = new MenuViewModel(modelFactory, viewState);
        }
        return menuViewModel;
    }
}


