package com.example.raulrcg.gm_loc_rr;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LocationListener {

    LocationManager locationManager;
    TextView lat, longi;
    String provider="";
    double la,ln;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lat = (TextView) findViewById(R.id.lat);
        longi = (TextView) findViewById(R.id.longi);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();

        provider =locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            onLocationChanged(location);
        }

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                la=Double.parseDouble(lat.getText().toString());
                ln=Double.parseDouble(longi.getText().toString());
                Intent i=new Intent(getApplicationContext(),MapsActivity.class);
                i.putExtra("la",la);
                i.putExtra("ln",ln);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onLocationChanged(Location location) {
        double lati = location.getLatitude();
        double lng = location.getLongitude();
        lat.setText(String.valueOf(lati));
        longi.setText(String.valueOf(lng));
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
    }

    @Override
    public void onProviderEnabled(String s) {
    }

    @Override
    public void onProviderDisabled(String s) {
    }
}
