package client.view.staff;

import javafx.beans.property.*;
import shared.utils.user.Customer;

public class SimpleCustomerViewModel {
 private final StringProperty customernameProperty;
 private final StringProperty passwordProperty;

 public SimpleCustomerViewModel(Customer customer) {
     this.customernameProperty = new SimpleStringProperty(customer.getUsername());
     this.passwordProperty = new SimpleStringProperty(customer.getPassword());
 }
 public StringProperty getCustomernameProperty() {return customernameProperty;}
    public StringProperty getPasswordProperty() {return passwordProperty;}
}
