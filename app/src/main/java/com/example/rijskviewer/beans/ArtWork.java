package com.example.rijskviewer.beans;

public class ArtWork extends Artist {
    private Artist author;
    private String title;
    private String date;
    private String url;
    private int index;

    public ArtWork(){
        super();
    }

    //Getters
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