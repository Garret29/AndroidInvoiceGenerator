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
import java.util.HashMap;

import uek.krakow.pl.androidinvoicegenerator.viewcontroller.ProviderFormActivity;

/**
 * Created by Szymon on 30.05.2017.
 */

public class GetFromApiTask extends AsyncTask<String, Integer, JSONObject> {

    private ProviderFormActivity activity;

    public GetFromApiTask(ProviderFormActivity activity) {
        this.activity = activity;
    }

    @Override
    protected JSONObject doInBackground(String... nip) {
        HashMap<String, String> tags = new HashMap<>();

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

            String nazwa = jsonObject.getString("krs_podmioty.nazwa");
            String ulica = jsonObject.getString("krs_podmioty.adres_ulica");
            String numerBudynku = jsonObject.getString("krs_podmioty.adres_numer");
            String numerLokalu = jsonObject.getString("krs_podmioty.adres_lokal");
            String miejscowosc = jsonObject.getString("krs_podmioty.adres_miejscowosc");
            String kod = jsonObject.getString("krs_podmioty.adres_kod_pocztowy");
            String mail = jsonObject.getString("krs_podmioty.email");

            if (nazwa != null) {
                activity.getEd_nazwaDost().setText(nazwa);
            }
            if (ulica != null) {
                activity.getEd_ulicaDost().setText(ulica);
            }
            if (numerBudynku != null) {
                activity.getEd_domDost().setText(numerBudynku);
            }
            if (numerLokalu != null) {
                activity.getEd_lokalDost().setText(numerLokalu);
            }
            if (miejscowosc != null) {
                activity.getEd_miejscowoscDost().setText(miejscowosc);
            }
            if (kod != null) {
                activity.getEd_kodDost().setText(kod);
            }
            if (mail != null) {
                activity.getEd_emailDost().setText(mail);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
