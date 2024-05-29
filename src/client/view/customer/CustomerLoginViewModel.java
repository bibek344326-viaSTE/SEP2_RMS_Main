package client.view.customer;

import client.core.ModelFactory;
import client.core.ViewModelFactory;
import client.core.ViewState;
import client.model.customer.CustomerModel;
import client.model.login.LoginModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.utils.Request;
import shared.utils.user.User;

public class CustomerLoginViewModel {
    private StringProperty errorLogin, usernameLogin, passwordLogin;
    private BooleanProperty signInScene;
    private LoginModel loginModel;

    private ViewState viewState;


    public CustomerLoginViewModel(ModelFactory modelFactory, ViewState viewState) {
        this.loginModel = modelFactory.getLoginModel();
        initializeAllProperties();
        signInScene.setValue(true);

    }

    private void initializeAllProperties() {
        errorLogin = new SimpleStringProperty();
        usernameLogin = new SimpleStringProperty();
        passwordLogin = new SimpleStringProperty();
        signInScene = new SimpleBooleanProperty();
    }

    public StringProperty getErrorLogin() {
        return errorLogin;
    }

    public StringProperty getUsernameLogin() {
        return usernameLogin;
    }

    public StringProperty getPasswordLogin() {
        return passwordLogin;
    }

    public BooleanProperty getSignInScene() {
        return signInScene;
    }

    public String login() {
        //  System.out.println(usernameLogin.get());
        if (usernameLogin.get() == null || usernameLogin.get().isEmpty()) {
            errorLogin.set("Username cannot be empty !!");
            return "";
        } else if (passwordLogin.get() == null || passwordLogin.get().isEmpty()) {
            errorLogin.set("Password cannot be empty !!");
            return "";
        } else {
            Request temp = loginModel.login(usernameLogin.get(), passwordLogin.get());
            if (temp.getObject() != null) {
                loginModel.setUsername(((User) temp.getObject()).getUsername());
                loginModel.setUserType(temp.getType());
            }
            errorLogin.set(temp.getType());
            return temp.getType();
        }
    }

    private void clearAll() {
        usernameLogin.set(null);
        passwordLogin.set(null);
    }
}

