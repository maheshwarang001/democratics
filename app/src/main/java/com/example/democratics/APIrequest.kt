package com.example.democratics

import com.example.democratics.API.NewsApiJson
import retrofit2.http.GET

interface APIrequest {


    @GET("/news?category=national")
    suspend fun getnews() : NewsApiJson

    @GET("/news?category=sports")
    suspend fun getsports() : NewsApiJson

    @GET("/news?category=business")
    suspend fun getBusiness(): NewsApiJson

    @GET("/news?category=world")
    suspend fun getWorld() : NewsApiJson

    @GET("/news?category=science")
    suspend fun getScience() : NewsApiJson

    @GET("/news?category=politics")
    suspend fun getPolitics() : NewsApiJson

}