package com.example.baladiti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Doc_tech extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);;
        setContentView(R.layout.doc_tech);

        listView = (ListView)findViewById(R.id.listview);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("الجباية المحلية ");
        arrayList.add("الاشهاد بمطابقة النسخ للاصل ");
        arrayList.add("رخصة تركيز علامات إشهارية (ركائز )");
        arrayList.add("قرار الترخيص في البناء ");
        arrayList.add("رخصة في هدم عقار ");
        arrayList.add("ابرام عقد زواج ");
        arrayList.add("الأنشطة التجارية و الحرفية");
        arrayList.add("إستخراج الدفتر العائلي ");


        ArrayAdapter arrayAdapter = new ArrayAdapter(this , android.R.layout.simple_list_item_1 , arrayList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position) {
                    case 0:
                        Intent layout0 = new Intent(Doc_tech.this , Contrat_mari.class);
                        startActivity(layout0);
                    break;
                    case 1:
                        Intent layout1 = new Intent(Doc_tech.this , Copie_conforme.class);
                        startActivity(layout1);
                        break;

                    case 2:
                        Intent layout2 = new Intent(Doc_tech.this , Pub.class);
                        startActivity(layout2);
                        break;
                    case 3:
                        Intent layout3 = new Intent(Doc_tech.this , Construction.class);
                        startActivity(layout3);
                        break;
                }

            }
        });
    }
}