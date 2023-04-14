package ca.qc.sol_td10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TableLayout;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ca.qc.sol_td10.services.CarteSouhaitRestAPI;
import ca.qc.sol_td10.services.CategorieRestAPI;

public class CartesActivity extends AppCompatActivity {

    TableLayout tableCartes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartes);

        tableCartes = findViewById(R.id.table_cartes);

        //exécuter de l'appel du service
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new CarteSouhaitRestAPI(this, tableCartes));
        service.shutdown();
    }
}