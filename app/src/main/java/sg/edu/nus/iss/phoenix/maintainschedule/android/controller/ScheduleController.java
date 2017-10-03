package sg.edu.nus.iss.phoenix.maintainschedule.android.controller;

import android.content.Intent;

import java.util.List;

import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.core.android.controller.MainController;
import sg.edu.nus.iss.phoenix.maintainschedule.android.delegate.CopyScheduleDelegate;
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
    private static final String TAG = ScheduleController.class.getName();
    private ScheduledProgramScreen scheduledProgramScreen;
    private ScheduleScreen scheduleScreen;
    private ProgramSlot pr2edit = null;
    private String copy_string = "copy";
    private String edit_string = "edit";

    public void startUseCase() {
        pr2edit = null;
        Intent intent = new Intent(MainController.getApp(), ScheduledProgramScreen.class);
        MainController.displayScreen(intent);
    }

    public void onDisplayScheduleList(ScheduledProgramScreen scheduledProgramScreen) {
        this.scheduledProgramScreen = scheduledProgramScreen;
        new RetrieveScheduleDelegate(this).execute("all");
        //new CopyScheduleDelegate(this).execute("copy");
    }

    public void schedulesRetrieved(List<ProgramSlot> programSlots) {
        scheduledProgramScreen.showSchedules(programSlots);
    }

    public void selectCreateSchedule() {
        pr2edit = null;
        Intent intent = new Intent(MainController.getApp(), ScheduleScreen.class);
        MainController.displayScreen(intent);
    }

    public void selectReviewProgram() {
        ControlFactory.getReviewSelectProgramController().startUseCase();
    }


    public void selectEditSchedule(ProgramSlot programSlot) {
        pr2edit = programSlot;
        Intent intent = new Intent(MainController.getApp(), ScheduleScreen.class);
        MainController.displayScreen(intent);
    }

    public void selectCopySchedule(ProgramSlot programSlot) {
        pr2edit = programSlot;
        Intent intent = new Intent(MainController.getApp(), ScheduleScreen.class);
        MainController.displayScreen(intent);
    }

    public void onDisplaySchedule(ScheduleScreen scheduleScreen) {
        this.scheduleScreen = scheduleScreen;
        if (pr2edit == null){
            scheduleScreen.createSchedule();
        } else if (pr2edit.getAction() != null && pr2edit.getAction().toString().toLowerCase() == edit_string)
        {
            scheduleScreen.editSchedule(pr2edit);
        } else if (pr2edit.getAction() != null && pr2edit.getAction().toString().toLowerCase() == copy_string)
        {
            scheduleScreen.copySchedule(pr2edit);
        }
    }

    public void selectUpdateSchedule(ProgramSlot pr) {
        new UpdateScheduleDelegate(this).execute(pr);
    }

    public void selectCopyupdateSchedule(ProgramSlot pr) {
        new CopyScheduleDelegate(this).execute(pr);
    }

    public void selectDeleteSchedule(ProgramSlot pr) {
        new DeleteScheduleDelegate(this).execute(pr);
    }

    public void scheduleDeleted(boolean success) {
        startUseCase();
    }

    public void scheduleUpdated(boolean success) {
        startUseCase();
    }

    public void selectCreateSchedule(ProgramSlot pr) {
        new CreateScheduleDelegate(this).execute(pr);
    }

    public void scheduleCreated(boolean success) {
        startUseCase();
    }

    public void selectCancelCreateEditSchedule() {
        startUseCase();
    }

    public void scheduleRetrieved(List<ProgramSlot> programSlots) {
        scheduledProgramScreen.showSchedules(programSlots);

    }

    public void maintainedProgram() {
        ControlFactory.getMainController().maintainedProgram();
    }
}
