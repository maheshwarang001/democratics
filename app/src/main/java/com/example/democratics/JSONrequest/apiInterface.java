package com.example.democratics.JSONrequest;

import com.example.democratics.ApiDetails.Status;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface apiInterface {

    String BASE_URL = "https://newsapi.org/v2/";


    @GET("top-headlines")
    Call<Status>getNews(
        @Query("country") String country,
        @Query("pageSize") int pagesize,
        @Query("apiKey") String apikey
        );

    @GET("top-headlines")
    Call<Status>getCategroryNews(
            @Query("country") String country,
            @Query("category") String category,
            @Query("pageSize") int pageSize,
            @Query("apiKey") String apikey
    );
}
