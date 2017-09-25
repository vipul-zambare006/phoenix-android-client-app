/**
 * Created by Gaurav on 24-09-2017.
 */

package sg.edu.nus.iss.phoenix.maintainschedule.android.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.maintainschedule.entity.ProgramSlot;

public class ReviewSelectScheduledProgramScreen extends AppCompatActivity {
    // Tag for logging
    private static final String TAG = ReviewSelectScheduledProgramScreen.class.getName();

    private ProgramSlotAdapter mPRAdapter;
    private ListView mListView;
    private ProgramSlot selectedPS = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_select_schedule);

        ArrayList<ProgramSlot> programSlots = new ArrayList<ProgramSlot>();

        mPRAdapter = new ProgramSlotAdapter(this, programSlots);

        //TODO Change the below line
        //mListView = (ListView) findViewById(R.id.review_select_pm_list);

        mListView.setAdapter(mPRAdapter);

        // Setup the item selection listener
        mListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                // Log.v(TAG, "Radio program at position " + position + " selected.");
                ProgramSlot ps = (ProgramSlot) adapterView.getItemAtPosition(position);

                // Log.v(TAG, "Radio program name is " + rp.getRadioProgramName());
                selectedPS = ps;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // your stuff
            }
        });
    }


    public void showPrograms(List<ProgramSlot> programSlots) {
        mPRAdapter.clear();

        for (int i = 0; i < programSlots.size(); i++) {
            mPRAdapter.add(programSlots.get(i));
        }
    }
}

