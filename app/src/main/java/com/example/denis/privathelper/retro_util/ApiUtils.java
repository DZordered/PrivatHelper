package com.example.denis.privathelper.retro_util;


import com.example.denis.privathelper.pojos.AtmResponse;
import com.example.denis.privathelper.pojos.GeometryData;
import com.example.denis.privathelper.pojos.Location;
import com.example.denis.privathelper.pojos.Statement;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiUtils {

    @GET("/p24api/pboffice?json")
    Call<ArrayList<Statement>> getStatementsFromApi(@Query("city") String city,
                                                    @Query("address") String address);

    @GET("maps/api/geocode/json")
    Call<GeometryData> getGeoData(@Query("address") String address,
                                  @Query("key") String key);

    @GET("/p24api/infrastructure?json&atm")
    Call<AtmResponse> getAtms(@Query("address") String address,
                              @Query("city") String city);

}
