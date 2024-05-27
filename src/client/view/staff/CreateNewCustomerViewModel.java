package client.view.staff;

import client.core.ModelFactory;
import client.core.ViewState;
import client.model.customer.CustomerModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.utils.user.Customer;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class CreateNewCustomerViewModel {
    private final CustomerModel customerModel;
    private final ViewState viewState;
    private final StringProperty username;
    private final StringProperty password;
    private final StringProperty errorProperty;
    private final StringProperty headerProperty;

    public CreateNewCustomerViewModel(ModelFactory modelFactory, ViewState viewState) throws RemoteException {
        this.customerModel = modelFactory.getCustomerModel();
        this.viewState = viewState;
        this.username = new SimpleStringProperty();
        this.password = new SimpleStringProperty();
        this.errorProperty = new SimpleStringProperty();
        this.headerProperty = new SimpleStringProperty("Create New Customer");
    }

    public void reset() throws RemoteException, SQLException {
        if (viewState != null && viewState.getCustomerName() != null) {
            Customer customer = customerModel.getCustomers().stream()
                    .filter(c -> c.getUsername().equals(viewState.getCustomerName()))
                    .findFirst()
                    .orElse(null);
            if (customer != null) {
                username.set(customer.getUsername());
                password.set(customer.getPassword());
            }
        } else {
            // Reset username and password properties
            username.set("");
            password.set("");
        }
        // Reset error property
        errorProperty.set(null);
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public StringProperty errorProperty() {
        return errorProperty;
    }

    public StringProperty headerProperty() {
        return headerProperty;
    }

    public boolean createCustomer() {
        if (validateInput()) {
            try {
                Customer customer = new Customer(username.get(), password.get());
                customerModel.addCustomer(customer);
                return true;
            } catch (Exception e) {
                errorProperty.set("Error creating customer: " + e.getMessage());
            }
        }
        return false;
    }

    private boolean validateInput() {
        if (username.get() == null || username.get().isEmpty()) {
            errorProperty.set("Username cannot be empty");
            return false;
        }
        if (password.get() == null || password.get().isEmpty()) {
            errorProperty.set("Password cannot be empty");
            return false;
        }
        return true;
    }
}