package com.example.baladiti;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Holder_info extends RecyclerView.ViewHolder {
    View view;
    public Holder_info(@NonNull View itemView) {
        super(itemView);

        view = itemView;
    }
    public void setView(Context context , String addresse, String genre ){
        TextView addr_tv = view.findViewById(R.id.addr_tv);
        TextView genre_tv = view.findViewById(R.id.genre_tv);
        addr_tv.setText(addresse);
        genre_tv.setText(genre);


    }
}
