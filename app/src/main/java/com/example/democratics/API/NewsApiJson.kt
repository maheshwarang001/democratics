package com.example.democratics.API

data class NewsApiJson(
    val category: String,
    val data: List<Data>,
    val success: Boolean
)