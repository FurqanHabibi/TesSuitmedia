package com.example.mathsci.tessuitmedia.models;

import android.graphics.drawable.Drawable;

/**
 * Created by Mathsci on 2/22/2015.
 */
public class Event {
    private String name;
    private String date;
    private Drawable image;

    public Event() {}

    public Event(String name, String date, Drawable image) {
        this.name = name;
        this.date = date;
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public Drawable getImage() {
        return image;
    }
}
