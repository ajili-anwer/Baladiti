package com.example.baladiti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat;
import ir.mirrajabi.searchdialog.core.BaseSearchDialogCompat;
import ir.mirrajabi.searchdialog.core.SearchResultListener;
import ir.mirrajabi.searchdialog.core.Searchable;

public class Annuaire_ret extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference ref ;
    FirebaseDatabase database;
    FloatingActionButton actionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.annuaire_ret);

        actionButton = findViewById(R.id.fab_btn);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              new SimpleSearchDialogCompat(Annuaire_ret.this, "" ,"", null , initData() , new SearchResultListener<Searchable>() {
                  @Override
                  public void onSelected(BaseSearchDialogCompat baseSearchDialogCompat, Searchable searchable, int i) {
                      Toast.makeText(Annuaire_ret.this, ""+searchable.getTitle(), Toast.LENGTH_SHORT).show();
                      baseSearchDialogCompat.dismiss();

                  }
              }).show();
            }
        });

        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        database = FirebaseDatabase.getInstance();
        ref = FirebaseDatabase.getInstance().getReference("Database").child("Annuaire");
    }

    private ArrayList<Search> initData() {
        ArrayList<Search> items = new ArrayList<>();
        items.add(new Search("Sté Ennaser"));
        items.add(new Search("creda"));
        items.add(new Search("FAILATURE"));
        items.add(new Search("BATIMENT ET TRAVEAUX"));
        return items;
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Member_ann,Holder_ann>firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Member_ann, Holder_ann>(
                        Member_ann.class,
                        R.layout.ann_test,
                        Holder_ann.class,
                        ref
                ) {
                    @Override
                    protected void populateViewHolder(Holder_ann holder_ann, Member_ann member_ann, int i) {
                        holder_ann.setView(getApplicationContext(),member_ann.getActivite(), member_ann.getAdresse1(), member_ann.getEmail(), member_ann.getSlug(),member_ann.getTel());

                    }
                };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}