package client.view.login;

import client.core.ViewModelFactory;
import client.view.ViewController;
import client.view.ViewHandler;
import javafx.fxml.FXML;

public class LoginViewController implements ViewController {
    private ViewHandler viewHandler;

    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;

    }

    /*@FXML
     private void LoginButtonClicked(ActionEvent actionEvent) {
         loginViewModel.login("Successfully logged in");
         viewHandler.openTableView();
     }*/

    @FXML
    private void customerButtonPressed() {
        viewHandler.openCustomerLogInView();
    }

    @FXML
    private void staffMemberButtonPressed() {
        viewHandler.openStaffLogInView();
    }

    @FXML
    private void kitchenStaffButtonPressed() {
        viewHandler.openKitchenLoginView();

    }
}