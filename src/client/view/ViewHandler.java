package client.view;

import client.core.ViewModelFactory;
import client.view.customer.CustomerLoginController;
import client.view.customer.CustomerViewMenuItemsController;
import client.view.customer.CustomerViewOrderStatusController;
import client.view.kitchenstaff.KitchenStaffLoginController;
import client.view.kitchenstaff.KitchenStaffViewController;
import client.view.login.LoginViewController;
import client.view.staff.*;
import client.view.staff.Customer.CreateNewCustomerController;
import client.view.staff.Customer.StaffCustomerViewController;
import client.view.staff.MenuItems.AddNewMenuItemsController;
import client.view.staff.MenuItems.EditMenuItemsController;
import client.view.staff.MenuItems.StaffMenuItemsViewController;
import client.view.staff.Order.StaffOrderViewController;
import client.view.staff.Table.StaffTableViewController;
import client.view.staff.Table.UpdateTableViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class ViewHandler {
    private final ViewModelFactory viewModelFactory;
    private Stage primaryStage;
    private Scene currentScene;
    private CustomerViewMenuItemsController customerViewMenuItemsController;
    private CustomerViewOrderStatusController customerViewOrderStatusController;
    private LoginViewController loginViewController;
    private CustomerLoginController customerLoginController;
    private StaffMemberLoginController staffLoginController;
    private KitchenStaffLoginController kitchenStaffLoginController;
    private KitchenStaffViewController kitchenStaffViewController;
    private ConnectionButtonViewController connectionButtonViewController;
    private StaffCustomerViewController staffCustomerController;
    private StaffOrderViewController staffOrderViewController;
    private StaffMenuItemsViewController staffMenuViewController;
    private UpdateTableViewController updateTableViewController;
    private AddNewMenuItemsController addNewMenuItemsController;
    private CreateNewCustomerController createNewCustomerController;
    private EditMenuItemsController editMenuItemsController;
    private StaffTableViewController staffTableViewController;

    public ViewHandler(ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
    }

    public void start(Stage primaryStage) throws SQLException, RemoteException {
        this.primaryStage = primaryStage;
        this.currentScene = new Scene(new Region(), 800, 600);
        openView("login");
    }

    public void openView(String id) throws SQLException, RemoteException {
        Region root = null;
        switch (id) {
            case "login":
                root = loadLoginView("login/RestaurantLoginView.fxml");
                break;
            case "customerLogin":
                root = loadCustomerLoginView("customer/CustomerLogin.fxml");
                break;
            case "staffLogin":
                root = loadStaffLoginView("staff/StaffMemberLogin.fxml");
                break;
            case "kitchenLogin":
                root = loadKitchenLoginView("kitchenstaff/KitchenStaffLogin.fxml");
                break;
            case "kitchenStaffView":
                root = loadKitchenStaffView("kitchenstaff/KitchenStaffView.fxml");
                break;
            case "connectionButtons":
                root = loadConnectionButtonsView("staff/ConnectionButtonsForStaff.fxml");
                break;
            case "customerViewStaff":
                root = loadCustomerViewStaff("staff/Customer/StaffCustomerView.fxml");
                break;
            case "orderView":
                root = loadOrderView("staff/Order/StaffOrderView.fxml");
                break;
            case "menuView":
                root = loadMenuView("staff/MenuItems/StaffMenuItems.fxml");
                break;
            case "addEditTable":
                root = loadAddEditTableView("staff/Table/UpdateTableDetails.fxml");
                break;
            case "customerViewMenuItems":
                root = loadCustomerViewMenuItems("customer/CustomerViewMenuItems.fxml");
                break;
            case "customerViewOrderStatus":
                root = loadCustomerViewOrderStatus("customer/CustomerViewOrderStatus.fxml");
                break;
            case "addNewMenuItems":
                root = loadAddNewMenuItemsView("staff/MenuItems/AddNewMenuItems.fxml");
                break;
            case "createNewCustomer":
                root = loadCreateNewCustomerView("staff/Customer/CreateNewCustomer.fxml");
                break;
            case "editMenuItems":
                root = loadEditMenuItemsView("staff/MenuItems/EditMenuItems.fxml");
                break;
            case "staffTable":
                root = loadStaffTableView("staff/Table/StaffTableView.fxml");
                break;
            default:
                System.out.println("Invalid view id: " + id);
                return;
        }

        if (root == null) {
            System.out.println("Failed to load view for id: " + id);
            return;
        }

        currentScene.setRoot(root);

        String title = "";
        if (root.getUserData() != null) {
            title += root.getUserData();
        }
        primaryStage.setTitle(title);
        primaryStage.setScene(currentScene);
        primaryStage.setWidth(root.getPrefWidth()); // Ensure minimum width of 800
        primaryStage.setHeight(root.getPrefHeight()+30); // Ensure minimum height of 600
        primaryStage.show();
    }

    private Region loadLoginView(String fxmlFile) {
        if (loginViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                loginViewController = loader.getController();
                loginViewController.init(viewModelFactory, this, root);
                return root;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return loginViewController.getRoot();
    }


    private Region loadCustomerLoginView(String fxmlFile) {
        if (customerLoginController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                customerLoginController = loader.getController();
                customerLoginController.init(viewModelFactory, this,root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return customerLoginController.getRoot();
    }

    private Region loadStaffLoginView(String fxmlFile) {
        if (staffLoginController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                staffLoginController = loader.getController();
                staffLoginController.init(viewModelFactory, this,root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return staffLoginController.getRoot();
    }

    private Region loadKitchenLoginView(String fxmlFile) {
        if (kitchenStaffLoginController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                kitchenStaffLoginController = loader.getController();
                kitchenStaffLoginController.init(viewModelFactory, this,root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return kitchenStaffLoginController.getRoot();
    }

    private Region loadConnectionButtonsView(String fxmlFile) {
        if (connectionButtonViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                connectionButtonViewController = loader.getController();
                connectionButtonViewController.init(viewModelFactory, this,root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return connectionButtonViewController.getRoot();
    }

    private Region loadCustomerViewStaff(String fxmlFile) {
        if (staffCustomerController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                staffCustomerController = loader.getController();
                staffCustomerController.init(viewModelFactory, this,root);
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            staffCustomerController.reset();
        }
        return staffCustomerController.getRoot();
    }

    private Region loadOrderView(String fxmlFile) {
        if (staffOrderViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                staffOrderViewController = loader.getController();
                staffOrderViewController.init(viewModelFactory, this,root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return staffOrderViewController.getRoot();
    }

    private Region loadMenuView(String fxmlFile) {
        if (staffMenuViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                staffMenuViewController = loader.getController();
                staffMenuViewController.init(viewModelFactory, this,root);
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            staffMenuViewController.reset();
        }

        return staffMenuViewController.getRoot();
    }

    private Region loadAddEditTableView(String fxmlFile) {
        if (updateTableViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                updateTableViewController = loader.getController();
                updateTableViewController.init(viewModelFactory, this,root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            updateTableViewController.reset();
        }
        return updateTableViewController.getRoot();
    }



    private Region loadCustomerViewMenuItems(String fxmlFile) {
        if (customerViewMenuItemsController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                customerViewMenuItemsController = loader.getController();
                customerViewMenuItemsController.init(viewModelFactory, this,root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return customerViewMenuItemsController.getRoot();
    }

    private Region loadCustomerViewOrderStatus(String fxmlFile) {
        if (customerViewOrderStatusController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                customerViewOrderStatusController = loader.getController();
                customerViewOrderStatusController.init(viewModelFactory, this,root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return customerViewOrderStatusController.getRoot();
    }



    private Region loadAddNewMenuItemsView(String fxmlFile) {
        if (addNewMenuItemsController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                addNewMenuItemsController = loader.getController();
                addNewMenuItemsController.init(viewModelFactory, this,root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            addNewMenuItemsController.reset();
        }
        return addNewMenuItemsController.getRoot();
    }


    private Region loadCreateNewCustomerView(String fxmlFile) throws SQLException, RemoteException {
        if (createNewCustomerController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                createNewCustomerController = loader.getController();
                createNewCustomerController.init(viewModelFactory, this,root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            createNewCustomerController.reset();
        }
        return createNewCustomerController.getRoot();
    }

    private Region loadEditMenuItemsView(String fxmlFile) {
        if (editMenuItemsController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                editMenuItemsController = loader.getController();
                editMenuItemsController.init(viewModelFactory, this,root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return editMenuItemsController.getRoot();
    }

    private Region loadStaffTableView(String fxmlFile) {
        if (staffTableViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                staffTableViewController = loader.getController();
                staffTableViewController.init(viewModelFactory, this,root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            staffTableViewController.reset();
        }
        return staffTableViewController.getRoot();
    }
    private Region loadKitchenStaffView(String fxmlFile) {
        if (kitchenStaffViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                kitchenStaffViewController = loader.getController();
                kitchenStaffViewController.init(viewModelFactory, this,root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return kitchenStaffViewController.getRoot();
    }
}