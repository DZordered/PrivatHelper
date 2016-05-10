package com.example.denis.privathelper.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.denis.privathelper.MainActivity;
import com.example.denis.privathelper.R;
import com.example.denis.privathelper.adapter.AtmAdapter;
import com.example.denis.privathelper.pojos.AtmDevice;

import java.util.ArrayList;
import java.util.List;

public class InfoAtmLaunch extends Activity {

    private ListView atmDevicesList;

    private ArrayList<AtmDevice> devices;

    private AtmAdapter atmAdapter;

    private Button toMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_atm_launch);

        setComponents();

    }

    public void setComponents(){
        atmDevicesList = (ListView) findViewById(R.id.atmDevicesList);
        devices = getIntent().getExtras().getParcelableArrayList("atmList");
        atmAdapter = new AtmAdapter(this, R.layout.atm_items, devices);
        atmDevicesList.setAdapter(atmAdapter);
        atmDevicesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String lat = devices.get(position).latitude;
                String lng = devices.get(position).longitude;

                toMap(lat, lng);

            }
        });

        toMain = (Button) findViewById(R.id.toMainFromAtmInfo);
        toMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMainActivity();
            }
        });
    }

    public void toMainActivity(){
        startActivity(new Intent(this, MainActivity.class));
    }

    public void toMap(String lat, String lng){
        startActivity(new Intent(this, MapLoader.class).putExtra("geoDataValues", new String[] {lat, lng}));
    }
}
