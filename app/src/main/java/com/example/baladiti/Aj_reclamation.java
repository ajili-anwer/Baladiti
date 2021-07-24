package com.example.baladiti;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.lang.reflect.Member;
import java.text.DateFormat;
import java.util.Calendar;

public class Aj_reclamation extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final int CAMERA_PER_CODE = 101 ;
    public static final int CAMERA_REQUEST_CODE = 103;
    ImageView selectedImage;
    private TextView textLatLang , textAdress ;
    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;
    FirebaseDatabase database;
    DatabaseReference ref;
    private Spinner spinner;
    private ResultReceiver resultReceiver;
    EditText sujet;
    int maxid = 0;
    Module_rec_nv mod;
    Button btn , camerabtn;
     ProgressBar progressBar;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aj_reclamation);

        //
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        //
        final TextView textDate = (TextView) findViewById(R.id.date);
        textDate.setText(currentDate);

        selectedImage = (ImageView) findViewById(R.id.imageView2);
        camerabtn= (Button) findViewById(R.id.textView11);
        camerabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askCameraPermissions();
            }
        });


        resultReceiver = new AddressResultReceiver(new Handler());
        findViewById(R.id.textView15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            Aj_reclamation.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            REQUEST_CODE_LOCATION_PERMISSION
                    );
                } else {
                    getCurrentLication();

                }

            }
        });
        textLatLang = (TextView) findViewById(R.id.editText3);
        textAdress = (TextView) findViewById(R.id.textAdress);
        sujet = (EditText) findViewById(R.id.editText);
        spinner = (Spinner) findViewById(R.id.spinner);
      //  map = (EditText) findViewById(R.id.editText3);
        btn = findViewById(R.id.button2);
        mod = new Module_rec_nv();
        ref = FirebaseDatabase.getInstance().getReference("Database").child("Reclamation");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    maxid = (int) dataSnapshot.getChildrenCount();

                } else {
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
                mod.setSpinner(spinner.getSelectedItem().toString());
                mod.setSujet(sujet.getText().toString());
                mod.setTextLatLang(textLatLang.getText().toString());
                mod.setTextAdress(textAdress.getText().toString());
                ///
                mod.setTextDate(textDate.getText().toString());
                ref.child(String.valueOf(maxid + 1)).setValue(mod);

                sujet.getText().clear();

                Toast.makeText(Aj_reclamation.this, "",Toast.LENGTH_SHORT).show();



            }
        });


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.choix, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    private void askCameraPermissions() {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this , new String[]{Manifest.permission.CAMERA} , CAMERA_PER_CODE);
        }else {
            openCamera();
        }
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == CAMERA_PER_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openCamera();

            }else{
                Toast.makeText(this, "تحقق من صلاحيات التطبيق", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getCurrentLication();
        } else {
            Toast.makeText(this, "تحقق من صلاحيات التطبيق", Toast.LENGTH_SHORT).show();


        }
    }

    private void openCamera() {
       Intent camera = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
       startActivityForResult(camera , CAMERA_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            selectedImage.setImageBitmap(image);




        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void getCurrentLication() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(30000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.getFusedLocationProviderClient(Aj_reclamation.this).requestLocationUpdates(locationRequest, new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                LocationServices.getFusedLocationProviderClient(Aj_reclamation.this).removeLocationUpdates(this);
                if(locationResult != null && locationResult.getLocations().size() > 0 ){
                    int latestLocationIndex = locationResult.getLocations().size() - 1;
                    double latitude = locationResult.getLocations().get(latestLocationIndex).getLatitude();
                    double longitude = locationResult.getLocations().get(latestLocationIndex).getLongitude();
                    textLatLang.setText(String.format("Lat: %s  Lon: %s", latitude , longitude));
                    Location location = new Location("providerNA");
                    location.setLatitude(latitude);
                    location.setLongitude(longitude);
                    FetchAdressFromLatLOng(location);



                } else {
                    progressBar.setVisibility(View.GONE);
                }

            }
        }, Looper.getMainLooper());
    }

    private void FetchAdressFromLatLOng  (Location location){
        Intent intent = new Intent(this, FetchAdress.class);
        intent.putExtra(Constants.RECEIVER, resultReceiver);
        intent.putExtra(Constants.LOCATION_DATA_EXTRA, location);
        startService(intent);
    }

    private class AddressResultReceiver extends ResultReceiver{

         AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            if(resultCode == Constants.SUCCESS_RESULT){
                textAdress.setText((resultData.getString(Constants.RESULT_DATA_KEY)));
            }else{
                Toast.makeText(Aj_reclamation.this, resultData.getString(Constants.RESULT_DATA_KEY),Toast.LENGTH_SHORT).show();
            }
        }
    }
}