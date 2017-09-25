package sg.edu.nus.iss.phoenix.maintainschedule.android.controller;

import android.content.Intent;

import java.util.List;

import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.core.android.controller.MainController;
import sg.edu.nus.iss.phoenix.maintainschedule.android.delegate.CreateScheduleDelegate;
import sg.edu.nus.iss.phoenix.maintainschedule.android.delegate.DeleteScheduleDelegate;
import sg.edu.nus.iss.phoenix.maintainschedule.android.delegate.RetrieveScheduleDelegate;
import sg.edu.nus.iss.phoenix.maintainschedule.android.delegate.UpdateScheduleDelegate;
import sg.edu.nus.iss.phoenix.maintainschedule.android.ui.ScheduleScreen;
import sg.edu.nus.iss.phoenix.maintainschedule.android.ui.ScheduledProgramScreen;
import sg.edu.nus.iss.phoenix.maintainschedule.entity.ProgramSlot;

/**
 * Created by Gaurav on 17-09-2017.
 */

public class ScheduleController {

    //Tag for Logging
    private static final String TAG = ScheduleController.class.getName();
    private ScheduledProgramScreen scheduledProgramScreen;
    private ScheduleScreen scheduleScreen;
    private ProgramSlot pr2edit = null;

    public void startUseCase() {
        pr2edit = null;
        Intent intent = new Intent(MainController.getApp(), ScheduledProgramScreen.class);
        MainController.displayScreen(intent);
    }

    public void onDisplayScheduleList(ScheduledProgramScreen scheduledProgramScreen) {
        this.scheduledProgramScreen = scheduledProgramScreen;
        new RetrieveScheduleDelegate(this).execute("all");
    }

    public void schedulesRetrieved(List<ProgramSlot> programSlots) {
        scheduledProgramScreen.showSchedules(programSlots);
    }


    public void selectCreateSchedule() {
        pr2edit = null;
        Intent intent = new Intent(MainController.getApp(), ScheduledProgramScreen.class);
        MainController.displayScreen(intent);
    }

    public void selectEditSchedule(ProgramSlot programSlot) {
        pr2edit = programSlot;
        //  Log.v(TAG, "Editing Schedule: " + programSlot.getSchedule() + "...");


        Intent intent = new Intent(MainController.getApp(), ScheduleScreen.class);
/*        Bundle b = new Bundle();
        b.putString("Name", radioProgram.getRadioProgramName());
        b.putString("Description", radioProgram.getRadioProgramDescription());
        b.putString("Duration", radioProgram.getRadioProgramDuration());
        intent.putExtras(b);
*/
        MainController.displayScreen(intent);
    }

    public void onDisplaySchedule(ScheduleScreen scheduleScreen) {
        this.scheduleScreen = scheduleScreen;

        if (pr2edit == null)
            scheduleScreen.createSchedule();
        else
            scheduleScreen.editSchedule(pr2edit);
    }

    public void selectUpdateSchedule(ProgramSlot pr) {
        new UpdateScheduleDelegate(this).execute(pr);
    }

    public void selectDeleteSchedule(ProgramSlot pr) {
        new DeleteScheduleDelegate(this).execute(pr.getRadioProgramName());
    }

    public void scheduleDeleted(boolean success) {
        // Go back to Scheduled Program screen with refreshed program slots.
        startUseCase();
    }

    public void scheduleUpdated(boolean success) {
        // Go back to Scheduled Program screen with refreshed program slots.
        startUseCase();
    }

    public void selectCreateSchedule(ProgramSlot pr) {
        new CreateScheduleDelegate(this).execute(pr);
    }

    public void scheduleCreated(boolean success) {
        // Go back to Scheduled Program screen with refreshed program slots.
        startUseCase();
    }

    public void selectCancelCreateEditSchedule() {
        //  // Go back to Scheduled Program screen with refreshed program slots.
        startUseCase();
    }

    public void scheduleRetrieved(List<ProgramSlot> programSlots) {
        scheduledProgramScreen.showSchedules(programSlots);

    }

    public void maintainedProgram() {
        ControlFactory.getMainController().maintainedProgram();
    }
}
