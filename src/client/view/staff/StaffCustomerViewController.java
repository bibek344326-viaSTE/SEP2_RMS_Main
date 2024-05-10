package client.view.staff;

import client.core.ViewModelFactory;
import client.view.ViewController;
import client.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.scene.layout.Region;

public class StaffCustomerViewController implements ViewController {
    private Region root;
    private ViewModelFactory viewModelFactory;
    private ViewHandler viewHandler;
    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
    }
   /* private void  staffMemberButtonPressed(){
        loginViewModel.login("Staff");
    }*/

    public void addNewCustomer(ActionEvent actionEvent) {
    }

    public void editNewCustomer(ActionEvent actionEvent) {

    }

    public void viewCustomerDetails(ActionEvent actionEvent) {
    }

    public void onComboBoxSelection(ActionEvent actionEvent) {
    }

    public void logInAsStaff() {
        viewHandler.openStaffMemberView();
    }


}
