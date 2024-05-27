package client.view.login;

import client.core.ViewModelFactory;
import client.view.ViewController;
import client.view.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;

public class LoginViewController implements ViewController {
    private ViewHandler viewHandler;
    private Region root;

    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler, Region root) {
        this.viewHandler = viewHandler;
        this.root = root;
    }

    /*@FXML
     private void LoginButtonClicked(ActionEvent actionEvent) {
         loginViewModel.login("Successfully logged in");
         viewHandler.openTableView();
     }*/

    @FXML
    private void customerButtonPressed() {
        viewHandler.openView("customerLogin");
    }

    @FXML
    private void staffMemberButtonPressed() {
        viewHandler.openView("staffLogin");
    }

    @FXML
    private void kitchenStaffButtonPressed() {
        viewHandler.openView("kitchenLogin");

    }
    public Region getRoot()
    {
        return root;
    }
}