package ca.qc.sol_td10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ca.qc.sol_td10.adapter.CategorieAdapter;
import ca.qc.sol_td10.entities.Categorie;
import ca.qc.sol_td10.services.CategorieRestAPI;
import ca.qc.sol_td10.services.ProjetRestAPI;

public class MainActivity extends AppCompatActivity implements CategorieRestAPI.CommunicationChannel {

    RecyclerView recyclerView;
    CategorieAdapter adapter;
    List<Categorie> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // init
        recyclerView = findViewById(R.id.rv_categories);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);


        //exécuter de l'appel du service
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new CategorieRestAPI(this));
        service.shutdown();
    }

    //cette méthode s'execute quand la liste des catégories est prête
    @Override
    public void loadData(List<Categorie> categories) {
        this.categories = categories;
        this.adapter = new CategorieAdapter(this.categories, this);
        this.recyclerView.setAdapter(this.adapter);
    }
}