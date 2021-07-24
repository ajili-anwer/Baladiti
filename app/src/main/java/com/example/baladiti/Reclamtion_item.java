package com.example.baladiti;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.jackandphantom.androidlikebutton.AndroidLikeButton;

public class Reclamtion_item extends AppCompatActivity {
    AndroidLikeButton likeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reclamtion_item);

        likeButton = findViewById(R.id.likebtn);
        likeButton.setOnLikeEventListener(new AndroidLikeButton.OnLikeEventListener() {
            @Override
            public void onLikeClicked(AndroidLikeButton androidLikeButton) {
                Toast.makeText(Reclamtion_item.this, "لقد تم دعم الشكوى ", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onUnlikeClicked(AndroidLikeButton androidLikeButton) {

            }
        });

    }
}