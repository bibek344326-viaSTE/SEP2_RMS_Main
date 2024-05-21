package client.view.staff;

import client.core.ViewModelFactory;
import client.view.ViewController;
import client.view.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class StaffLoginController implements ViewController {
    private static final String STAFF_PASSWORD = "bestfood123";
    private Region root;
    private ViewModelFactory viewModelFactory;
    private ViewHandler viewHandler;

    @FXML
    private TextField passwordField;

    @FXML
    private Label errorLabel;

    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
    }

    @FXML
    public void logInAsStaff() {
        String password = passwordField.getText();
        if (password.isEmpty()) {
            errorLabel.setText("You need to fill in the password");
        } else if (password.equals(STAFF_PASSWORD)) {
            viewHandler.openConnectionButtons();
        } else {
            errorLabel.setText("Incorrect password");
        }
    }

    public void back(){
        viewHandler.openLogin();
    }
}



