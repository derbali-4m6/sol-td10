package ca.qc.sol_td10.services;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import ca.qc.sol_td10.entities.Destination;

public class DestinationRestAPI implements Runnable {

    public interface CommunicationChannel {
        void sendResponseCode(int responseCode);
    }

    CommunicationChannel channel;
    Activity activity;
    Destination destination;

    String urlPath = "https://derbali-36d8.restdb.io/rest/destinations";
    String key = "232ec52be6fc72935ea68431ecf658ba36b5a";
    HttpURLConnection urlConnection = null;

    public DestinationRestAPI(Activity activity, Destination destination) {
        this.activity = activity;
        this.destination = destination;
        channel = (CommunicationChannel) activity;
    }

    @Override
    public void run() {
        try {
            URL url;
            try {
                url = new URL(urlPath);
                //open a URL connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("x-apikey", key);
                urlConnection.setDoOutput(true);

                //utiliser la bib Gson pour convertir un objet en mémoire è un fichier JSON
                Gson gson = new Gson();
                String destinationJson = gson.toJson(destination);

                //send request
                OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
                wr.write(destinationJson);
                wr.flush();
                if (urlConnection.getResponseCode() >= 200 && urlConnection.getResponseCode() < 300)
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                channel.sendResponseCode(urlConnection.getResponseCode());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });

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
