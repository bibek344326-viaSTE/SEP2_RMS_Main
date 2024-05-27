package client.view.customer;

import client.core.ModelFactory;
import client.core.ViewState;
import client.model.customer.CustomerModel;

public class CustomerTableNumberViewModel {
    private CustomerModel customerModel;
    private ViewState viewState;

    public CustomerTableNumberViewModel(ModelFactory modelFactory, ViewState viewState) {
        this.viewState = viewState;
        this.customerModel = modelFactory.getCustomerModel();
    }
}
