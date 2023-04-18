package ca.qc.sol_td10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import ca.qc.sol_td10.entities.CarteSouhait;
import ca.qc.sol_td10.entities.Categorie;
import ca.qc.sol_td10.services.CarteSouhaitRestAPI;
import ca.qc.sol_td10.services.CategorieRestAPI;


public class CartesActivity extends AppCompatActivity implements CarteSouhaitRestAPI.CommunicationChannel{

    TableLayout tableCartes;
    List<CarteSouhait> carteSouhaits;
    Categorie categorie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartes);

        tableCartes = findViewById(R.id.table_cartes);
        //quelle catégorie ?
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            this.categorie = extras.getParcelable("categorie");
            if(this.categorie != null){
                //exécuter de l'appel du service
                ExecutorService service = Executors.newSingleThreadExecutor();
                service.execute(new CarteSouhaitRestAPI(this));
                service.shutdown();
            }
        }


    }

    @Override
    public void loadData(List<CarteSouhait> carteSouhaits) {
        this.carteSouhaits = carteSouhaits.stream().filter(c -> c.getCategorie_id().equals(this.categorie.get_id())).collect(Collectors.toList());
        int compteur = 0;
        TableRow tr = null;
        for (CarteSouhait carte: this.carteSouhaits) {
            if(compteur % 2 == 0) {
                //préparer une ligne TableRow
                tr = new TableRow(this);
                TableLayout.LayoutParams trParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
                tr.setPadding(0, 0, 0, 0);
                tr.setLayoutParams(trParams);
            }
            //préparer un affichage TextView
            final ImageView imgCarte = new ImageView(this);
            Picasso.get().load(carte.getUrl()).resize(350, 450).into(imgCarte);
            imgCarte.setPadding(10, 20, 10, 20);
            //event click sur la photo
            imgCarte.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), DestinationActivity.class);
                    intent.putExtra("carteChoisie", carte);
                    intent.putExtra("categorie", categorie);
                    startActivity(intent);
                }
            });

            // ajouter le TextView è la ligne
            tr.addView(imgCarte);
            compteur++;
            if(compteur % 2 == 0 || compteur == this.carteSouhaits.size()) {
                //ajouter la ligne TableRow au tableau TableLayout
                tableCartes.addView(tr);
            }
        }

    }
}