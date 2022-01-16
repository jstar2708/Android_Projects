package com.example.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;


//An Adapter class to display the Data objects on a listview.

public class QuakeAdapter extends ArrayAdapter<Data> {

    public QuakeAdapter(@NonNull Context context, int resource, ArrayList<Data> list) {
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;
        if(listView == null){
            listView = LayoutInflater.from(getContext()).inflate(R.layout.mylayout,parent,false);
        }
        //The current Data item.
        Data currentData = getItem(position);

        //TextView object referring to the magnitude textview in xml layout.
        TextView magnitude = listView.findViewById(R.id.magnitude);

        //Set the magnitude of the earthquake.
        magnitude.setText(String.valueOf(currentData.getMagnitude()));

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentData.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        //TextView object referring to the city textview in xml layout.
        TextView city = listView.findViewById(R.id.city);

        //Set the city at which earthquake occurred.
        city.setText(currentData.getCity());

        //TextView object referring to the date textview in xml layout.
        TextView date = listView.findViewById(R.id.date);

        //Set the date of the earthquake.
        date.setText(currentData.getDate());

        //TextView object referring to the time textview in xml layout.
        TextView time = listView.findViewById(R.id.time);

        //Set the time of occurrence of the earthquake.
        time.setText(currentData.getTime());

        //TextView object referring to the near location textview in xml layout.
        TextView nearLocation = listView.findViewById(R.id.nearlocation);

        //Set the location offset of the earthquake.
        nearLocation.setText(currentData.getNearDistance());

        return listView;
    }

    /*
    Function to select appropriate color for the magnitude textview background.
    This selection is based on the magnitude of the earthquake.
     */
    private int getMagnitudeColor(double magnitude){
        int intMagnitude = (int) Math.floor(magnitude);
        switch (intMagnitude){
            case 1:
                intMagnitude = R.color.magnitude1;
                break;
            case 2:
                intMagnitude = R.color.magnitude2;
                break;
            case 3:
                intMagnitude = R.color.magnitude3;
                break;
            case 4:
                intMagnitude = R.color.magnitude4;
                break;
            case 5:
                intMagnitude = R.color.magnitude5;
                break;
            case 6:
                intMagnitude = R.color.magnitude6;
                break;
            case 7:
                intMagnitude = R.color.magnitude7;
                break;
            case 8:
                intMagnitude = R.color.magnitude8;
                break;
            case 9:
                intMagnitude = R.color.magnitude9;
                break;
            case 10:
                intMagnitude = R.color.magnitude10plus;
                break;
            default:
                intMagnitude = 0;
        }

        //Returning the actual value of color.
        return  ContextCompat.getColor(getContext(), intMagnitude);
    }

}
