package ca.qc.sol_td10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ca.qc.sol_td10.entities.CarteSouhait;
import ca.qc.sol_td10.entities.Categorie;
import ca.qc.sol_td10.entities.Destination;
import ca.qc.sol_td10.services.CarteSouhaitRestAPI;
import ca.qc.sol_td10.services.DestinationRestAPI;

public class DestinationActivity extends AppCompatActivity implements DestinationRestAPI.CommunicationChannel{

    private ImageView photoChoisie;
    private EditText nomRecepteur;
    private EditText adresseRecepteur;
    CarteSouhait carteSouhait;
    Categorie categorie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);
        initView();
        Bundle extras =  getIntent().getExtras();
        if(extras!=null){
            carteSouhait = extras.getParcelable("carteChoisie");
            categorie = extras.getParcelable("categorie");
            if(carteSouhait != null){
                Picasso.get().load(carteSouhait.getUrl()).resize(350, 450).into(photoChoisie);
            }
        }
    }

    public void envoyerCarte(View view) {
        //construire un objet de type Destination
        String nomRecepteur = this.nomRecepteur.getText().toString();
        String adresseRecepteur = this.adresseRecepteur.getText().toString();
        Destination destination = new Destination(nomRecepteur, adresseRecepteur, carteSouhait.get_id());

        //exécuter de l'appel du service
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new DestinationRestAPI(this, destination));
        service.shutdown();
    }

    private void initView() {
        photoChoisie = (ImageView) findViewById(R.id.photo_choisie);
        nomRecepteur = (EditText) findViewById(R.id.nom_recepteur);
        adresseRecepteur = (EditText) findViewById(R.id.adresse_recepteur);
    }

    @Override
    public void sendResponseCode(int responseCode) {
        //check response code
        if (responseCode == 201) //ajout efectué avec succès
            Toast.makeText(this, "La Carte choisie est envoyée avec succés", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Échec lors de l'envoi de la carte!", Toast.LENGTH_LONG).show();
        //retour à la liste des cartes de la catégorie en cours
        Intent intent = new Intent(this, CartesActivity.class);
        intent.putExtra("categorie", categorie);
        startActivity(intent);
    }
}