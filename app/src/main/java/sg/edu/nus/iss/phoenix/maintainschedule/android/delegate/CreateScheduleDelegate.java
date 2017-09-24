package sg.edu.nus.iss.phoenix.maintainschedule.android.delegate;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import sg.edu.nus.iss.phoenix.maintainschedule.android.controller.ScheduleController;
import sg.edu.nus.iss.phoenix.maintainschedule.entity.ProgramSlot;

import static sg.edu.nus.iss.phoenix.core.android.delegate.DelegateHelper.PRMS_BASE_URL_MaintainSchedule;
/**
 * Created by Gaurav on 13-09-2017.
 */

public class CreateScheduleDelegate extends AsyncTask<ProgramSlot, Void, Boolean> {

    // Tag for logging
    private static final String TAG = CreateScheduleDelegate.class.getName();

    private final ScheduleController scheduleController;

    public CreateScheduleDelegate(ScheduleController scheduleController) {
        this.scheduleController = scheduleController;
    }

    @Override
    protected Boolean doInBackground(ProgramSlot... params) {
        Uri builtUri = Uri.parse(PRMS_BASE_URL_MaintainSchedule).buildUpon().build();
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
            json.put("name", "Test");
            json.put("description", "TestDes");
            json.put("typicalDuration", "Duration");
        } catch (JSONException e) {
            Log.v(TAG, e.getMessage());
        }

        /*try {
            json.put("name", params[0].getRadioProgramName());
            json.put("description", params[0].getRadioProgramDescription());
            json.put("typicalDuration", params[0].getRadioProgramDuration());
        } catch (JSONException e) {
            Log.v(TAG, e.getMessage());
        }*/

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
        scheduleController.scheduleCreated(result.booleanValue());

    }
}
