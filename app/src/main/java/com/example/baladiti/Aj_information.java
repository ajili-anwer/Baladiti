package com.example.baladiti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Aj_information extends AppCompatActivity {
    Button btn;
    Member_info member_info;
    RadioButton r1,r2,r3,r4,r5,r6;
    FirebaseDatabase database;
    DatabaseReference ref;
    int i = 0;
    EditText nom1 , email1, tel1,fax1,addresse1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aj_information);
        btn = (Button) findViewById(R.id.btn_n);
        member_info = new Member_info();
        nom1 = (EditText) findViewById(R.id.np);
        email1 = findViewById(R.id.email);
        tel1 = findViewById(R.id.ntel);
        fax1 = findViewById(R.id.fax);
        addresse1 = findViewById(R.id.addr);
        r1 = findViewById(R.id.rb1);
        r2 = findViewById(R.id.rb2);
        r3 = findViewById(R.id.rb3);
        r4 = findViewById(R.id.rb4);
        r5 = findViewById(R.id.rb5);
        r6 = findViewById(R.id.rb6);

        ref = FirebaseDatabase.getInstance().getReference("Database").child("Demande_Information");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    i = (int) dataSnapshot.getChildrenCount();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m1 = r1.getText().toString();
                String m2 = r2.getText().toString();
                String m3 = r3.getText().toString();
                String m4 = r4.getText().toString();
                String m5 = r5.getText().toString();
                String m6 = r6.getText().toString();
                if(r1.isChecked()){
                    member_info.setGenre(m1);
                    ref.child(String.valueOf(i+1)).setValue(member_info);

                    member_info.setNom(nom1.getText().toString());
                    member_info.setEmail_info(email1.getText().toString());
                    member_info.setTel(tel1.getText().toString());
                    member_info.setFax(fax1.getText().toString());
                    member_info.setAddresse(addresse1.getText().toString());
                    ref.child(String.valueOf(i+1)).setValue(member_info);

                }
                if(r1.isChecked()){
                    member_info.setGenre(m1);
                    ref.child(String.valueOf(i+1)).setValue(member_info);

                }
                if(r2.isChecked()){
                    member_info.setGenre(m2);
                    ref.child(String.valueOf(i+1)).setValue(member_info);

                }
                if(r3.isChecked()){
                    member_info.setType(m3);
                    ref.child(String.valueOf(i+1)).setValue(member_info);

                }
                if(r4.isChecked()){
                    member_info.setType(m4);
                    ref.child(String.valueOf(i+1)).setValue(member_info);

                }
                if(r5.isChecked()){
                    member_info.setType(m5);
                    ref.child(String.valueOf(i+1)).setValue(member_info);

                }
                if(r6.isChecked()){
                    member_info.setType(m6);
                    ref.child(String.valueOf(i+1)).setValue(member_info);

                }

            }
        });

    }
}