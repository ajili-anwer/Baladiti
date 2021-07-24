package com.example.baladiti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class annuaire_nv extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference ref;
    EditText activite , adresse1,email,slug,tel;
    Button btn;
    int maxid = 0;
    Member_ann member_ann;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.annuaire_nv);
        activite = (EditText) findViewById(R.id.et_activite);
        adresse1 = (EditText) findViewById(R.id.et_adresse);
        email = (EditText) findViewById(R.id.et_email);
        slug = (EditText) findViewById(R.id.et_slug);
        tel = (EditText) findViewById(R.id.et_tel);
        btn = (Button) findViewById(R.id.envoie);

        member_ann = new  Member_ann();
        ref = FirebaseDatabase.getInstance().getReference("Database").child("Annuaire");


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    maxid = (int) dataSnapshot.getChildrenCount();
                }else{
                    ///
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               member_ann.setActivite(activite.getText().toString());
                member_ann.setAdresse1(adresse1.getText().toString());
                member_ann.setEmail(email.getText().toString());
                member_ann.setSlug(slug.getText().toString());
                member_ann.setTel(tel.getText().toString());

                ref.child(String.valueOf(maxid+1)).setValue(member_ann);
            }
        });
    }
}