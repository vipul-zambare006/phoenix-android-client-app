package sg.edu.nus.iss.phoenix.maintainschedule.android.delegate;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sg.edu.nus.iss.phoenix.maintainschedule.android.controller.ReviewSelectScheduledController;
import sg.edu.nus.iss.phoenix.maintainschedule.android.controller.ScheduleController;
import sg.edu.nus.iss.phoenix.maintainschedule.entity.ProgramSlot;

import static sg.edu.nus.iss.phoenix.core.android.delegate.DelegateHelper.PRMS_BASE_URL_MaintainSchedule;

/**
 * Created by Gaurav on 13-09-2017.
 */

public class RetrieveScheduleDelegate extends AsyncTask<String, Void, String> {
    private static final String TAG = sg.edu.nus.iss.phoenix.maintainschedule.android.delegate.RetrieveScheduleDelegate.class.getName();
    private ScheduleController scheduleController = null;
    private ReviewSelectScheduledController reviewSelectScheduledController = null;

    public RetrieveScheduleDelegate(ScheduleController scheduleController) {
        this.reviewSelectScheduledController = null;
        this.scheduleController = scheduleController;
    }

    public RetrieveScheduleDelegate(ReviewSelectScheduledController reviewSelectScheduledController) {
        this.scheduleController = null;
        this.reviewSelectScheduledController = reviewSelectScheduledController;
    }

    @Override
    protected String doInBackground(String... params) {
        Uri builtUri1 = Uri.parse(PRMS_BASE_URL_MaintainSchedule).buildUpon().build();
        Uri builtUri = Uri.withAppendedPath(builtUri1, params[0]).buildUpon().build();
        Log.v(TAG, builtUri.toString());
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            Log.v(TAG, e.getMessage());
            return e.getMessage();
        }

        String jsonResp = null;
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            if (scanner.hasNext()) jsonResp = scanner.next();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) urlConnection.disconnect();
        }

        return jsonResp;
    }

    @Override
    protected void onPostExecute(String result) {
        List<ProgramSlot> programSlots = new ArrayList<>();

        if (result != null && !result.equals("")) {
            try {
                JSONArray prArray = new JSONArray(result);

                for (int i = 0; i < prArray.length(); i++) {
                    JSONObject prJson = prArray.getJSONObject(i);

                    String radioProgramName = prJson.getString("radioProgramId");
                    String presenter = prJson.getString("presenterId");
                    String producer = prJson.getString("producerId");
                    String dateOfProgram = prJson.getString("dateOfProgram");
                    String startTime = prJson.getString("startTime");
                    String duration = prJson.getString("duration");

                    programSlots.add(new ProgramSlot(radioProgramName, presenter, producer, "", duration, startTime, dateOfProgram));

                }
            } catch (JSONException e) {
                Log.v(TAG, e.getMessage());
            }
        } else {
            Log.v(TAG, "JSON response error.");
        }

        if (scheduleController != null)
            scheduleController.scheduleRetrieved(programSlots);

        else if (reviewSelectScheduledController != null)
            reviewSelectScheduledController.scheduleRetrieved(programSlots);
    }
}
