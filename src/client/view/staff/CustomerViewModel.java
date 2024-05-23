package client.view.staff;

import client.core.ModelFactory;
import client.core.ViewState;
import client.model.customer.CustomerModel;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.utils.user.Customer;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public class CustomerViewModel {
    private final ObservableList<SimpleCustomerViewModel> customerList;
    private final CustomerModel customerModel;
    private final ObjectProperty<SimpleCustomerViewModel> selectedCustomerProperty;
    private final StringProperty errorLabel;
    private final ViewState viewState;

    public CustomerViewModel(ModelFactory modelFactory, ViewState viewState) throws RemoteException, SQLException {
        this.customerModel = modelFactory.getCustomerModel();
        this.customerList = FXCollections.observableArrayList();
        this.selectedCustomerProperty = new SimpleObjectProperty<>();
        this.errorLabel = new SimpleStringProperty();
        this.viewState = viewState;

        updateCustomerList();
    }

    public ObservableList<SimpleCustomerViewModel> getCustomerList() {
        return customerList;
    }

    public StringProperty getErrorProperty() {
        return errorLabel;
    }

    public void updateCustomerList() throws RemoteException, SQLException {
        customerList.clear();
        for(int i = 0; i< customerModel.getCustomers().size(); i++){
            customerList.add(new SimpleCustomerViewModel(customerModel.getCustomers().get(i)));
        }
    }

    public void setSelected(SimpleCustomerViewModel customer) {
        if (customer == null) {
            viewState.setCustomerName(null);
            viewState.setPassword(null);
        } else {
            this.selectedCustomerProperty.set(customer);
            viewState.setCustomerName(selectedCustomerProperty.get().getCustomernameProperty().get());
            viewState.setPassword(selectedCustomerProperty.get().getPasswordProperty().get());
        }
    }

    public void deselect() {
        setSelected(null);
    }

    public void add() {
        // Add a new customer
    }

    public void edit() {
        SimpleCustomerViewModel selectedCustomer = selectedCustomerProperty.get();
        if (selectedCustomer != null) {
            // Edit the selected customer
        } else {
            // Show error or handle appropriately
        }
    }



    public void updateCustomer(/* parameters */) throws RemoteException, SQLException {
        // Update customer details
    }


}