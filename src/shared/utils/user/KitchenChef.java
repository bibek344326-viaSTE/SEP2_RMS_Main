package shared.utils.user;

import java.io.Serializable;

public class KitchenChef extends User implements Serializable {
    private final String userType;

    public KitchenChef(String username, String password) {
        super(username, password);
        this.userType = Usertype.KITCHENCHEF.name();
    }

    public KitchenChef(String username) {
        super(username);
        this.userType = Usertype.KITCHENCHEF.name();
    }

    @Override
    public String getEmployeeType() {
        return userType;
    }
}
