package client.view;

import client.core.ViewModelFactory;
import client.view.staff.TableViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ViewHandler {
    private final ViewModelFactory viewModelFactory;
    private Stage stage;
    private Scene loginScene;
    private Scene customerScene;
    private Scene staffLoginScene;
    private Scene kitchenLoginScene;
    private Scene menuScene;
    private Scene kitchenScene;
    private Scene customerTableNumberScene;
    private Scene connectionButtons;
    private Scene customerViewStaff;
    private Scene tableViewStaff;
    private Scene orderViewStaff;
    private Scene menuViewStaff;





    public ViewHandler(ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
    }

    public void start() {
        stage = new Stage();
        openLogin();

    }


    public void openLogin() {
        if (loginScene == null) {
            try {
                Parent root = loadFXML("./login/RestaurantLoginView.fxml");
                loginScene = new Scene(root);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        stage.setTitle("Login");
        stage.setScene(loginScene);
        stage.show();
    }


    public void openCustomerLogInView() {
        if (customerScene == null) {
            try {
                Parent root = loadFXML("./customer/CustomerLogin.fxml");
                customerScene = new Scene(root);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        stage.setTitle("Login To Customer View");
        stage.setScene(customerScene);
        stage.show();
    }

    public void openCustomerTableNumberView() {
        if (customerTableNumberScene == null) {
            try {
                Parent root = loadFXML("./customer/CustomerTableNumberView.fxml");
                customerTableNumberScene = new Scene(root);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        stage.setTitle("Login To CustomerTableNumber View");
        stage.setScene(customerTableNumberScene);
        stage.show();
    }

    public void openStaffLogInView() {
        if (staffLoginScene == null) {
            try {
                Parent root = loadFXML("./staff/StaffMemberLogin.fxml");
                staffLoginScene = new Scene(root);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        stage.setTitle("Login To Staff Log In View");
        stage.setScene(staffLoginScene);
        stage.show();
    }



    public void openKitchenLoginView() {
        if (kitchenLoginScene == null) {
            try {
                Parent root = loadFXML("./kitchenstaff/KitchenStaffLogin.fxml");
                kitchenLoginScene = new Scene(root);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        stage.setTitle("Login To kitchen staff View");
        stage.setScene(kitchenLoginScene);
        stage.show();
    }

    public void openMenuItemsView() {
        if (menuScene == null) {
            try {
                Parent root = loadFXML("./customer/CustomerViewMenuItems.fxml");
                menuScene = new Scene(root);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        stage.setTitle("Login To Menu Items View");
        stage.setScene(menuScene);
        stage.show();
    }



    public void openKitchenView() {
        if (kitchenScene == null) {
            try {
                Parent root = loadFXML("./kitchenstaff/KitchenStaffView.fxml");
                kitchenScene = new Scene(root);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        stage.setTitle("Login To kitchen View");
        stage.setScene(kitchenScene);
        stage.show();
    }
    public void openConnectionButtons() {
        if (connectionButtons == null) {
            try {
                Parent root = loadFXML("./staff/ConnectionButtonsForStaff.fxml");
                connectionButtons = new Scene(root);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        stage.setTitle("Login To kitchen View");
        stage.setScene(connectionButtons);
        stage.show();
    }
    public void openStaffTableView() {
        if (tableViewStaff == null) {
            try {
                Parent root = loadFXML("./staff/StaffTablesView.fxml");
                tableViewStaff = new Scene(root);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        stage.setTitle("Login To table View");
        stage.setScene(tableViewStaff);
        stage.show();
    }
    public void openCustomerViewStaff() {
        if (customerViewStaff == null) {
            try {
                Parent root = loadFXML("./staff/StaffCustomerView.fxml");
                customerViewStaff = new Scene(root);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        stage.setTitle("Login To customer view");
        stage.setScene(customerViewStaff);
        stage.show();
    }
    public void openOrderView() {
        if (orderViewStaff == null) {
            try {
                Parent root = loadFXML("./staff/StaffOrderView.fxml");
                orderViewStaff = new Scene(root);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        stage.setTitle("Login To order view");
        stage.setScene(orderViewStaff);
        stage.show();
    }
    public void openMenuView() {
        if (menuViewStaff == null) {
            try {
                Parent root = loadFXML("./staff/StaffMenuItems.fxml");
                menuViewStaff = new Scene(root);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        stage.setTitle("Login To kitchen View");
        stage.setScene(menuViewStaff);
        stage.show();
    }


    private Parent loadFXML(String path) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        Parent root = loader.load();

        ViewController controller = loader.getController();
        controller.init(viewModelFactory, this);
        return root;
    }


}
