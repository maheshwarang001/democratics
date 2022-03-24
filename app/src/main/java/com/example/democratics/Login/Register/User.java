package com.example.democratics.Login.Register;

public class User {

    private String name;
    private String uid;
    private String number;
    private String imageurl;
    private String mail;
    private String bio;



    public User(String name, String uid, String number, String imageurl, String mail, String bio) {
        this.name = name;
        this.uid = uid;
        this.number = number;
        this.imageurl = imageurl;
        this.mail = mail;
        this.bio = bio;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
