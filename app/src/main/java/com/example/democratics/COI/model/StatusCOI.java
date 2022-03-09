package com.example.democratics.COI.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class StatusCOI {

    @SerializedName("")
    private model articles;

    public model getArticles() {
        return articles;
    }

    public void setArticles(model articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public String toString() {
        return "StatusCOI{" +
                "articles=" + articles +
                '}';
    }
}
