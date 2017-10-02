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
import sg.edu.nus.iss.phoenix.user.android.ui.ReviewSelectPresenterProducerScreen;

/**
 * Created by Gaurav on 26-09-2017.
 */

public class ScheduleScreen extends AppCompatActivity {
    // Tag for logging
    private static final String TAG = ScheduleScreen.class.getName();
    String duration = null;
    private Button radio_program, presenter, producer;
    private EditText mdateofprogram;
    private EditText mduration, starttime, assignedby;
    private TextView rpTextView, producerTextView, presenterTextView;
    private ProgramSlot pr2edit = null;

    private String copy_string = "copy";

    // KeyListener mPSNameEDitTextKeyListner = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maintain_schedule);
        rpTextView = (TextView) findViewById(R.id.maintain_schedule_selected_programName_text_view);
        producerTextView = (TextView) findViewById(R.id.maintain_schedule_selected_producer_text_view);
        presenterTextView = (TextView) findViewById(R.id.maintain_schedule_selected_Presenter_text_view);
        radio_program = (Button) findViewById(R.id.programName);
        presenter = (Button) findViewById(R.id.Selectpresenter);
        producer = (Button) findViewById(R.id.Selectproducer);
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

        presenter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent input = new Intent(v.getContext(), ReviewSelectPresenterProducerScreen.class);
                startActivityForResult(input, 2);
            }
        });

        producer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent input = new Intent(v.getContext(), ReviewSelectPresenterProducerScreen.class);
                startActivityForResult(input, 3);
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
        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                String presenterName = data.getStringExtra("presenterName");

                presenterTextView.setText(presenterName);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }

        if (requestCode == 3) {
            if (resultCode == Activity.RESULT_OK) {
                String producerName = data.getStringExtra("producerName");

                producerTextView.setText(producerName);
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

                    ProgramSlot ps = new ProgramSlot(rpTextView.getText().toString(), presenterTextView.getText().toString(), producerTextView.getText().toString(), assignedby.getText().toString(), mduration.getText().toString(), starttime.getText().toString(), mdateofprogram.getText().toString());
                    ControlFactory.getScheduleController().selectCreateSchedule(ps);

                } else if (pr2edit.getAction().toLowerCase() != copy_string) { // Edited.
                    Log.v(TAG, "Updating program slot " + pr2edit.getRadioProgramName() + "...");

                    pr2edit.setProducer(producerTextView.getText().toString());
                    pr2edit.setDuration(mduration.getText().toString());
                    pr2edit.setDateOfProgram(mdateofprogram.getText().toString());
                    pr2edit.setRadioProgramName(rpTextView.getText().toString());
                    pr2edit.setPresenter(presenterTextView.getText().toString());
                    pr2edit.setAssignedBy(assignedby.getText().toString());
                    pr2edit.setStartTime(starttime.getText().toString());

                    ControlFactory.getScheduleController().selectUpdateSchedule(pr2edit);
                } else {
                    ControlFactory.getScheduleController().selectCopyupdateSchedule(pr2edit);
                }
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                Log.v(TAG, "Deleting program slot " + pr2edit.getRadioProgramName() + "...");
                ControlFactory.getScheduleController().selectDeleteSchedule(pr2edit);
                return true;

            // Respond to a click on the "Cancel" menu option
            case R.id.action_cancel:

                Log.v(TAG, "Canceling creating/editing Program slot ...");
                ControlFactory.getScheduleController().selectCancelCreateEditSchedule();
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

        rpTextView.setText("", TextView.BufferType.EDITABLE);
        producerTextView.setText("", TextView.BufferType.EDITABLE);
        presenterTextView.setText("", TextView.BufferType.EDITABLE);
        mdateofprogram.setText("", TextView.BufferType.EDITABLE);
        mduration.setText("", TextView.BufferType.EDITABLE);
        starttime.setText("", TextView.BufferType.EDITABLE);
        assignedby.setText("", TextView.BufferType.EDITABLE);


         /*mRPNameEditText.setText("", TextView.BufferType.EDITABLE);
        mRPDescEditText.setText("", TextView.BufferType.EDITABLE);
        mDurationEditText.setText("", TextView.BufferType.EDITABLE);
        mRPNameEditText.setKeyListener(mRPNameEditTextKeyListener);*/
    }

    public void editSchedule(ProgramSlot pr2edit) {
        this.pr2edit = pr2edit;
        if (pr2edit != null) {
            //TODO Need to change to fix the edit schedule as per required.

            rpTextView.setText(pr2edit.getRadioProgramName(), TextView.BufferType.EDITABLE);
            producerTextView.setText(pr2edit.getProducer(), TextView.BufferType.EDITABLE);
            presenterTextView.setText(pr2edit.getPresenter(), TextView.BufferType.EDITABLE);
            mdateofprogram.setText(pr2edit.getDateOfProgram(), TextView.BufferType.EDITABLE);
            mdateofprogram.setEnabled(false);
            mduration.setText(pr2edit.getDuration(), TextView.BufferType.EDITABLE);
            starttime.setText(pr2edit.getStartTime(), TextView.BufferType.NORMAL);
            starttime.setEnabled(false);
            assignedby.setText(pr2edit.getAssignedBy(), TextView.BufferType.EDITABLE);

            /*mRPNameEditText.setText(rp2edit.getRadioProgramName(), TextView.BufferType.NORMAL);
            mRPDescEditText.setText(rp2edit.getRadioProgramDescription(), TextView.BufferType.EDITABLE);
            mDurationEditText.setText(rp2edit.getRadioProgramDuration(), TextView.BufferType.EDITABLE);
            mRPNameEditText.setKeyListener(null);*/
        }
    }

    public void copySchedule(ProgramSlot pr2edit) {
        this.pr2edit = pr2edit;
        if (pr2edit != null) {
            //TODO Need to change to fix the edit schedule as per required.

            rpTextView.setText(pr2edit.getRadioProgramName(), TextView.BufferType.EDITABLE);
            producerTextView.setText(pr2edit.getProducer(), TextView.BufferType.EDITABLE);
            presenterTextView.setText(pr2edit.getPresenter(), TextView.BufferType.EDITABLE);
            mdateofprogram.setText("", TextView.BufferType.EDITABLE);
            mduration.setText("", TextView.BufferType.EDITABLE);
            starttime.setText("", TextView.BufferType.EDITABLE);
            assignedby.setText("", TextView.BufferType.EDITABLE);

            /*mRPNameEditText.setText(rp2edit.getRadioProgramName(), TextView.BufferType.NORMAL);
            mRPDescEditText.setText(rp2edit.getRadioProgramDescription(), TextView.BufferType.EDITABLE);
            mDurationEditText.setText(rp2edit.getRadioProgramDuration(), TextView.BufferType.EDITABLE);
            mRPNameEditText.setKeyListener(null);*/
        }
    }
}
