package com.example.democratics.ApiDetails;

import java.util.ArrayList;

public class Status {
    private int totalResults;
    private String status;
    private ArrayList<ModelClass> articles;


    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<ModelClass> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<ModelClass> articles) {
        this.articles = articles;
    }

    public Status( int totalResults, String status, ArrayList<ModelClass> results) {
        this.totalResults = totalResults;
        this.status = status;
        this.articles = results;
    }


}
