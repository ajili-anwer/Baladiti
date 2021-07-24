package com.example.baladiti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText email, pass;
    Button connecter;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        TextView sign = findViewById(R.id.s1);
        String text = "إنشاء حساب ";
        SpannableString ss = new SpannableString(text);
        connecter = findViewById(R.id.se_connecter);
        email = findViewById(R.id.email1);
        pass = findViewById(R.id.pass1);
        fAuth = FirebaseAuth.getInstance();
        connecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1 = email.getText().toString().trim();
                String pass1 = pass.getText().toString().trim();

                if (TextUtils.isEmpty(email1)) {
                    email.setError("الرجاء إدخال جميع البيانات");
                    return;
                }
                if (TextUtils.isEmpty(pass1)) {
                    pass.setError("الرجاء إدخال جميع البيانات");
                    return;
                }

                if (pass.length() < 8) {
                    pass.setError("أدخل 8 أحرف على أقل");
                    return;
                }
                fAuth.signInWithEmailAndPassword(email1, pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "تم تسجيل الدخول بنجاح", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Login.class));
                        } else {
                            Toast.makeText(Login.this, "الرجاء التثبت من المعطيات " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    }
                });



            }
        });



    }


    public void openSignupPage(View view) {
        startActivity(new Intent(getApplicationContext(), Signup.class));
        finish();
    }
}