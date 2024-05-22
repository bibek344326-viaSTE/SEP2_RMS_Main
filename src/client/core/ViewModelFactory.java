package client.core;

import client.view.staff.TableViewModel;
import client.view.staff.UpdateTableViewModel;

import java.rmi.RemoteException;

public class ViewModelFactory {
    private final ModelFactory modelFactory;
    private TableViewModel tableViewModel;
    private UpdateTableViewModel updateTableViewModel;
    private ViewState viewState;

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
}


