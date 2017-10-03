package sg.edu.nus.iss.phoenix.user.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.maintainschedule.android.ui.ScheduleScreen;
import sg.edu.nus.iss.phoenix.user.entity.User;

/**
 * Created by monalisadebnth on 2/10/17.
 */

public class ReviewSelectPresenterProducerScreen extends AppCompatActivity {
    // Tag for logging
    private static final String TAG = ReviewSelectPresenterProducerScreen.class.getName();

    private UserAdapter mUserAdapter;
    private ListView mListView;
    private User selectedUser = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_select_presenter_producer);

        ArrayList<User> presenterProducer = new ArrayList<User>();

        mUserAdapter = new UserAdapter(this, presenterProducer);
        mListView = (ListView) findViewById(R.id.review_select_pp_list);

        mListView.setAdapter(mUserAdapter);

        // Setup the item selection listener
        mListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                User user = (User) adapterView.getItemAtPosition(position);
                selectedUser = user;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // your stuff
            }
        });
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        mListView.setSelection(0);

        ControlFactory.getReviewSelectPresenterProducerController().onDisplay(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_presenterproducer_select, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "View" menu option
            case R.id.action_presenterproducer_select:
                if (selectedUser == null) {
                    // Prompt for the selection of a radio program.
                    Toast.makeText(this, "Select a presenter producer first! Use arrow keys on emulator", Toast.LENGTH_SHORT).show();
                } else {
                    Intent rpintent = new Intent(ReviewSelectPresenterProducerScreen.this, ScheduleScreen.class);
                    rpintent.putExtra("presenterName", selectedUser.getUserName());
                    rpintent.putExtra("producerName", selectedUser.getUserName());
                    setResult(Activity.RESULT_OK, rpintent);
                    finish();
                }
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        ControlFactory.getReviewSelectPresenterProducerController().selectCancel();
    }


    public void showPresenterProducer(List<User> presenterProducer) {
        mUserAdapter.clear();

        for (int i = 0; i < presenterProducer.size(); i++) {
            mUserAdapter.add(presenterProducer.get(i));
        }
    }

}
