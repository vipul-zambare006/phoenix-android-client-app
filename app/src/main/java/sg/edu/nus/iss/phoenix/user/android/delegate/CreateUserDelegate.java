package sg.edu.nus.iss.phoenix.user.android.delegate;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import sg.edu.nus.iss.phoenix.user.android.controller.UserController;
import sg.edu.nus.iss.phoenix.user.entity.User;

import static sg.edu.nus.iss.phoenix.core.android.delegate.DelegateHelper.PRMS_BASE_URL_USER;

/**
 * Created by Govardhan on 19/9/2017.
 */

public class CreateUserDelegate extends AsyncTask<User, Void, Boolean> {

    private static final String TAG = CreateUserDelegate.class.getName();

    private final UserController userController;

    public CreateUserDelegate(UserController userController) {
        this.userController = userController;
    }
    /**
     * Override this method to perform a computation on a background thread.
     *
     * @param params The parameters of the task.
     *
     * @return A result, defined by the subclass of this task.
     *
     *
     */

    @Override
    protected Boolean doInBackground(User... params) {
        Uri builtUri = Uri.parse(PRMS_BASE_URL_USER).buildUpon().build();
        builtUri = Uri.withAppendedPath(builtUri, "create").buildUpon().build();
        Log.v(TAG, builtUri.toString());
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            Log.v(TAG, e.getMessage());
            return new Boolean(false);
        }
        JSONObject json = new JSONObject();
        try {
            json.put("id", params[0].getUserName());
            json.put("name", params[0].getUserName());
            json.put("password", params[0].getPassword());

            JSONArray rolesArray = new JSONArray();
            JSONObject jsonRoles = new JSONObject();

            jsonRoles.put("role", params[0].getUserRole());
            rolesArray.put(jsonRoles);

            json.put("roles", rolesArray);

        } catch (JSONException e) {
            Log.v(TAG, e.getMessage());
        }

        boolean success = false;
        HttpURLConnection httpURLConnection = null;
        DataOutputStream dos = null;
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestMethod("PUT");
            httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=utf8");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            dos = new DataOutputStream(httpURLConnection.getOutputStream());
            dos.writeUTF(json.toString());
            dos.write(256);
            Log.v(TAG, "Http PUT response " + httpURLConnection.getResponseCode());
            success = true;
        } catch (IOException exception) {
            Log.v(TAG, exception.getMessage());
        } finally {
            if (dos != null) {
                try {
                    dos.flush();
                    dos.close();
                } catch (IOException exception) {
                    Log.v(TAG, exception.getMessage());
                }
            }
            if (httpURLConnection != null) httpURLConnection.disconnect();
        }
        return new Boolean(success);
    }

    @Override
    protected void onPostExecute(Boolean result) {
        userController.userCreated(result.booleanValue());
    }
}