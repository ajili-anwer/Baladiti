package com.example.baladiti;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Signup extends AppCompatActivity {
    EditText nom , prenom,mail , n_tel, password;
    Button upload;
    FirebaseAuth fAuth;
    DatabaseReference ref;
    FirebaseDatabase database;
    int maxid = 0;
    Member_user member_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        /**DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Database").child("Users");
         Module_signup module_signup = new Module_signup("az","az","az","az");
         reference.push().setValue(module_signup);**/


        nom = (EditText) findViewById(R.id.nom);
        prenom = (EditText) findViewById(R.id.Prenom);
        mail = (EditText) findViewById(R.id.mail);
        n_tel = (EditText) findViewById(R.id.n_tel);
        password = (EditText) findViewById(R.id.password);
        upload = (Button) findViewById(R.id.tasjil);
        fAuth = FirebaseAuth.getInstance();

        member_user = new Member_user();
        ref = database.getInstance().getReference("Database").child("User");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                maxid = (int) dataSnapshot.getChildrenCount();
                }else{

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                member_user.setNom(nom.getText().toString());
                member_user.setPrenom(prenom.getText().toString());
                member_user.setMail(mail.getText().toString());
                member_user.setN_tel(n_tel.getText().toString());
                member_user.setPassword(password.getText().toString());

                ref.child(String.valueOf(maxid+1)).setValue(member_user);
            }
        });

        if(fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        }



        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mail.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mail.setError("الرجاء إدخال جميع البيانات");
                    return;
                }
                if(TextUtils.isEmpty(pass)) {
                    password.setError("الرجاء إدخال جميع البيانات");
                    return;
                }

                if(password.length() < 8){
                    password.setError("أدخل 8 أحرف على أقل");
                    return;
                }
                fAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Signup.this, "تم تسجيل بنجاح ",Toast.LENGTH_SHORT).show() ;
                            startActivity(new Intent(getApplicationContext() , Login.class));
                        }
                        else{
                            Toast.makeText(Signup.this, "Error!" + task.getException().getMessage(),Toast.LENGTH_SHORT).show() ;

                        }


                    }
                });



            }
        });



    }


}