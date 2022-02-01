package com.example.democratics.MyMinisters.API;

import com.example.democratics.MyMinisters.ApiDet.Count;

import retrofit2.Call;
import retrofit2.http.GET;

public interface apiInt {

    String BASE_URL = "https://nish.space/my_neta/";

    @GET("mps/2014")
    Call<Count>getList();

    @GET("mps/2014/maharashtra")
    Call<Count>getListMaha();
}
