package uek.krakow.pl.androidinvoicegenerator.tasks;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import uek.krakow.pl.androidinvoicegenerator.viewcontroller.interfaces.UpdatableActivity;

/**
 * Created by Szymon on 30.05.2017.
 */

public class GetFromApiTask extends AsyncTask<String, Integer, JSONObject> {

    private UpdatableActivity activity;

    public GetFromApiTask(UpdatableActivity activity) {
        this.activity = activity;
    }

    @Override
    protected JSONObject doInBackground(String... nip) {

        String jsonStr = "{}";

        try {
            URL url = new URL("https://api-v3.mojepanstwo.pl/dane/krs_podmioty.json?conditions[krs_podmioty.nip]=" + nip[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                jsonStr = readStream(in);
            } finally {
                urlConnection.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    private String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is), 1000);
        for (String line = r.readLine(); line != null; line = r.readLine()) {
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }


    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        try {
            jsonObject = jsonObject.getJSONArray("Dataobject").getJSONObject(0).getJSONObject("data");

            activity.update(jsonObject);

        } catch (JSONException e) {
            activity.onUpdateFailure();
        }
    }
}
