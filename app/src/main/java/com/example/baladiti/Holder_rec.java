package com.example.baladiti;

import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.core.Context;

public class Holder_rec extends RecyclerView.ViewHolder {

    View view;

    public Holder_rec(@NonNull View itemView) {
        super(itemView);
        view = itemView;
    }

    public  void setView(android.content.Context applicationContext, String sujet, String spinner, String textLatLang, String textAdress , String textDate ) {

        TextView sujet_tv =(TextView) view.findViewById(R.id.sujet_tv);
        sujet_tv.setText(sujet);

        TextView spinner_tv = (TextView) view.findViewById(R.id.spinner_tv);
        spinner_tv.setText(spinner);
        TextView adresse_tv = (TextView)  view.findViewById(R.id.adresse_tv);
        adresse_tv.setText(textAdress);
        TextView textDate_tv = (TextView)  view.findViewById(R.id.textDate_tv);
        textDate_tv.setText(textDate);





    }

}
