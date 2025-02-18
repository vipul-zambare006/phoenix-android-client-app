package sg.edu.nus.iss.phoenix.user.android.delegate;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import sg.edu.nus.iss.phoenix.user.android.controller.UserController;

import static sg.edu.nus.iss.phoenix.core.android.delegate.DelegateHelper.PRMS_BASE_URL_USER;

/**
 * Created by Govardhan on 19/9/2017.
 */

public class DeleteUserDelegate extends AsyncTask<String, Void, Boolean> {

    private static final String TAG = DeleteUserDelegate.class.getName();

    private final UserController userController;

    public DeleteUserDelegate(UserController userController) {
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
    protected Boolean doInBackground(String... params) {
        String name = null;
        try {
            name = URLEncoder.encode(params[0], "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.v(TAG, e.getMessage());
            return new Boolean(false);
        }
        Uri builtUri = Uri.parse(PRMS_BASE_URL_USER).buildUpon().build();
        builtUri = Uri.withAppendedPath(builtUri, "delete").buildUpon().build();
        builtUri = Uri.withAppendedPath(builtUri, name).buildUpon().build();
        Log.v(TAG, builtUri.toString());
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            Log.v(TAG, e.getMessage());
            return new Boolean(false);
        }

        boolean success = false;
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestMethod("DELETE");
            httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=utf8");
            httpURLConnection.setUseCaches(false);
            System.out.println(httpURLConnection.getResponseCode());
            Log.v(TAG, "Http DELETE response " + httpURLConnection.getResponseCode());
            success = true;
        } catch (IOException exception) {
            Log.v(TAG, exception.getMessage());
        } finally {
            if (httpURLConnection != null) httpURLConnection.disconnect();
        }
        return new Boolean(success);
    }

    @Override
    protected void onPostExecute(Boolean result) {
        userController.userDeleted(result.booleanValue());
    }
}
