package com.example.denis.privathelper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.Toast;

import com.example.denis.privathelper.activities.InfoAtmLaunch;
import com.example.denis.privathelper.activities.InfoLaunchActivity;
import com.example.denis.privathelper.activities.TerminalsInfoActivity;
import com.example.denis.privathelper.pojos.AtmDevice;
import com.example.denis.privathelper.pojos.AtmResponse;
import com.example.denis.privathelper.pojos.Statement;
import com.example.denis.privathelper.pojos.TerminalDevice;
import com.example.denis.privathelper.pojos.TerminalResponse;
import com.example.denis.privathelper.retro_util.ApiUtils;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Main activity what provide opportunity to edit
 * address and city (or just city) and find departments,
 * ATM's and terminals of PrivatBank.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Base URL of PrivatBank api
     */
    public static final String PRIVAT_API= "https://api.privatbank.ua";

    /**
     * The name of the city, which the user enters to find departments in it
     */
    private EditText editCity;
    /**
     * The address in city (street)
     */
    private EditText editAddress;
    /**
     * The name of the city, which the user enters to find terminals in it
     */
    private EditText editAtmCity;
    /**
     * The address in city (street)
     */
    private EditText editAtmAddress;

    private EditText editTerminalCity;

    private EditText editTerminalAddress;

    /**
     * Get all departments on click
     */
    private Button getStatements;

    /**
     * Get all ATM's on click
     */
    private Button getAtms;

    private Button getTerminals;

    private Button toTerminalInfo;
    /**
     * Go to info about (list of) ATM's
     */
    private Button toAtmInfo;
    /**
     * Go to info about (list of) departments
     */
    private Button toInfo;

    /**
     * List of Statements(departments, of course) what we get from response
     */
    private ArrayList<Statement> statementList;
    /**
     * List of ATM devices what we get from response
     */
    private ArrayList<AtmDevice> atmDevices;
    /**
     * High hierarchy object, what we get from response
     */
    private AtmResponse atmResponse;

    private TerminalResponse terminalResponse;

    private ArrayList<TerminalDevice> terminalDevices;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set view components
        setComponents();


    }

    public void setComponents(){
        editCity = (EditText) findViewById(R.id.editCity);
        editAddress = (EditText) findViewById(R.id.editAddress);
        editAtmAddress = (EditText) findViewById(R.id.editAtmAddress);
        editAtmCity = (EditText) findViewById(R.id.editAtmCity);
        editTerminalCity = (EditText) findViewById(R.id.editTerminalsCity);
        editTerminalAddress = (EditText) findViewById(R.id.editTerminalAddress);

        getStatements = (Button) findViewById(R.id.getStatementsButton);
        getStatements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // request to privat api
                getStatementsFromAPI();

            }
        });
        toInfo = (Button) findViewById(R.id.toInfoButton);
        if (toInfo != null) {
            toInfo.setEnabled(false);
        }
        toInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // go to list with our departments
                toInfoActivity();
            }
        });

        getAtms = (Button) findViewById(R.id.getAtmsButton);
        getAtms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // request to privat api
                getAtms();
            }
        });

        toAtmInfo = (Button) findViewById(R.id.toInfoAtmButton);
        if(toAtmInfo != null) {
            toAtmInfo.setEnabled(false);
        }
        toAtmInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // go to list with ATM's
                toAtmInfoActivity();
            }
        });

        getTerminals = (Button) findViewById(R.id.getTerminalsInfo);
        getTerminals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTerminals();
            }
        });

        toTerminalInfo = (Button) findViewById(R.id.toTerminalInfo);
        if(toTerminalInfo != null){
            toTerminalInfo.setEnabled(false);
        }
        toTerminalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toTerminalInfoActivity();
            }
        });
    }

    /**
     * Method what use Retrofit for getting response
     * from Privat API (in JSON output)
     */
    public void getStatementsFromAPI(){

        // Build retrofit instance with base url and gson factory
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(PRIVAT_API)
                .build();

        //Create instance of our interface what hold GET methods
        ApiUtils apiUtils = retrofit.create(ApiUtils.class);

        //Create 'Call' instance, what contains Statement(Department)
        //objects with specific fields
        Call<ArrayList<Statement>> call = apiUtils.getStatementsFromApi(editCity.getText()
                                                                    .toString(),
                                                                    editAddress.getText()
                                                                    .toString()
        );
        call.enqueue(new Callback<ArrayList<Statement>>() {
            @Override
            public void onResponse(Response<ArrayList<Statement>> response) {
                statementList = response.body();
                if(statementList != null)
                    toInfo.setEnabled(true);
            }

            @Override
            public void onFailure(Throwable t) {
                t.getStackTrace();
            }
        });

    }

    public void getAtms(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PRIVAT_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiUtils atmUtils = retrofit.create(ApiUtils.class);
        final Call<AtmResponse> atmResponseCall = atmUtils.getAtms(editAtmAddress.getText().toString(),
                                                             editAtmCity.getText().toString());
        atmResponseCall.enqueue(new Callback<AtmResponse>() {
            @Override
            public void onResponse(Response<AtmResponse> response) {
                atmResponse = response.body();

                atmDevices =(ArrayList<AtmDevice>) atmResponse.devices;

                if(atmResponse != null) toAtmInfo.setEnabled(true);
            }

            @Override
            public void onFailure(Throwable t) {
                t.getStackTrace();
            }
        });
    }

    public void getTerminals(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PRIVAT_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiUtils apiUtils = retrofit.create(ApiUtils.class);
        Call<TerminalResponse> call = apiUtils.getTerminals(editTerminalCity.getText().toString(),
                                                            editTerminalAddress.getText().toString());
        call.enqueue(new Callback<TerminalResponse>() {
            @Override
            public void onResponse(Response<TerminalResponse> response) {
                terminalResponse = response.body();

                terminalDevices =(ArrayList<TerminalDevice>) terminalResponse.devices;

                if(terminalResponse != null) toTerminalInfo.setEnabled(true);
            }

            @Override
            public void onFailure(Throwable t) {
                t.getStackTrace();
            }
        });
    }


    public void toInfoActivity(){
        startActivity(new Intent(this, InfoLaunchActivity.class).putExtra("stateList", statementList));
    }
    public void toAtmInfoActivity(){
        startActivity(new Intent(this, InfoAtmLaunch.class).putExtra("atmList", atmDevices));
    }

    public void toTerminalInfoActivity(){
        startActivity(new Intent(this, TerminalsInfoActivity.class).putExtra("terminalsList", terminalDevices));

    }
}
