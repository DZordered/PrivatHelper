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
import com.example.denis.privathelper.adapter.TerminalsAdapter;
import com.example.denis.privathelper.pojos.TerminalDevice;

import java.util.ArrayList;

public class TerminalsInfoActivity extends Activity {

    private ListView terminalsListView;

    private ArrayList<TerminalDevice> devices;

    private TerminalsAdapter terminalsAdapter;

    private Button toMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminals_info);
    }

    public void setComponents(){
        toMain = (Button) findViewById(R.id.backButtonFromTerminalsInfo);
        toMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TerminalsInfoActivity.this, MainActivity.class));
            }
        });
        devices = getIntent().getExtras().getParcelableArrayList("terminalsList");
        terminalsListView = (ListView) findViewById(R.id.terminalsList);
        terminalsAdapter = new TerminalsAdapter(this, R.layout.terminal_items, devices);
        terminalsListView.setAdapter(terminalsAdapter);
        terminalsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String lat = devices.get(position).latitude;
                String lng = devices.get(position).longitude;

                toMap(lat, lng);
            }
        });
    }

    public void toMap(String lat, String lng){
        startActivity(new Intent(this, MapLoader.class).putExtra("geoDataValues", new String[] {lat, lng}));
    }
}
