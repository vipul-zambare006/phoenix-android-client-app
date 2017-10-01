package sg.edu.nus.iss.phoenix.user.android.controller;

import android.content.Intent;

import java.util.List;

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

    public void selectCreateUser(User user) {
        new CreateUserDelegate(this).execute(user);
    }

    public void userUpdated(boolean success) {
        // Go back to ProgramList screen with refreshed programs.
        startUseCase();
    }

    public void selectUpdateUser(User user) {
        new UpdateUserDelegate(this).execute(user);
    }

    public void selectDeleteUser(User user) {
        new DeleteUserDelegate(this).execute(user.getUserName());
    }

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

    public void onDisplayUser(MaintainUserScreen maintainUserScreen) {
        this.maintainUserScreen = maintainUserScreen;
        if (useredit == null)
            maintainUserScreen.createUser();
        else
            maintainUserScreen.editUser(useredit);
    }

    public void onDisplayUserList(UserScreen userScreen) {
        this.userScreen = userScreen;
        new RetrieveUserDelegate(this).execute("getusers");
    }

    public void userDeleted(boolean success) {
        // Go back to ProgramList screen with refreshed programs.
        startUseCase();
    }

    public void userRetrieved(List<User> user) {
        userScreen.showUsers(user);
    }

}

