package client.view.staff.Customer;

import client.core.ModelFactory;
import client.core.ViewState;
import client.model.customer.CustomerModel;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class StaffCustomerViewModel {
    private final ObservableList<SimpleCustomerViewModel> customerList;
    private final CustomerModel customerModel;
    private final ObjectProperty<SimpleCustomerViewModel> selectedCustomerProperty;
    private final StringProperty errorLabel;
    private final ViewState viewState;

    public StaffCustomerViewModel(ModelFactory modelFactory, ViewState viewState) throws RemoteException, SQLException {
        this.customerModel = modelFactory.getCustomerModel();
        this.customerList = FXCollections.observableArrayList();
        this.selectedCustomerProperty = new SimpleObjectProperty<>();
        this.errorLabel = new SimpleStringProperty();
        this.viewState = viewState;

        updateCustomerList();
    }

    public void clear(){
        errorLabel.set(null);
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

    public void addEdit() throws SQLException, RemoteException {
        viewState.setRemove(false);
        SimpleCustomerViewModel selectedCustomer = selectedCustomerProperty.get();
        if (selectedCustomer != null) {
            viewState.setCustomerName(selectedCustomer.getCustomernameProperty().get());
            viewState.setPassword(selectedCustomer.getPasswordProperty().get());
        } else {
            viewState.setCustomerName(null);
            viewState.setPassword(null);
        }
        updateCustomerList();
    }

    public void remove() throws SQLException, RemoteException {
        if (selectedCustomerProperty.get() != null) {
            customerModel.removeCustomer(selectedCustomerProperty.get().getCustomernameProperty().get());
            updateCustomerList();
            errorLabel.set(null); // Clear error message on success
        } else {
            errorLabel.set("You have to select a row"); // Set error message if no customer is selected
        }
    }
}