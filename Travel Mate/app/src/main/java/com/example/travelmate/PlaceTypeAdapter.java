package com.example.travelmate;

import android.app.Activity;
import android.content.Context;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PlaceTypeAdapter extends ArrayAdapter<String> {

    public PlaceTypeAdapter(Activity context, ArrayList<String> placesNames){
        super(context, 0, placesNames);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;
        if(listView == null){
            listView = LayoutInflater.from(getContext()).inflate(R.layout.simplelist,parent,false);
        }

        String currentPlaceType = getItem(position);

        TextView textView = (TextView) listView.findViewById(R.id.placeText);
        textView.setText(currentPlaceType);

        return listView;


    }
}
