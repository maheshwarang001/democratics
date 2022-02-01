package com.example.democratics.News.ApiDetails;

public class ModelClass {

    private String content;
    private String author;
    private String description;
    private String urlToImage;
    private String url;
    private String publishedAt;
   // private String source;
    private SourceModel source;
    private String title;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public SourceModel getSource() {
        return source;
    }

    public void setSource(SourceModel source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public ModelClass(String content, String creator, String description,String image_url, String link, String pubDate, SourceModel source_id, String title) {
        this.content = content;
        this.author = creator;
        this.description = description;
        this.urlToImage = image_url;
        this.url = link;
        this.publishedAt = pubDate;
        this.source = source_id;
        this.title = title;

    }

}
