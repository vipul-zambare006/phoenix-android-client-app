package sg.edu.nus.iss.phoenix.user.android.controller;

/**
 * Created by monalisadebnth on 2/10/17.
 */

import android.content.Intent;
import android.util.Log;

import java.util.List;

import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.core.android.controller.MainController;
import sg.edu.nus.iss.phoenix.user.android.delegate.RetrievePresenterProducerDelegate;
import sg.edu.nus.iss.phoenix.user.android.ui.ReviewSelectPresenterProducerScreen;
import sg.edu.nus.iss.phoenix.user.entity.User;

public class ReviewSelectPresenterProducerController {
    // Tag for logging.
    private static final String TAG = ReviewSelectPresenterProducerController.class.getName();

    private ReviewSelectPresenterProducerScreen reviewSelectPresenterProducerScreen;
    private User userSelected = null;

    public void startUseCase() {
        userSelected = null;
        Intent intent = new Intent(MainController.getApp(), ReviewSelectPresenterProducerScreen.class);
        MainController.displayScreen(intent);
    }

    public void onDisplay(ReviewSelectPresenterProducerScreen reviewSelectScheduledProgramScreen) {
        this.reviewSelectPresenterProducerScreen = reviewSelectScheduledProgramScreen;
        new RetrievePresenterProducerDelegate(this).execute("presenterproducer");
    }

    public void scheduleRetrieved(List<User> presenterProducer) {
        reviewSelectPresenterProducerScreen.showPresenterProducer(presenterProducer);
    }

    public void SelectSchedule(User presenterProducer) {
        userSelected = presenterProducer;

        //Log.v(TAG, "Selected program slot : " + radioProgram.getRadioProgramName() + ".");
        // To call the base use case controller with the selected Scheduled program.
        // At present, call the MainController instead.
        ControlFactory.getMainController().selectedUser(userSelected);

    }


    public void selectCancel() {
        userSelected = null;
        Log.v(TAG, "Cancelled the selection of Presenter Producer.");
        // To call the base use case controller without selection;
        // At present, call the MainController instead.
        ControlFactory.getMainController().selectedUser(userSelected);
    }

    public void userRetrieved(List<User> user) {
        reviewSelectPresenterProducerScreen.showPresenterProducer(user);
    }
}
