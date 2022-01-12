package com.example.travelmate;

import android.app.Activity;

public class Place {
    //Image ID of the place type
    private int mImageid;

    //Name of the place type
    private String mPlaceType;

    //Constructor for Place
    public Place(String name, int imageId){
        mPlaceType = name;
        mImageid = imageId;
    }
    //Returns the imageid of the Place object
    public int getImageID(){
        return mImageid;
    }
    //Returns the Place type of the Place object
    public String getPlaceType(){
        return mPlaceType;
    }

}
