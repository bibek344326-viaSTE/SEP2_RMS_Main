package shared.utils.user;

import java.io.Serializable;

public class StaffMember extends User implements Serializable {
    private final String userType;

    public StaffMember(String username, String password) {
        super(username, password);
        this.userType = Usertype.STAFFMEMBERS.name();
    }
    public StaffMember(String username) {
        super(username);
        this.userType = Usertype.STAFFMEMBERS.name();
    }



    @Override
    public String getEmployeeType() {
        return userType;
    }
}
