package sg.edu.nus.iss.phoenix.maintainschedule.android.controller;

import android.content.Intent;
import android.util.Log;

import java.util.List;

import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.core.android.controller.MainController;
import sg.edu.nus.iss.phoenix.maintainschedule.android.delegate.RetrieveScheduleDelegate;
import sg.edu.nus.iss.phoenix.maintainschedule.android.ui.ReviewSelectScheduledProgramScreen;
import sg.edu.nus.iss.phoenix.maintainschedule.entity.ProgramSlot;

/**
 * Created by Gaurav on 25/9/2017.
 */

public class ReviewSelectScheduledController {
    // Tag for logging.
    private static final String TAG = ReviewSelectScheduledController.class.getName();

    private ReviewSelectScheduledProgramScreen reviewSelectScheduledProgramScreen;
    private ProgramSlot prSelected = null;

    public void startUseCase() {
        prSelected = null;
        Intent intent = new Intent(MainController.getApp(), ReviewSelectScheduledProgramScreen.class);
        MainController.displayScreen(intent);
    }

    public void onDisplay(ReviewSelectScheduledProgramScreen reviewSelectScheduledProgramScreen) {
        this.reviewSelectScheduledProgramScreen = reviewSelectScheduledProgramScreen;
        new RetrieveScheduleDelegate(this).execute("all");
    }

    public void scheduleRetrieved(List<ProgramSlot> programSlots) {
        reviewSelectScheduledProgramScreen.showPrograms(programSlots);
    }

    public void SelectSchedule(ProgramSlot programSlot) {
        prSelected = programSlot;

        //Log.v(TAG, "Selected program slot : " + radioProgram.getRadioProgramName() + ".");
        // To call the base use case controller with the selected Scheduled program.
        // At present, call the MainController instead.
        ControlFactory.getMainController().selectedSchedule(prSelected);

    }


    public void selectCancel() {
        prSelected = null;
        Log.v(TAG, "Cancelled the seleciton of Program Slot.");
        // To call the base use case controller without selection;
        // At present, call the MainController instead.
        ControlFactory.getMainController().selectedSchedule(prSelected);
    }
}
