package sg.edu.nus.iss.phoenix.maintainschedule.android.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.maintainschedule.entity.ProgramSlot;


/**
 * Created by Gaurav on 13-09-2017.
 */

public class ScheduledProgramScreen extends AppCompatActivity {
    // Tag for logging
    private static final String TAG = ScheduledProgramScreen.class.getName();

    private ListView mListView;
    private ProgramSlotAdapter mPSAdapter;
    private ProgramSlot selectedPS = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduled_program_screen);

        ArrayList<ProgramSlot> programSlots = new ArrayList<ProgramSlot>();
        mPSAdapter = new ProgramSlotAdapter(this, programSlots);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlFactory.getProgramController().selectCreateProgram();
            }
        });

        mListView = (ListView) findViewById(R.id.program_slot_list);
        mListView.setAdapter(mPSAdapter);
        // Setup the item selection listener
        mListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                // Log.v(TAG, "Program Slot at position " + position + " selected.");
                ProgramSlot programSlot = (ProgramSlot) adapterView.getItemAtPosition(position);

                selectedPS = programSlot;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // your stuff
            }
        });

    }









