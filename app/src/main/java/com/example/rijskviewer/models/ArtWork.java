package com.example.rijskviewer.models;

public class ArtWork {
    private String author;
    private String title;
    private String date;
    private String url;
    private int index;

    public ArtWork(String author, String title, String date, String url, int index)
    {
        this.author = author;
        this.title = title;
        this.date = date;
        this.url = url;
        this.index = index;
    }
    /*public Oeuvre()
    {
        this("No Artist", "No Title", "No Date", museumAPI.class.getClassLoader().getResource("Hearthstone.jpg").toString(), 9999);
    }*/

    //Getters
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return this.author + " " + this.title + " " + this.date + " " + this.url + " " + this.index;
    }
}