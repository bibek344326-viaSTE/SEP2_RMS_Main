package client.view.login;


import client.core.ModelFactory;
import client.model.login.LoginModel;

public class LogInViewModel {

    private final LoginModel loginModel;

    public LogInViewModel(ModelFactory modelFactory) {
        this.loginModel = modelFactory.getLoginModel();
    }
    public void login(String username, String password) {
        loginModel.login(username, password);
    }
}
