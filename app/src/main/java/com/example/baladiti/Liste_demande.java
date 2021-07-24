package com.example.baladiti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Liste_demande extends AppCompatActivity {
    FloatingActionButton actionButton;
    RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference ref;
    FirebaseAuth fAuth;
    EditText email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_demande);

        email = findViewById(R.id.email1);
        pass = findViewById(R.id.pass1);
        fAuth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.rc1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Database").child("Demande_Information");

        actionButton = findViewById(R.id.fab);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fAuth.getCurrentUser() == null) {
                    Toast.makeText(Liste_demande.this ,  "الرجاء التسجيل ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Login.class));
                    finish();
                } else {
                    Intent rec = new Intent(Liste_demande.this, Aj_information.class);
                    startActivity(rec);
                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Member_info , Holder_info> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Member_info, Holder_info>(
                        Member_info.class,
                        R.layout.data_info,
                        Holder_info.class,
                        ref
                ) {
                    @Override
                    protected void populateViewHolder(Holder_info holder_info, Member_info member_info, int i) {

                        holder_info.setView(getApplicationContext(),member_info.getAddresse() , member_info.getType());

                    }
                };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}