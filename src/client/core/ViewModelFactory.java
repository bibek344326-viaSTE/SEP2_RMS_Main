package client.core;

import client.view.customer.*;
import client.view.kitchenstaff.KitchenStaffViewModel;
import client.view.staff.Customer.CreateNewCustomerViewModel;
import client.view.staff.Customer.StaffCustomerViewModel;
import client.view.staff.MenuItems.AddNewMenuItemsViewModel;
import client.view.staff.MenuItems.EditMenuItemsViewModel;
import client.view.staff.MenuItems.StaffMenuItemsViewModel;
import client.view.staff.Order.StaffOrderViewModel;
import client.view.staff.Table.StaffTableViewModel;
import client.view.staff.Table.UpdateTableViewModel;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class ViewModelFactory {
    private final ModelFactory modelFactory;
    private StaffTableViewModel tableViewModel;
    private UpdateTableViewModel updateTableViewModel;
    private ViewState viewState;
    private StaffMenuItemsViewModel menuViewModel;
    private StaffCustomerViewModel customerViewModel;
    private AddNewMenuItemsViewModel addNewMenuItemsViewModel;
    private CreateNewCustomerViewModel createNewCustomerViewModel;
    private EditMenuItemsViewModel editMenuItemsViewModel;
    private StaffOrderViewModel staffOrderViewModel;
    private CustomerLoginViewModel customerLoginViewModel;
    private CustomerViewMenuItemsViewModel customerViewMenuItemsViewModel;
    private CustomerViewOrderStatusViewModel customerViewOrderStatusViewModel;
    private KitchenStaffViewModel kitchenStaffViewModel;

    public ViewModelFactory(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
        this.viewState = new ViewState();
    }

    public StaffTableViewModel getTableViewModel() throws RemoteException {
        if (tableViewModel == null) {
            tableViewModel = new StaffTableViewModel(modelFactory, viewState);
        }
        return tableViewModel;
    }

    public UpdateTableViewModel getUpdateTableViewModel() throws RemoteException {
        if (updateTableViewModel == null) {
            updateTableViewModel = new UpdateTableViewModel(modelFactory, viewState);
        }
        return updateTableViewModel;
    }

    public StaffMenuItemsViewModel getMenuViewModel() throws RemoteException, SQLException {
        if (menuViewModel == null) {
            menuViewModel = new StaffMenuItemsViewModel(modelFactory, viewState);
        }
        return menuViewModel;
    }

    public StaffCustomerViewModel getCustomerViewModel() throws RemoteException, SQLException {
        if (customerViewModel == null) {
            customerViewModel = new StaffCustomerViewModel(modelFactory, viewState);
        }
        return customerViewModel;
    }


    public AddNewMenuItemsViewModel getAddNewMenuItemsViewModel() throws RemoteException {
        if (addNewMenuItemsViewModel == null) {
            addNewMenuItemsViewModel = new AddNewMenuItemsViewModel(modelFactory, viewState);
        }
        return addNewMenuItemsViewModel;
    }


    public CreateNewCustomerViewModel getCreateNewCustomerViewModel() throws RemoteException {
        if (createNewCustomerViewModel == null) {
            createNewCustomerViewModel = new CreateNewCustomerViewModel(modelFactory, viewState);
        }
        return createNewCustomerViewModel;
    }

    public EditMenuItemsViewModel getEditMenuItemsViewModel() throws RemoteException {
        if (editMenuItemsViewModel == null) {
            editMenuItemsViewModel = new EditMenuItemsViewModel(modelFactory, viewState);
        }
        return editMenuItemsViewModel;
    }

    public StaffOrderViewModel getStaffOrderViewModel() throws RemoteException {
        if (staffOrderViewModel == null) {
            staffOrderViewModel = new StaffOrderViewModel(modelFactory, viewState);
        }
        return staffOrderViewModel;
    }

    public CustomerLoginViewModel getCustomerLoginViewModel() throws RemoteException {
        if (customerLoginViewModel == null) {
            customerLoginViewModel = new CustomerLoginViewModel(modelFactory, viewState);
        }
        return customerLoginViewModel;
    }


    public CustomerViewMenuItemsViewModel getCustomerViewMenuItemsViewModel() throws RemoteException {
        if (customerViewMenuItemsViewModel == null) {
            customerViewMenuItemsViewModel = new CustomerViewMenuItemsViewModel(modelFactory, viewState);
        }
        return customerViewMenuItemsViewModel;
    }

    public CustomerViewOrderStatusViewModel getCustomerViewOrderStatusViewModel() throws RemoteException {
        if (customerViewOrderStatusViewModel == null) {
            customerViewOrderStatusViewModel = new CustomerViewOrderStatusViewModel(modelFactory, viewState);
        }
        return customerViewOrderStatusViewModel;
    }

    public KitchenStaffViewModel getKitchenStaffViewModel() throws RemoteException {
        if (kitchenStaffViewModel == null) {
            kitchenStaffViewModel = new KitchenStaffViewModel(modelFactory, viewState);
        }
        return kitchenStaffViewModel;
    }

}


