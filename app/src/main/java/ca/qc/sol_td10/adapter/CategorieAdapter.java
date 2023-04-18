package ca.qc.sol_td10.adapter;

import android.content.Context;
import android.content.Intent;
import android.service.chooser.ChooserTarget;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ca.qc.sol_td10.CartesActivity;
import ca.qc.sol_td10.R;
import ca.qc.sol_td10.entities.Categorie;

public class CategorieAdapter extends RecyclerView.Adapter<CategorieViewHolder> {
    //data source
    List<Categorie> categories;
    Context context;
    public CategorieAdapter(List<Categorie> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public CategorieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.ligne_categorie, parent, false);
        return new CategorieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategorieViewHolder holder, int position) {
        holder.lblNom.setText(categories.get(position).getNom());
        //événement de click sur une catégorie
        final int indexCateg = position;
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CartesActivity.class);
                intent.putExtra("categorie", categories.get(indexCateg));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
