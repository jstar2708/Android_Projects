package com.example.travelmate;

public class PlaceType {
    // Name of the place.
    private String mName;
    //Location of the place on google maps.
    private String mLocation;

    // Constructor for PlaceType class.
    public PlaceType(String name, String location){
        mName = name;
        mLocation = location;
    }

    //Returns the PlaceType of the current object.
    public String getName(){
        return mName;
    }

    // Returns the Location of the current PlaceType object.
    public String getLocation(){
        return mLocation;
    }

}
