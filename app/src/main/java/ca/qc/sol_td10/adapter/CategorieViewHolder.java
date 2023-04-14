package ca.qc.sol_td10.adapter;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ca.qc.sol_td10.R;

public class CategorieViewHolder extends RecyclerView.ViewHolder {
    TextView lblNom;
    View container;

    public CategorieViewHolder(@NonNull View itemView) {
        super(itemView);
        container = itemView;
        lblNom = itemView.findViewById(R.id.lbl_nom_categorie);
    }
}
