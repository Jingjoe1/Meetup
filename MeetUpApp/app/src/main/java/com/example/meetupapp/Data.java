package com.example.meetupapp;

/**
 * Created by Not_Today on 5/20/2016.
 */
public class Data {
    public String title;
    public String description;
    public int imageId;
    public String location;

    Data(String title, String description, int imageId,String location) {
        this.title = title;
        this.description = description;
        this.imageId = imageId;
        this.location = location;
    }

}