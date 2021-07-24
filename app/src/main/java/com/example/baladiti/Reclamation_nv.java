package com.example.baladiti;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Reclamation_nv extends AppCompatActivity {
    FloatingActionButton actionButton;
    RecyclerView recyclerView;
    DatabaseReference ref ;
    FirebaseDatabase database;
    FirebaseAuth fAuth;
    EditText email, pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reclamation_nv);

        email = findViewById(R.id.email1);
        pass = findViewById(R.id.pass1);
        fAuth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.rec_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Database").child("Reclamation");


        actionButton = findViewById(R.id.flotingrec);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(fAuth.getCurrentUser() == null) {
                    Toast.makeText(Reclamation_nv.this ,  "الرجاء التسجيل ",Toast.LENGTH_SHORT).show();
                     startActivity(new Intent(getApplicationContext(), Login.class));
                   finish();
                } else {
                   Intent rec = new Intent(Reclamation_nv.this, Aj_reclamation.class);
                   startActivity(rec);
               }

            }
        });

        /**DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Database").child("Reclamation");
        Module_rec_nv module_rec_nv = new Module_rec_nv("propleme 1 a ras el kef", "14/07/2020","takssir ..." ,"lat/lang", "image1" ,"blablabla");
    reference.push().setValue(module_rec_nv);**/


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Module_rec_nv , Holder_rec> firebaseRecycleAdapter = new FirebaseRecyclerAdapter<Module_rec_nv , Holder_rec> (
                Module_rec_nv.class,
                R.layout.reclamtion_item,
                Holder_rec.class ,
                ref
        ){

            @Override
            protected void populateViewHolder(Holder_rec holder_rec, Module_rec_nv module_rec_nv, int i) {
                //holder_rec.setView(getApplicationContext() , module_rec_nv.getSujet() , module_rec_nv.getDate_input() , module_rec_nv.getSpinner() , module_rec_nv.getTextAdress());
                holder_rec.setView(getApplicationContext() ,
                        module_rec_nv.getSujet() ,
                        module_rec_nv.getSpinner() ,
                        module_rec_nv.getTextLatLang(),
                        module_rec_nv.getTextAdress(),
                        module_rec_nv.getTextDate());

            }
        };
        recyclerView.setAdapter(firebaseRecycleAdapter);
    }
}