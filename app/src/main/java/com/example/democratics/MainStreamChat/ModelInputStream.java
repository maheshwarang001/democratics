package com.example.democratics.MainStreamChat;

import android.net.Uri;

import java.net.URL;
import java.util.Date;

public class ModelInputStream {

    private String id;
    private String title;
    private String imageURL;
    private String context;
    private String date;

    public ModelInputStream(){}

    public ModelInputStream(String id, String title, String imageURL, String context, String date) {
        this.id = id;
        this.title = title;
        this.imageURL = imageURL;
        this.context = context;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
