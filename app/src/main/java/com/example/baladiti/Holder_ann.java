package com.example.baladiti;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Holder_ann extends RecyclerView.ViewHolder {
    View view;
    public Holder_ann(@NonNull View itemView) {
        super(itemView);

        view = itemView;

    }
    public void  setView(Context context , String activite , String adresse1 , String email , String slug , String tel){
        TextView activite_tv = view.findViewById(R.id.activite_tv);
        TextView adresse_tv = view.findViewById(R.id.adresse_tv);
        TextView slug_tv1 = view.findViewById(R.id.slug_tv1);

        activite_tv.setText(activite);
        adresse_tv.setText(adresse1);
        slug_tv1.setText(slug);


    }
}
