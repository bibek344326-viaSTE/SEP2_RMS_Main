package client.view.staff;

import client.core.ModelFactory;
import client.core.ViewState;
import client.model.tables.TableModel;

import java.rmi.RemoteException;

public class AddNewTableViewModel {
    private TableModel tableModel;
    private ViewState viewState;

    public AddNewTableViewModel(ModelFactory modelFactory, ViewState viewState) throws RemoteException {
        tableModel = modelFactory.getTableModel();
        this.viewState = viewState;
    }
}
