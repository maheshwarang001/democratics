package com.example.democratics.ApiCOI;

import com.example.democratics.COI.model.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiCoi
{
    String BASE_URl = "https://raw.githubusercontent.com/civictech-India/constitution-of-india/main/";

    @GET("constitution_of_india.json")
    Call<List<model>> getList();

}
