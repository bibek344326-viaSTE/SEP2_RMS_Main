package shared.utils.user;

import java.io.Serializable;
import java.util.Objects;

public abstract class User implements Serializable {
    private String username;
    private String password;

    public User(String username, String password) {
        //validateUsername(username);
        //validatePassword(password);
        this.username = username;
        this.password = password;
    }

    public User(String username) {
        //validateUsername(username);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public abstract String getEmployeeType();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    private void validateUsername(String username) {
        if (username.length() < 4) {
            throw new IllegalStateException("Username should consist more than 3 characters");
        } else if (username.length() > 14) {
            throw new IllegalStateException("Username should consist less than 15 characters");
        }
        char[] chars = username.toCharArray();
        boolean hasDigits = false;
        for (char aChar : chars) {
            if (Character.isDigit(aChar)) {
                hasDigits = true;
                break;
            }
        }
        if (!hasDigits) {
            throw new IllegalStateException("Username should have at least one digit..");
        }
    }

    private void validatePassword(String password) {
        if (password.length() < 4) {
            throw new IllegalStateException("Password should consist more than 3 characters");
        } else if (password.length() > 14) {
            throw new IllegalStateException("Password should consist less than 15 characters");
        }
        char[] chars = password.toCharArray();
        boolean hasDigits = false;
        for (char aChar : chars) {
            if (Character.isDigit(aChar)) {
                hasDigits = true;
                break;
            }
        }
        if (!hasDigits) {
            throw new IllegalStateException("Password should have at least one digit..");
        }
    }

}
