package com.example.denis.privathelper.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.denis.privathelper.MainActivity;
import com.example.denis.privathelper.R;
import com.example.denis.privathelper.adapter.StatementsAdapter;
import com.example.denis.privathelper.pojos.GeometryData;
import com.example.denis.privathelper.pojos.Statement;
import com.example.denis.privathelper.retro_util.ApiUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoLaunchActivity extends Activity {

    public static final String GOOGLE_GEOCODING_API = "https://maps.googleapis.com/";

    public static final String API_KEY = "AIzaSyAtOWqrjsqMXDM9fG8LPTSznlu6Nd6OFRA";

    private Button goBack;

    private ListView statementsView;

    private ArrayList<Statement> list;

    private StatementsAdapter stAdapter;

    private GeometryData geometryData;

    private String latitude;
    private String longtitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_launch);

        list = getIntent().getParcelableArrayListExtra("stateList");

        setComponents();


    }

    public void setComponents(){
        goBack = (Button)findViewById(R.id.backButton);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InfoLaunchActivity.this, MainActivity.class));
            }
        });
        statementsView = (ListView) findViewById(R.id.listView);
        stAdapter = new StatementsAdapter(this, R.layout.statement_items, list);
        statementsView.setAdapter(stAdapter);
        statementsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getGeographicData(list.get(position).phone + ", " + list.get(position).state + ", "
                     + list.get(position).index + ", " + list.get(position).country, API_KEY);

            }
        });

    }

    public void getGeographicData(String address, String api_key){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GOOGLE_GEOCODING_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiUtils utils = retrofit.create(ApiUtils.class);
        final Call<GeometryData> geoData = utils.getGeoData(address, api_key);
        geoData.enqueue(new Callback<GeometryData>() {
            @Override
            public void onResponse(Call<GeometryData> call, Response<GeometryData> response) {
                geometryData = response.body();
                latitude = geometryData.results.get(0).geometry.location.lat;
                longtitude =geometryData.results.get(0).geometry.location.lng;

                toMap(latitude, longtitude);
            }

            @Override
            public void onFailure(Call<GeometryData> call, Throwable t) {
                t.getStackTrace();
            }
        });

    }
    public void toMap(String lat, String lng){
        startActivity(new Intent(InfoLaunchActivity.this, MapLoader.class).putExtra("geoDataValues",
                new String []{lat, lng}));
    }

}
