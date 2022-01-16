package com.example.quakereport;

public class Data {

    // Magnitude of the earthquake.
    private final float magnitude;

    //City and country in which the earthquake occurred.
    private final String city;

    //Location offset of the earthquake
    private final String nearDistance;

    //Date on which earthquake occurred.
    private final String date;

    //Time at which earthquake occurred.
    private final String time;

    //Url of the USGS website which consists of earthquake details.
    private final String url;

    //Constructor for Data class.
    public Data(float magnitude, String city, String nearDistance, String date, String time, String url){
        this.magnitude = magnitude;
        this.city = city;
        this.date = date;
        this.time = time;
        this.nearDistance = nearDistance;
        this.url = url;
    }
    //Returns the city at which the earthquake occurred.
    public String getCity() {
        return city;
    }

    //Returns the magnitude of the earthquake/
    public float getMagnitude() {
        return magnitude;
    }

    //Returns the date on which the earthquake occurred.
    public String getDate() {
        return date;
    }

    //Returns the time at which the earthquake occurred.
    public String getTime(){ return time; }

    //Returns the location offset of the earthquake.
    public String getNearDistance(){ return nearDistance; }

    //Returns the url of the USGS website which consists of the earthquake details.
    public String getUrl() { return url; }
}
