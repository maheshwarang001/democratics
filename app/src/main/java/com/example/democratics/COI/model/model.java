package com.example.democratics.COI.model;


//https://raw.githubusercontent.com/Yash-Handa/The_Constitution_Of_India/master/COI.json


public class model
{

    private String article;
    private String title;
    private String description;


    @Override
    public String toString() {
        return "model{" +
                "article='" + article + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}