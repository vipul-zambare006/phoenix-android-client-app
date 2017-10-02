package sg.edu.nus.iss.phoenix.user.android.ui;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by monalisadebnth on 2/10/17.
 */

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.user.entity.User;

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
                // Log.v(TAG, "PresenterProducer at position " + position + " selected.");
                User user = (User) adapterView.getItemAtPosition(position);

                // Log.v(TAG, "PresenterProducer name is " + rp.getPresenterProducer());
                selectedUser = user;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // your stuff
            }
        });
    }


    public void showPresenterProducer(List<User> presenterProducer) {
        mUserAdapter.clear();

        for (int i = 0; i < presenterProducer.size(); i++) {
            mUserAdapter.add(presenterProducer.get(i));
        }
    }

}
