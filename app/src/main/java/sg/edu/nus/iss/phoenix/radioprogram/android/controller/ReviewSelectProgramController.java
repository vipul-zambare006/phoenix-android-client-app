package sg.edu.nus.iss.phoenix.radioprogram.android.controller;

import android.content.Intent;
import android.util.Log;

import java.util.List;

import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.core.android.controller.MainController;
import sg.edu.nus.iss.phoenix.radioprogram.android.delegate.RetrieveProgramsDelegate;
import sg.edu.nus.iss.phoenix.radioprogram.android.ui.ReviewSelectProgramScreen;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;

import static sg.edu.nus.iss.phoenix.core.android.controller.MainController.displayScreen;

public class ReviewSelectProgramController {
    // Tag for logging.
    private static final String TAG = ReviewSelectProgramController.class.getName();

    private ReviewSelectProgramScreen reviewSelectProgramScreen;
    private RadioProgram rpSelected = null;

    public void startUseCase() {
        rpSelected = null;

        Intent intent = new Intent(MainController.getApp(), ReviewSelectProgramScreen.class);
        displayScreen(intent);
    }

    public void onDisplay(ReviewSelectProgramScreen reviewSelectProgramScreen) {
        this.reviewSelectProgramScreen = reviewSelectProgramScreen;
        new RetrieveProgramsDelegate(this).execute("all");
    }

    public void displayReviewSelect() {

        ControlFactory.getReviewSelectProgramController().startUseCase();

    }

    public void programsRetrieved(List<RadioProgram> radioPrograms) {
        reviewSelectProgramScreen.showPrograms(radioPrograms);
    }

    public void selectProgram(RadioProgram radioProgram) {
        rpSelected = radioProgram;
        Log.v(TAG, "Selected radio program: " + radioProgram.getRadioProgramName() + ".");
        // To call the base use case controller with the selected radio program.
        // At present, call the MainController instead.

        ControlFactory.getMainController().selectedProgram(rpSelected);
    }

    public void selectCancel() {
        rpSelected = null;
        Log.v(TAG, "Cancelled the seleciton of radio program.");
        // To call the base use case controller without selection;
        // At present, call the MainController instead.
        ControlFactory.getMainController().selectedProgram(rpSelected);
    }
}
