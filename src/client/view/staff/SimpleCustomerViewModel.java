package client.view.staff;

import javafx.beans.property.*;
import shared.utils.user.Customer;

public class SimpleCustomerViewModel {
    private final StringProperty customerNameProperty;
    private final IntegerProperty numberOfSeatsProperty;
    private final StringProperty notesProperty;
    private final StringProperty tableNumberProperty;
    private final BooleanProperty TableStatus;

    public SimpleCustomerViewModel(Customer customer) {
        //this.customerNameProperty = new SimpleStringProperty(customer.getUsername());
        this.numberOfSeatsProperty = new SimpleIntegerProperty(customer.get);
    }
}
