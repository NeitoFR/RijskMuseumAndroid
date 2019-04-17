package com.example.rijskviewer.beans;

public class ArtWork extends Artist {
    private String title;
    private String date;
    private String image;
    private int index;

    public ArtWork(){
        super();
    }

    //Getters and setters
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return super.toString() + " " + this.title + " " + this.date + " " + this.image + " " + this.index;
    }
}