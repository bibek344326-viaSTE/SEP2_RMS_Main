package client.view.customer;

import client.core.ModelFactory;
import client.core.ViewModelFactory;
import client.core.ViewState;
import client.model.customer.CustomerModel;

public class CustomerLoginViewModel {
    private CustomerModel customerModel;
    private ViewState viewState;

    public CustomerLoginViewModel(ModelFactory modelFactory, ViewState viewState) {
        this.customerModel = modelFactory.getCustomerModel();
        this.viewState = viewState;
    }
}
