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

    /**
     * This method will populate the presenter producer list on the screen by fetching the value Object from delegate.
     *
     * @param reviewSelectPresenterProducerScreen
     */

    public void onDisplay(ReviewSelectPresenterProducerScreen reviewSelectPresenterProducerScreen) {
        this.reviewSelectPresenterProducerScreen = reviewSelectPresenterProducerScreen;
        new RetrievePresenterProducerDelegate(this).execute("presenterproducer");
    }


    public void selectCancel() {
        userSelected = null;
        Log.v(TAG, "Cancelled the selection of Presenter Producer.");
        // To call the base use case controller without selection;
        // At present, call the MainController instead.
        ControlFactory.getMainController().selectedUser(userSelected);
    }

    /**
     * This method will provide the retrieved presenter producer value objects from the user Adapter
     * to bid it with the list view.
     *
     * @param user
     */


    public void userRetrieved(List<User> user) {
        reviewSelectPresenterProducerScreen.showPresenterProducer(user);
    }
}
