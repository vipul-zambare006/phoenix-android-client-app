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

public class DeleteScheduleDelegate extends AsyncTask<ProgramSlot, Void, Boolean> {

    private static final String TAG = sg.edu.nus.iss.phoenix.maintainschedule.android.delegate.DeleteScheduleDelegate.class.getName();

    private final ScheduleController scheduleController;

    public DeleteScheduleDelegate(ScheduleController scheduleController) {
        this.scheduleController = scheduleController;
    }

    @Override
    protected Boolean doInBackground(ProgramSlot... params) {
        Uri builtUri = Uri.parse(PRMS_BASE_URL_MaintainSchedule).buildUpon().build();
        builtUri = Uri.withAppendedPath(builtUri, "delete").buildUpon().build();
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
            json.put("radioProgramId", params[0].getRadioProgramName());
            json.put("dateOfProgram", params[0].getDateOfProgram());
            json.put("presenterId", params[0].getPresenter());
            json.put("producerId", params[0].getProducer());
            json.put("assignedBy", params[0].getAssignedBy());
            json.put("startTime", params[0].getStartTime());
            json.put("duration", params[0].getDuration());
        } catch (JSONException e) {
            Log.v(TAG, e.getMessage());
        }
        boolean success = false;
        HttpURLConnection httpURLConnection = null;
        DataOutputStream dos = null;
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestMethod("DELETE");
            httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=utf8");
            httpURLConnection.setUseCaches(false);
            dos = new DataOutputStream(httpURLConnection.getOutputStream());
            dos.writeUTF(json.toString());
            dos.write(256);

            Log.v(TAG, "Http DELETE response " + httpURLConnection.getResponseCode());
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
        scheduleController.scheduleDeleted(result.booleanValue());

    }

}




