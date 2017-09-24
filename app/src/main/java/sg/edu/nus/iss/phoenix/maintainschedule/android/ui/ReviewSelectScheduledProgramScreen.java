/**
 * Created by Gaurav on 24-09-2017.
 */

package sg.edu.nus.iss.phoenix.maintainschedule.android.ui;

import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import sg.edu.nus.iss.phoenix.maintainschedule.entity.ProgramSlot;

public class ReviewSelectScheduledProgramScreen extends AppCompatActivity {
    // Tag for logging
    private static final String TAG = ReviewSelectScheduledProgramScreen.class.getName();

    private ProgramSlot mPRAdapter;
    // private ArrayAdapter<String> adapter = null;
    private ListView mListView;
    private ProgramSlot selectedPS = null;
}
