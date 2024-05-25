package client.view.customer;

import client.core.ModelFactory;
import client.core.ViewState;
import client.model.customer.CustomerModel;

public class CustomerViewMenuItemsViewModel {
    private CustomerModel customerModel;
    private ViewState viewState;

    public CustomerViewMenuItemsViewModel(ModelFactory modelFactory, ViewState viewState) {
        this.customerModel = modelFactory.getCustomerModel();
        this.viewState = viewState;
    }
}
