package client.view.staff;

import client.core.ViewModelFactory;
import client.view.ViewHandler;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;

public class AddNewTableController {
    private AddNewTableViewModel addNewTableViewModel;
    private Region root;
    private ViewHandler viewHandler;

    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler, Region root) throws RemoteException {
        this.viewHandler = viewHandler;
        this.root = root;
       this.addNewTableViewModel = viewModelFactory.getAddNewTableViewModel();
    }
    public Region getRoot()
    {
        return root;
    }
}
