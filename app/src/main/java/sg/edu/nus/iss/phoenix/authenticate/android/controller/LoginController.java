package sg.edu.nus.iss.phoenix.authenticate.android.controller;

import android.content.Intent;

import sg.edu.nus.iss.phoenix.authenticate.android.delegate.LoginDelegate;
import sg.edu.nus.iss.phoenix.authenticate.android.ui.LoginScreen;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.core.android.controller.MainController;

public class LoginController {
    // Tag for logging.
    private static final String TAG = LoginController.class.getName();

    private LoginScreen loginScreen;

    /**
     * load-method. This will load valueObject contents from database using
     * Primary-Key as identifier. Upper layer should use this so that
     * valueObject instance is created and only primary-key should be specified.
     * Then call this method to complete other persistent information. This
     * method will overwrite all other fields except primary-key and possible
     * runtime variables. If load can not find matching row, NotFoundException
     * will be thrown.
     *
     * @param loginScreen
     */
    public void onDisplay(LoginScreen loginScreen) {
        this.loginScreen = loginScreen;
    }

    public void login(String userName, String password) {
        loginScreen.showLoadingIndicator();
        new LoginDelegate(this).execute(userName, password);
    }

    public void loggedIn(boolean success, String username) {
        loginScreen.hideLoadingIndicator();
        if (!success) {
            loginScreen.showErrorMessage();
            return;
        }

        ControlFactory.getMainController().startUseCase(username);
    }

    public void logout() {
        Intent intent = new Intent(MainController.getApp(), LoginScreen.class);
        MainController.displayScreen(intent);
    }
}
