package com.example.marvelanddc;

import android.widget.ImageView;
import android.widget.TextView;

public class Poster {
    private int mimageView;

    /* refers to the image view */

    private String mmovieName;

    /* movie name */

    private String mlink;

    /* Link of the trailer */

    public Poster(String name, int image, String link){
        mimageView = image;
        mmovieName = name;
        mlink = link;
    }
    /* Constructor for Poster class*/

    public String getName(){
        return mmovieName;
    }
    /* returns name of the movie */

    public int getImageId(){
        return mimageView;
    }
    /* returns image id of poster */

    public String getLink(){
        return mlink;
    }
    /* returns link of the movie */

}
