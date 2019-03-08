package com.example.rijskviewer.beans;

public class Artist {
    private String author;
    private String artWorkNumber;

    public Artist(){
        super();
    }

    //Getters
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getArtWorkNumber() {
        return artWorkNumber;
    }

    public void setArtWorkNumber(String artWorkNumber) {
        this.artWorkNumber = artWorkNumber;
    }
}
