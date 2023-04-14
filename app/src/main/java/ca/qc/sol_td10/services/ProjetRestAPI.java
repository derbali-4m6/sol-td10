package ca.qc.sol_td10.services;

import android.app.Activity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ProjetRestAPI implements Runnable {
    String urlPath = "https://derbali-36d8.restdb.io/rest/projets";
    String key = "232ec52be6fc72935ea68431ecf658ba36b5a";

    Activity activity;

    public ProjetRestAPI(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void run() {
        try {
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(urlPath);
                //open a URL connection
                urlConnection = (HttpURLConnection) url.openConnection();
                //add headers to request
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("x-apikey", key);

                InputStream in = urlConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                // use a string builder to bufferize the response body
                // read from the input strea.
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append('\n');
                }
                // use the string builder directly,
                // or convert it into a String
                String body = sb.toString();
                Log.d("HTTP-GET", body);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Exception: ", e.getMessage());
        }
    }
}
