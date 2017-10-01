package sg.edu.nus.iss.phoenix.user.entity;

/**
 * Created by Govardhan on 19/9/2017.
 */

public class User {

    private String userName;
    private String userDescription;
    private String userRole;

    public User(String userName, String userDescription, String userRole) {
        this.userName = userName;
        this.userDescription = userDescription;
        this.userRole = userRole;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

}
