package client.view;

import client.core.ViewModelFactory;
import client.view.customer.CustomerLoginController;
import client.view.kitchenstaff.KitchenStaffLoginController;
import client.view.login.LoginViewController;
import client.view.staff.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ViewHandler {
    private final ViewModelFactory viewModelFactory;
    private Stage primaryStage;
    private Scene currentScene;

    private LoginViewController loginViewController;
    private CustomerLoginController customerLoginController;
    private StaffLoginController staffLoginController;
    private KitchenStaffLoginController kitchenStaffLoginController;;
    private ConnectionButtonViewController connectionButtonViewController;
    private StaffCustomerController staffCustomerController;
    private StaffOrderViewController staffOrderViewController;
    private StaffMenuViewController staffMenuViewController;
    private UpdateTableViewController updateTableViewController;

    public ViewHandler(ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.currentScene = new Scene(new Region());
        openLoginView();
    }

    public void openLoginView() {
        if (loginViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("login/RestaurantLoginView.fxml"));
                Region root = loader.load();
                loginViewController = loader.getController();
                loginViewController.init(viewModelFactory, this);
                currentScene.setRoot(root);
                primaryStage.setTitle("Login");
                primaryStage.setScene(currentScene);
                primaryStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void openCustomerLoginView() {
        if (customerLoginController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("customer/CustomerLogin.fxml"));
                Region root = loader.load();
                customerLoginController = loader.getController();
                customerLoginController.init(viewModelFactory, this);
                currentScene.setRoot(root);
                primaryStage.setTitle("Customer Login");
                primaryStage.setScene(currentScene);
                primaryStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }



    public void openStaffLoginView() {
        if (staffLoginController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("staff/StaffMemberLogin.fxml"));
                Region root = loader.load();
                staffLoginController = loader.getController();
                staffLoginController.init(viewModelFactory, this);
                currentScene.setRoot(root);
                primaryStage.setTitle("Staff Login");
                primaryStage.setScene(currentScene);
                primaryStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void openKitchenLoginView() {
        if (kitchenStaffLoginController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("kitchenstaff/KitchenStaffLogin.fxml"));
                Region root = loader.load();
                kitchenStaffLoginController = loader.getController();
                kitchenStaffLoginController.init(viewModelFactory, this);
                currentScene.setRoot(root);
                primaryStage.setTitle("Kitchen Staff Login");
                primaryStage.setScene(currentScene);
                primaryStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }





    public void openConnectionButtonsView() {
        if (connectionButtonViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("staff/ConnectionButtonsForStaff.fxml"));
                Region root = loader.load();
                connectionButtonViewController = loader.getController();
                connectionButtonViewController.init(viewModelFactory, this);
                currentScene.setRoot(root);
                primaryStage.setTitle("Connection Buttons");
                primaryStage.setScene(currentScene);
                primaryStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }



    public void openCustomerViewStaff() {
        if (staffCustomerController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("staff/StaffCustomerView.fxml"));
                Region root = loader.load();
                staffCustomerController = loader.getController();
                staffCustomerController.init(viewModelFactory, this);
                currentScene.setRoot(root);
                primaryStage.setTitle("Customer View Staff");
                primaryStage.setScene(currentScene);
                primaryStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void openOrderView() {
        if (staffOrderViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("staff/StaffOrderView.fxml"));
                Region root = loader.load();
                staffOrderViewController = loader.getController();
                staffOrderViewController.init(viewModelFactory, this);
                currentScene.setRoot(root);
                primaryStage.setTitle("Order View");
                primaryStage.setScene(currentScene);
                primaryStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void openMenuView() {
        if (staffMenuViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("staff/StaffMenuItems.fxml"));
                Region root = loader.load();
                staffMenuViewController = loader.getController();
                staffMenuViewController.init(viewModelFactory, this);
                currentScene.setRoot(root);
                primaryStage.setTitle("Menu View");
                primaryStage.setScene(currentScene);
                primaryStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void openAddEditTableView() {
        if (updateTableViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("staff/UpdateTableDetails.fxml"));
                Region root = loader.load();
                updateTableViewController = loader.getController();
                updateTableViewController.init(viewModelFactory, this);
                currentScene.setRoot(root);
                primaryStage.setTitle("Add/Edit Table");
                primaryStage.setScene(currentScene);
                primaryStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}