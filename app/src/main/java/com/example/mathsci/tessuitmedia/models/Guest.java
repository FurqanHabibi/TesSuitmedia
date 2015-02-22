package com.example.mathsci.tessuitmedia.models;

import android.graphics.drawable.Drawable;

/**
 * Created by Mathsci on 2/22/2015.
 */
public class Guest {
    private int id;
    private String name;
    private String birthdate;
    private Drawable image;

    public Guest() {}

    public Guest(int id, String name, String birthdate, Drawable image) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.image = image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public Drawable getImage() {
        return image;
    }
}
