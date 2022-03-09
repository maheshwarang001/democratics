package com.example.democratics.ApiCOI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiUtilityCoi {
    public static Retrofit retrofit = null;

    public static ApiCoi apiCoi() {
        if (retrofit == null) {

            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(ApiCoi.BASE_URl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiCoi.class);
    }
}
