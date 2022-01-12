package com.example.quakereport;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthQuake_Activity extends AppCompatActivity {

    public static final String LOG_TAG = EarthQuake_Activity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earth_quake);

        // Create a fake list of earthquake locations.
        ArrayList<Data> earthquakes = new ArrayList<>();
        earthquakes.add(new Data(7.2f, "San Francisco", "Feb 2, 2016"));
        earthquakes.add(new Data(6.1f, "London", "July 20, 2015"));
        earthquakes.add(new Data(3.9f, "Tokyo", "Nov 10, 2014"));
        earthquakes.add(new Data(5.4f, "Mexico City", "May 3, 2014"));
        earthquakes.add(new Data(2.8f, "Moscow", "Jan 31, 2012"));
        earthquakes.add(new Data(4.9f, "Rio de Janeiro", "Aug 19, 2012"));

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        QuakeAdapter quakeAdapter = new QuakeAdapter(this, 0, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(quakeAdapter);
    }
}