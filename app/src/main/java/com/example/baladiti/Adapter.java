package com.example.baladiti;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<Get_ann> Get_anns;
    LayoutInflater inflater;


    public Adapter(Context ctx , List<Get_ann> annuaire){
        this.inflater = LayoutInflater.from(ctx);
        this.Get_anns = Get_anns;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_list_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.slug_id.setText(Get_anns.get(position). getSlug());
        holder.adresse_id.setText(Get_anns.get(position).getAdresse());
        holder.activite_id.setText(Get_anns.get(position).getAdresse());
        Picasso.get().load(Get_anns.get(position).getImage()).into(holder.coverImage);


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView slug_id, adresse_id, activite_id;
        ImageView coverImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            slug_id = itemView.findViewById(R.id.slug_id);
            activite_id = itemView.findViewById(R.id.activite_id);
            adresse_id = itemView.findViewById(R.id.adresse_id);

        }
    }
}
