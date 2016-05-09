package com.example.denis.privathelper.activities;

import android.app.Activity;
import android.os.Bundle;

import com.example.denis.privathelper.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapLoader extends Activity implements OnMapReadyCallback {

    private String [] geoValues;
    private String  latitude;
    private String  longtitude;

    private GoogleMap gMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_fragment);

        setFieldsFromIntent();

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        gMap = googleMap;
        LatLng currentStatement = new LatLng(Double.parseDouble(latitude),Double.parseDouble(longtitude));

        gMap.addMarker(new MarkerOptions().position(currentStatement).title("Current statement"));
        gMap.moveCamera(CameraUpdateFactory.newLatLng(currentStatement));
    }

    public void setFieldsFromIntent(){
        geoValues = getIntent().getStringArrayExtra("geoDataValues");
        latitude = geoValues[0];
        longtitude = geoValues[1];
    }
}
