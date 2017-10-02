package sg.edu.nus.iss.phoenix.user.android.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.user.entity.User;

/**
 * Created by Govardhan on 19/9/2017.
 */

public class UserAdapter extends ArrayAdapter<User> {

    public UserAdapter(@NonNull Context context, ArrayList<User> user) {
        super(context, 0, user);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_list_user, parent, false);
        }
        //    Word currentWord = getItem(position);
        User currentuser = getItem(position);

        EditText userName = (EditText) listItemView.findViewById(R.id.user_name_text_view);
        userName.setText(currentuser.getUserName(), TextView.BufferType.NORMAL);
        userName.setKeyListener(null); // This disables editing.

//        EditText userDesc = (EditText) listItemView.findViewById(R.id.user_password_text_view);
//        userDesc.setText(currentuser.getUserDescription(), TextView.BufferType.NORMAL);
//        userDesc.setKeyListener(null);

        EditText userRole = (EditText) listItemView.findViewById(R.id.user_role_text_view);
        userRole.setText(currentuser.getUserRole(), TextView.BufferType.NORMAL);
        userRole.setKeyListener(null);

        return listItemView;
    }
}
