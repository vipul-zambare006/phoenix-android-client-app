package sg.edu.nus.iss.phoenix.user.android.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.radioprogram.android.ui.MaintainProgramScreen;
import sg.edu.nus.iss.phoenix.user.entity.User;

/**
 * Created by Govardhan on 19/9/2017.
 */

public class MaintainUserScreen extends AppCompatActivity {

    private static final String TAG = MaintainProgramScreen.class.getName();
    KeyListener userNameEditTextKeyListener = null;
    private EditText userNameEditText;
    private EditText userRoleEditText;
    private EditText userPasswordEditText;
    private User useredit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintain_user);

        userNameEditText = (EditText) findViewById(R.id.user_name_text_view);
        userPasswordEditText = (EditText) findViewById(R.id.user_password_text_view);
        userRoleEditText = (EditText) findViewById(R.id.user_role_text_view);
        userNameEditTextKeyListener = userNameEditText.getKeyListener();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        ControlFactory.getUserController().onDisplayUser(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user_editor, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (useredit == null) {
            MenuItem menuItem = menu.findItem(R.id.user_action_delete);
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.user_action_save:
                if (useredit == null) {
                    User user = new User(userNameEditText.getText().toString(), userNameEditText.getText().toString(),
                            userPasswordEditText.getText().toString(), userRoleEditText.getText().toString());
                    ControlFactory.getUserController().selectCreateUser(user);
                } else { // Edited.
                    Log.v(TAG, "Saving User " + useredit.getUserName() + "...");
                    useredit.setUserName(userNameEditText.getText().toString());
                    useredit.setPassword(userPasswordEditText.getText().toString());
                    useredit.setUserRole(userRoleEditText.getText().toString());
                    ControlFactory.getUserController().selectUpdateUser(useredit);
                }
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.user_action_delete:
                ControlFactory.getUserController().selectDeleteUser(useredit);
                return true;
            // Respond to a click on the "Cancel" menu option
            case R.id.user_action_cancel:
                ControlFactory.getUserController().selectCancelCreateEditUser();
                return true;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        ControlFactory.getUserController().selectCancelCreateEditUser();
    }

    public void createUser() {
        this.useredit = null;
        userNameEditText.setText("", TextView.BufferType.EDITABLE);
        userPasswordEditText.setText("", TextView.BufferType.EDITABLE);
        userRoleEditText.setText("", TextView.BufferType.EDITABLE);
        userNameEditText.setKeyListener(userNameEditTextKeyListener);
    }

    public void editUser(User useredit) {
        this.useredit = useredit;
        if (useredit != null) {
            userNameEditText.setText(useredit.getUserName(), TextView.BufferType.NORMAL);
            userPasswordEditText.setText(useredit.getPassword(), TextView.BufferType.EDITABLE);
            userRoleEditText.setText(useredit.getUserRole(), TextView.BufferType.EDITABLE);
            userNameEditText.setKeyListener(null);
        }

    }
}
