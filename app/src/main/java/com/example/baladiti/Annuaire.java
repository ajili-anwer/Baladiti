package com.example.baladiti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Annuaire extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Get_ann> Get_anns;
    Adapter adapter;

     public static final String GET_ALL = "https://mamunicipalite.tn/api/municipals/6111";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.annuaire);
        recyclerView = findViewById(R.id.annuaire_list);
        Get_anns = new ArrayList<>();
        getAll();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, Get_anns);

    }
     public void getAll(){
     JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
             GET_ALL,
             null,
             new Response.Listener<JSONObject>() {
                 @Override
                 public void onResponse(JSONObject response) {
                     for(int i=0; i < response.length(); i++) {
                         try {
                             JSONObject annuaireobject = response.getJSONObject(String.valueOf(i));
                             Get_ann Get_ann = new Get_ann();
                             Get_ann.setSlug(annuaireobject.getString("slug").toString());
                             Get_ann.setAdresse(annuaireobject.getString("adresse1").toString());
                             Get_ann.setActivite(annuaireobject.getString("activite").toString());
                             Get_ann.setImage(annuaireobject.getString("logo").toString());
                             Get_anns.add(Get_ann);

                         } catch (JSONException e) {
                             e.printStackTrace();
                             Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                         }
                     }

                     }
                 }, new Response.ErrorListener() {
                     @Override
                     public void onErrorResponse(VolleyError error) {
                         Toast.makeText(getBaseContext(),error.getMessage(),Toast.LENGTH_LONG).show();

                     }
                 });

                 RequestQueue requestQueue = Volley.newRequestQueue(this);
             requestQueue.add(request);

             }
     }