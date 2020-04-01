package com.example.tema_i;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CoordinatesActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinates);

        textView = findViewById(R.id.textViewC);

        ActivityCompat.requestPermissions(CoordinatesActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);
    }

    public void show(View v){
        GPSCoordinates gps = new GPSCoordinates(getApplicationContext());
        Location location = gps.getLocation();

        textView.setText("Latitude :" + location.getLatitude() + "\nLongitude :" + location.getLongitude());
    }
}
