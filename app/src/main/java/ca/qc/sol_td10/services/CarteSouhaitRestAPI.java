package ca.qc.sol_td10.services;

import android.app.Activity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ca.qc.sol_td10.adapter.CategorieAdapter;
import ca.qc.sol_td10.entities.CarteSouhait;
import ca.qc.sol_td10.entities.Categorie;

public class CarteSouhaitRestAPI implements Runnable {
    public interface CommunicationChannel{
        void loadData(List<CarteSouhait> carteSouhaits);
    }

    CommunicationChannel channel;
    String urlPath = "https://derbali-36d8.restdb.io/rest/cartes-souhaits";
    String key = "232ec52be6fc72935ea68431ecf658ba36b5a";

    Activity activity;

    public CarteSouhaitRestAPI(Activity activity) {
        this.activity = activity;
        this.channel = (CommunicationChannel) activity;
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
                //convert jsonBody to List<CarteSouhait> in memory

                Gson gson = new Gson();
                Type listType = new TypeToken<ArrayList<CarteSouhait>>(){}.getType();

                List<CarteSouhait> carteSouhaits = gson.fromJson(body, listType);

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //when list of categories is ready
                        channel.loadData(carteSouhaits);
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

