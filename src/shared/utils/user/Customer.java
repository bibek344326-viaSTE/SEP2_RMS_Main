package shared.utils.user;

import java.io.Serializable;

public class Customer extends User implements Serializable {
    private final String userType;

    public Customer(String username, String password) {
        super(username, password);
        this.userType = Usertype.CUSTOMER.name();
    }
    public Customer(String username) {
        super(username);
        this.userType = Usertype.CUSTOMER.name();
    }
    public String getUserType() {
        return userType;
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public String getEmployeeType() {
        return userType;
    }
}
