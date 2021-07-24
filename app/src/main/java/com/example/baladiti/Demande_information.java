package com.example.baladiti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Demande_information extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demande_information);

        toolbar=findViewById(R.id.info);
        setSupportActionBar(toolbar);
        TabLayout tabLayout = findViewById(R.id.tab_info);
        TabItem nafadh = findViewById(R.id.nafadh_item);
        TabItem tahrir = findViewById(R.id.tahrir);

        ViewPager2 viewPager2 = findViewById(R.id.viewPager_info);
        viewPager2.setAdapter(new PageAdapter(this));


        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch(position) {
                    case 0 : {
                        tab.setText("النفاذ إلى المعلومة" );
                        break;
                    }
                    case 1 : {
                        tab.setText("مطالب");
                        break;
                    }


                }
            }
        });
        tabLayoutMediator.attach();
    }
}
