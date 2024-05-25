package client.view;

import client.core.ViewModelFactory;
import client.view.customer.CustomerLoginController;
import client.view.customer.CustomerTableNumberViewController;
import client.view.customer.CustomerViewMenuItemsController;
import client.view.customer.CustomerViewOrderStatusController;
import client.view.kitchenstaff.KitchenStaffLoginController;
import client.view.kitchenstaff.KitchenStaffViewController;
import client.view.login.LoginViewController;
import client.view.staff.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ViewHandler {
    private final ViewModelFactory viewModelFactory;
    private Stage primaryStage;
    private Scene currentScene;
    private CustomerTableNumberViewController customerTableNumberViewController;
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
    private AddNewCustomerController addNewCustomerController;
    private AddNewMenuItemsController addNewMenuItemsController;
    private AddNewTableController addNewTableController;
    private CreateNewCustomerController createNewCustomerController;
    private EditMenuItemsController editMenuItemsController;
    private StaffTableViewController staffTableViewController;

    public ViewHandler(ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.currentScene = new Scene(new Region(), 800, 600);
        openView("login");
    }

    public void openView(String id) {
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
                root = loadCustomerViewStaff("staff/StaffCustomerView.fxml");
                break;
            case "orderView":
                root = loadOrderView("staff/StaffOrderView.fxml");
                break;
            case "menuView":
                root = loadMenuView("staff/StaffMenuItems.fxml");
                break;
            case "addEditTable":
                root = loadAddEditTableView("staff/UpdateTableDetails.fxml");
                break;
            case "customerTableNumber":
                root = loadCustomerTableNumberView("customer/CustomerTableNumberView.fxml");
                break;
            case "customerViewMenuItems":
                root = loadCustomerViewMenuItems("customer/CustomerViewMenuItems.fxml");
                break;
            case "customerViewOrderStatus":
                root = loadCustomerViewOrderStatus("customer/CustomerViewOrderStatus.fxml");
                break;
            case "addNewCustomer":
                root = loadAddNewCustomerView("staff/AddNewCustomer.fxml");
                break;
            case "addNewMenuItems":
                root = loadAddNewMenuItemsView("staff/AddNewMenuItems.fxml");
                break;
            case "addNewTable":
                root = loadAddNewTableView("staff/AddNewTable.fxml");
                break;
            case "createNewCustomer":
                root = loadCreateNewCustomerView("staff/CreateNewCustomer.fxml");
                break;
            case "editMenuItems":
                root = loadEditMenuItemsView("staff/EditMenuItems.fxml");
                break;
            case "staffTable":
                root = loadStaffTableView("staff/StaffTablesView.fxml");
                break;
        }
        currentScene.setRoot(root);

        String title = "";
        if (root.getUserData() != null) {
            title += root.getUserData();
        }
        primaryStage.setTitle(title);
        primaryStage.setScene(currentScene);
        primaryStage.setWidth(root.getPrefWidth() > 800 ? root.getPrefWidth() : 800); // Ensure minimum width of 800
        primaryStage.setHeight(root.getPrefHeight() > 600 ? root.getPrefHeight() : 600); // Ensure minimum height of 600
        primaryStage.show();
    }

    private Region loadLoginView(String fxmlFile) {
        if (loginViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                loginViewController = loader.getController();
                loginViewController.init(viewModelFactory, this,root);
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

    private Region loadCustomerTableNumberView(String fxmlFile) {
        if (customerTableNumberViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                customerTableNumberViewController = loader.getController();
                customerTableNumberViewController.init(viewModelFactory, this,root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return customerTableNumberViewController.getRoot();
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

    private Region loadAddNewCustomerView(String fxmlFile) {
        if (addNewCustomerController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                addNewCustomerController = loader.getController();
                addNewCustomerController.init(viewModelFactory, this,root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return addNewCustomerController.getRoot();
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
        return addNewMenuItemsController.getRoot();
    }

    private Region loadAddNewTableView(String fxmlFile) {
        if (addNewTableController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                addNewTableController = loader.getController();
                addNewTableController.init(viewModelFactory, this,root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return addNewTableController.getRoot();
    }

    private Region loadCreateNewCustomerView(String fxmlFile) {
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