package com.example.quakereport;

public class Data {

    // Richter scale value of the earthquake.
    private final float richterScale;

    //City in which the earthquake occurred.
    private final String city;

    //Date on which earthquake occurred.
    private final String date;

    //Constructor for Data class.
    public Data(float richterScale, String city, String date){
        this.richterScale = richterScale;
        this.city = city;
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public float getRichterScale() {
        return richterScale;
    }

    public String getDate() {
        return date;
    }
}
