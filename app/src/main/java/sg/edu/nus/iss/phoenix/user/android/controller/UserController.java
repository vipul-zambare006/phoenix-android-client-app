package sg.edu.nus.iss.phoenix.user.android.controller;

import android.content.Intent;

import java.util.List;

import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.core.android.controller.MainController;
import sg.edu.nus.iss.phoenix.user.android.delegate.CreateUserDelegate;
import sg.edu.nus.iss.phoenix.user.android.delegate.DeleteUserDelegate;
import sg.edu.nus.iss.phoenix.user.android.delegate.RetrieveUserDelegate;
import sg.edu.nus.iss.phoenix.user.android.delegate.UpdateUserDelegate;
import sg.edu.nus.iss.phoenix.user.android.ui.MaintainUserScreen;
import sg.edu.nus.iss.phoenix.user.android.ui.UserScreen;
import sg.edu.nus.iss.phoenix.user.entity.User;

/**
 * Created by Govardhan on 19/9/2017.
 */

public class UserController {

    private MaintainUserScreen maintainUserScreen;
    private UserScreen userScreen;
    private User useredit = null;

    public void startUseCase() {
        useredit = null;
        Intent intent = new Intent(MainController.getApp(), UserScreen.class);
        MainController.displayScreen(intent);
    }

    public void selectCreateUser() {
        useredit = null;
        Intent intent = new Intent(MainController.getApp(), MaintainUserScreen.class);
        MainController.displayScreen(intent);
    }

    /**
     * This method will link the Create UI button with the Create User delegate.
     *
     * @param user
     */


    public void selectCreateUser(User user) {
        new CreateUserDelegate(this).execute(user);
    }

    public void userUpdated(boolean success) {
        // Go back to ProgramList screen with refreshed programs.
        startUseCase();
    }

    /**
     * This method will link the Edit UI button with the Update User Delegate.
     *
     * @param user
     */

    public void selectUpdateUser(User user) {
        new UpdateUserDelegate(this).execute(user);
    }

    /**
     * This method will link the Delete UI button with the Delete User delegate.
     *
     * @param user
     */

    public void selectDeleteUser(User user) {
        new DeleteUserDelegate(this).execute(user.getUserName());
    }

    /**
     * This method will link the Edit UI button with the Edit User delegate.
     *
     * @param user
     */

    public void selectEditUser(User user) {
        useredit = user;
        Intent intent = new Intent(MainController.getApp(), MaintainUserScreen.class);
        MainController.displayScreen(intent);
    }

    public void userCreated(boolean success) {
        // Go back to ProgramList screen with refreshed programs.
        startUseCase();
    }

    public void selectCancelCreateEditUser() {
        // Go back to ProgramList screen with refreshed programs.
        startUseCase();
    }

    /**
     * This method is used to verify if a new user is being created or is selected from the list view.
     *
     * @param maintainUserScreen
     */

    public void onDisplayUser(MaintainUserScreen maintainUserScreen) {
        this.maintainUserScreen = maintainUserScreen;
        if (useredit == null)
            maintainUserScreen.createUser();
        else
            maintainUserScreen.editUser(useredit);
    }

    /**
     * This method is used to verify if a new user is being created or is selected from the list view.
     *
     * @param userScreen
     */

    public void onDisplayUserList(UserScreen userScreen) {
        this.userScreen = userScreen;
        new RetrieveUserDelegate(this).execute("all");
    }

    public void userDeleted(boolean success) {
        // Go back to ProgramList screen with refreshed programs.
        startUseCase();
    }

    /**
     * This method will provide the retrieved presenter producer value objects from the user Adapter
     * to bid it with the list view.
     *
     * @param user
     */

    public void userRetrieved(List<User> user) {
        userScreen.showUsers(user);
    }


    public void maintainedUser() {
        ControlFactory.getMainController().maintainedProgram();
    }

}

