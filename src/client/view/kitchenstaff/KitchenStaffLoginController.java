package client.view.kitchenstaff;

import client.core.ViewModelFactory;
import client.view.ViewController;
import client.view.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class KitchenStaffLoginController implements ViewController {
    private ViewHandler viewHandler;
    private ViewModelFactory viewModelFactory;
    private static final String KITCHEN_PASSWORD = "bestfood123";
    private Region root;

    @FXML
    private TextField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    private Button logInButton;

    @FXML
    private Button backButton;

    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler,Region root) {
        this.viewModelFactory = viewModelFactory;
        this.viewHandler = viewHandler;
        this.root = root;
    }

    @FXML
    public void logInAsKitchenStaff() {
        String password = passwordField.getText();
        if (password.isEmpty()) {
            errorLabel.setText("You need to fill in the password");
        } else if (password.equals(KITCHEN_PASSWORD)) {
            viewHandler.openView("kitchenStaffView");
        } else {
            errorLabel.setText("Incorrect password");
        }
    }

    @FXML
    public void back() {
        viewHandler.openView("login");
    }
    public Region getRoot()
    {
        return root;
    }
}