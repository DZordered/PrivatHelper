package com.example.denis.privathelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.denis.privathelper.activities.InfoLaunchActivity;
import com.example.denis.privathelper.pojos.Statement;
import com.example.denis.privathelper.retro_util.ApiUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String PRIVAT_API= "https://api.privatbank.ua";
    private TextView statementsInfo;
    private TextView atmInfo;
    private TextView terminalsInfo;

    private EditText editCity;
    private EditText editAddress;
    private EditText editAtm;
    private EditText editTerminals;

    private Button getStatements;
    private Button getAtms;
    private Button getTerminals;
    private Button toInfo;

    public ArrayList<Statement> statementList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setComponents();


    }

    public void setComponents(){
        statementsInfo = (TextView) findViewById(R.id.stateInfo);
        editCity = (EditText) findViewById(R.id.editCity);
        editAddress = (EditText) findViewById(R.id.editAddress);
        getStatements = (Button) findViewById(R.id.getStatementsButton);
        getStatements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getStatementsFromAPI();
            }
        });
        toInfo = (Button) findViewById(R.id.toMapButton);
        if (toInfo != null) {
            toInfo.setEnabled(false);
        }
        toInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toInfoActivity();
            }
        });
    }

    public void getStatementsFromAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PRIVAT_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiUtils apiUtils = retrofit.create(ApiUtils.class);
        Call<ArrayList<Statement>> call = apiUtils.getStatementsFromApi(editCity.getText()
                                                                    .toString(),
                                                                    editAddress.getText()
                                                                    .toString()
        );
        call.enqueue(new Callback<ArrayList<Statement>>() {
            @Override
            public void onResponse(Call<ArrayList<Statement>> call,
                                   Response<ArrayList<Statement>> response) {
                statementList = response.body();
                if(statementList != null)
                    toInfo.setEnabled(true);
            }

            @Override
            public void onFailure(Call<ArrayList<Statement>> call, Throwable t) {
                t.getStackTrace();
            }
        });

        Toast.makeText(MainActivity.this, "Data is get, click on 'info', to see result "
                            , Toast.LENGTH_SHORT).show();

    }

    public void toInfoActivity(){
        startActivity(new Intent(this, InfoLaunchActivity.class).putExtra("stateList", statementList));
    }
}
