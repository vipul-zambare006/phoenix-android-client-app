package sg.edu.nus.iss.phoenix.user.entity;

/**
 * Created by Govardhan on 19/9/2017.
 */

public class User {

    private String id;
    private String userName;
    private String password;
    private String userRole;

    public User(String id, String userName,  String password, String userRole) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.userRole = userRole;
    }

    public User(String id, String userName, String userRole) {
        this.id = id;
        this.userName = userName;
        this.userRole = userRole;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

}
