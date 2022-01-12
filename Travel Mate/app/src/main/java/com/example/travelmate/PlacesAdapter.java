package com.example.travelmate;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PlacesAdapter extends ArrayAdapter<Place> {

    public PlacesAdapter(Activity context, ArrayList<Place> placesList){
        super(context, 0, placesList);


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;

        if(listView == null){
            listView = LayoutInflater.from(getContext()).inflate(R.layout.mylayout,parent,false);
        }

        Place currentPlace = getItem(position);

        ImageView currentImage = (ImageView) listView.findViewById(R.id.places_image);
        TextView currentPlaceType = (TextView) listView.findViewById(R.id.placetype);

        currentImage.setImageResource(currentPlace.getImageID());

        currentPlaceType.setText(currentPlace.getPlaceType());
        return listView;

    }



}
