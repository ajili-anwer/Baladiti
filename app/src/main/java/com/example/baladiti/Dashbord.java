package com.example.baladiti;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.google.firebase.auth.FirebaseAuth;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Dashbord extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private Handler slideHandler = new Handler();
    SpaceNavigationView navigationView;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.dashbord);

        //naviagation bar
        navigationView = findViewById(R.id.space);
        navigationView.initWithSaveInstanceState(savedInstanceState);
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.call1));
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_langue));
        //navigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_compte));
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_ajouter));
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_info));
        navigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                navigationView.setCentreButtonSelectable(true);
                Intent rec = new Intent(Dashbord.this , Modifier_profil.class);
                startActivity(rec);
            }

            @Override
            public void onItemClick(int itemIndex, String itemName){
            switch(itemIndex) {
                case 0:
                    openDialog();
                    break;
                case 1:
                   showChangeLanguageDialog();
                   break;
                case 2:
                    openSignup();
                    break;
                case 3:
                    ShowSuccessDialog();
                    break;
                default:
            }

            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                //Toast.makeText(Dashbord.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton button = (ImageButton) findViewById(R.id.reclamation);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rec = new Intent(Dashbord.this , Reclamation_nv.class);
                startActivity(rec);
            }
        });

        ImageButton button4 = (ImageButton) findViewById(R.id.a);

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rec1 = new Intent(Dashbord.this , Doc_tech.class);
                startActivity(rec1);
            }
        });


        ImageButton button7 = (ImageButton) findViewById(R.id.istitla3);

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rec7 = new Intent(Dashbord.this , Poll.class);
                startActivity(rec7);
            }
        });

        ImageButton button3 = (ImageButton) findViewById(R.id.nafadh);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rec3 = new Intent(Dashbord.this ,Liste_demande.class);
                startActivity(rec3);
            }
        });

        ImageButton button10 = (ImageButton) findViewById(R.id.mj);

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rec10 = new Intent(Dashbord.this ,Membre_muni.class);
                startActivity(rec10);
            }
        });

      ImageButton button5 = (ImageButton) findViewById(R.id.dalil);

       button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent rec5 = new Intent(Dashbord.this , Annuaire_ret.class);
               startActivity(rec5);
            }
       });



        viewPager2 = findViewById(R.id.viewPagerImage);
        List<SliderIteam> sliderIteams = new ArrayList<>();
        sliderIteams.add(new SliderIteam(R.drawable.im6));
        sliderIteams.add(new SliderIteam(R.drawable.im1));
        sliderIteams.add(new SliderIteam(R.drawable.im2));
        sliderIteams.add(new SliderIteam(R.drawable.im3));
        sliderIteams.add(new SliderIteam(R.drawable.im4));
        sliderIteams.add(new SliderIteam(R.drawable.im5));

        viewPager2.setAdapter(new SliderAdapter(sliderIteams, viewPager2));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer() ;
        compositePageTransformer.addTransformer(new MarginPageTransformer(10));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1-Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);

            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHandler.removeCallbacks(slideRunnable);
                slideHandler.postDelayed(slideRunnable, 4000);
            }
        });
    }

    private void openSignup() {

            Intent rec = new Intent(Dashbord.this, Signup.class);
            startActivity(rec);

    }

    private void showChangeLanguageDialog() {
        final String[] listItems = {"العربية ", "Français", "English"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(Dashbord.this);
        mBuilder.setTitle("Choose Language...");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

                if (i == 0){
                    setLocale("Ar");
                    recreate();
                }
                else if (i == 1){
                    setLocale("Fr");
                    recreate();
                }

               else if (i == 2){
                    setLocale("En");
                    recreate();
                }
                dialog.dismiss();


            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Paramètres", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();

        }
    public void loadLocale () {
        SharedPreferences prefs = getSharedPreferences("Paramètres", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        setLocale(language);
    }


    private Runnable slideRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);

        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        slideHandler.removeCallbacks(slideRunnable );
    }

    @Override
    protected void onResume() {
        super.onResume();
        slideHandler.postDelayed(slideRunnable, 4000);
    }

    public void openDialog(){

        Dialog dialog = new Dialog();
        dialog.show(getSupportFragmentManager(), "بلديتي");


    }

    private void ShowSuccessDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(Dashbord.this , R.style.AlertDialogTheme);
        View view = LayoutInflater.from(Dashbord.this).inflate(
                R.layout.layout_succes_dialog,
                (ConstraintLayout)findViewById(R.id.layoutDialogContainer)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.textTitle)).setText(getResources().getString(R.string.baladiti));
        ((TextView) view.findViewById(R.id.textMessage)).setText(getResources().getString(R.string.info));
        ((Button) view.findViewById(R.id.buttonAction)).setText(getResources().getString(R.string.okay));
        ((ImageView) view.findViewById(R.id.imageIcon)).setImageResource(R.drawable.cap);

        final AlertDialog alertDialog = builder.create();
        view.findViewById(R.id.buttonAction).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        if(alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();


    }
}
