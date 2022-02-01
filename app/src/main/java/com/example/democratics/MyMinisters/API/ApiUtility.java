package com.example.democratics.MyMinisters.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtility {

    public static Retrofit retrofit = null;

    public static apiInt apiInt(){

        if(retrofit==null){

            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(apiInt.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(apiInt.class);
    }
}
