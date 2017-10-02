package sg.edu.nus.iss.phoenix.maintainschedule.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.maintainschedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.radioprogram.android.ui.ReviewSelectProgramScreen;

/**
 * Created by Gaurav on 26-09-2017.
 */

public class ScheduleScreen extends AppCompatActivity {
    // Tag for logging
    private static final String TAG = ScheduleScreen.class.getName();
    String duration = null;
    private Button radio_program, presenter_producer;
    private EditText mdateofprogram;
    private EditText mduration, starttime, assignedby;
    private TextView rpTextView, producerTextView, PresenterTextView;
    private ProgramSlot pr2edit = null;

    // KeyListener mPSNameEDitTextKeyListner = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maintain_schedule);
        rpTextView = (TextView) findViewById(R.id.maintain_schedule_selected_programName_text_view);
        producerTextView = (TextView) findViewById(R.id.maintain_schedule_selected_producer_text_view);
        PresenterTextView = (TextView) findViewById(R.id.maintain_schedule_selected_Presenter_text_view);
        radio_program = (Button) findViewById(R.id.programName);
        presenter_producer = (Button) findViewById(R.id.presenter_producer);
        mdateofprogram = (EditText) findViewById(R.id.maintain_schedule_date_text_view);
        mduration = (EditText) findViewById(R.id.maintain_duration_time_text_view);
        starttime = (EditText) findViewById(R.id.maintain_start_time_text_view);
        assignedby = (EditText) findViewById(R.id.maintain_AssignedBy_text_view);

        // rpTextView.setText(radioProgramName);

        radio_program.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent input = new Intent(v.getContext(), ReviewSelectProgramScreen.class);
                startActivityForResult(input, 1);
            }
        });

        /*presenter_producer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
            }
        });*/
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String radioProgramName = data.getStringExtra("radioProgramName");
                duration = data.getStringExtra("duration");
                rpTextView.setText(radioProgramName);


            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    } //onActivityResult

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        ControlFactory.getScheduleController().onDisplaySchedule(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    /**
     * This method is called after invalidateOptionsMenu(), so that the
     * menu can be updated (some menu items can be hidden or made visible).
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        // If this is a new program slot , hide the "Delete" menu item.
        if (pr2edit == null) {
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:

                // Save  ProgramSlot .
                if (pr2edit == null) {
                    // Newly created.
                    Log.v(TAG, "Saving Program Slot" + rpTextView.getText().toString() + "...");

                    ProgramSlot ps = new ProgramSlot(rpTextView.getText().toString(), PresenterTextView.getText().toString(), producerTextView.getText().toString(), assignedby.getText().toString(), mduration.getText().toString(), starttime.getText().toString(), mdateofprogram.getText().toString());
                    ControlFactory.getScheduleController().selectCreateSchedule(ps);

                } else { // Edited.
                    //TODO Change the below Log Statement

                    //Log.v(TAG, "Saving radio program " + rp2edit.getRadioProgramName() + "...");
                    //rp2edit.setRadioProgramDescription(mRPDescEditText.getText().toString());
                    //rp2edit.setRadioProgramDuration(mDurationEditText.getText().toString());

                    ControlFactory.getScheduleController().selectUpdateSchedule(pr2edit);
                }
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                //TODO Change the below log Statement.

                //Log.v(TAG, "Deleting radio program " + rp2edit.getRadioProgramName() + "...");
                ControlFactory.getScheduleController().selectDeleteSchedule(pr2edit);
                return true;
            // Respond to a click on the "Cancel" menu option
            case R.id.action_cancel:

                Log.v(TAG, "Canceling creating/editing Program slot ...");
                ControlFactory.getScheduleController().selectCancelCreateEditSchedule();
                return true;

            case R.id.action_copy:

                Log.v(TAG, "Copying Program slot ...");

                //ControlFactory.getScheduleController().selectCancelCreateEditSchedule();

                return true;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        Log.v(TAG, "Canceling creating/editing Program slot ...");
        ControlFactory.getScheduleController().selectCancelCreateEditSchedule();
    }

    public void createSchedule() {
        this.pr2edit = null;
        // TODO Change the Following to perform Create Operation.

         /*mRPNameEditText.setText("", TextView.BufferType.EDITABLE);
        mRPDescEditText.setText("", TextView.BufferType.EDITABLE);
        mDurationEditText.setText("", TextView.BufferType.EDITABLE);
        mRPNameEditText.setKeyListener(mRPNameEditTextKeyListener);*/
    }

    public void editSchedule(ProgramSlot pr2edit) {
        this.pr2edit = pr2edit;
        if (pr2edit != null) {
            //TODO Need to change to fix the edit schedule as per required.

            /*mRPNameEditText.setText(rp2edit.getRadioProgramName(), TextView.BufferType.NORMAL);
            mRPDescEditText.setText(rp2edit.getRadioProgramDescription(), TextView.BufferType.EDITABLE);
            mDurationEditText.setText(rp2edit.getRadioProgramDuration(), TextView.BufferType.EDITABLE);
            mRPNameEditText.setKeyListener(null);*/
        }
    }
}
