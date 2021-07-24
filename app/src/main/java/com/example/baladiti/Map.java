package com.example.baladiti;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map extends FragmentActivity implements OnMapReadyCallback {
    GoogleMap map;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapp);
        mapFragment.getMapAsync(this);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent dsp = new Intent(Map.this , Dashbord.class);
                                    startActivity(dsp);
                                    finish();

                                }
                            } , 3000
        );
    }

    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng mg = new LatLng(34.419109,8.7910762);
        map.addMarker((new MarkerOptions().position(mg).title("anwer")));
        map.animateCamera(CameraUpdateFactory.newLatLng(mg));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(mg, 20));
    }
}

