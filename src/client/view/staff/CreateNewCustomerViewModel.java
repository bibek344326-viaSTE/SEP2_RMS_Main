package client.view.staff;

import client.core.ModelFactory;
import client.core.ViewState;
import client.model.customer.CustomerModel;

public class CreateNewCustomerViewModel {
    private CustomerModel customerModel;
    private ViewState viewState;

    public CreateNewCustomerViewModel(ModelFactory modelFactory, ViewState viewState) {
        this.customerModel = modelFactory.getCustomerModel();
        this.viewState = viewState;
    }
}
