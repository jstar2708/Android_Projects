package com.example.quakereport;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class EarthQuake_Activity extends AppCompatActivity {

    public static final String LOG_TAG = EarthQuake_Activity.class.getName();

    /**  Url for a USGS query */

    private static final String urlString = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earth_quake);

        EarthQuakeAsyncTask newObj = new EarthQuakeAsyncTask();
        newObj.execute(urlString);

        ArrayList<Data> earthquakes = new ArrayList<>();


        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        QuakeAdapter quakeAdapter = new QuakeAdapter(this, 0, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(quakeAdapter);
    }

    private class EarthQuakeAsyncTask extends AsyncTask<String, Void, ArrayList<Data>>{

        @Override
        protected ArrayList<Data> doInBackground(String... url) {
            URL mainUrl = QueryUtils.makeUrl(url[0]);
            String jsonResponse = "";
            try{
                jsonResponse = QueryUtils.makeHTTPRequest(mainUrl);
            }
            catch (IOException e){
                Log.e(LOG_TAG, "Problem in retrieving json response");
            }

            ArrayList<Data> earthquakes = QueryUtils.extractEarthquakes(jsonResponse);
            return earthquakes;
        }

        @Override
        protected void onPostExecute(ArrayList<Data> earthquakes) {
            // Find a reference to the {@link ListView} in the layout
            ListView earthquakeListView = (ListView) findViewById(R.id.list);

            // Create a new {@link ArrayAdapter} of earthquakes
            QuakeAdapter quakeAdapter = new QuakeAdapter(EarthQuake_Activity.this, 0, earthquakes);

            // Set the adapter on the {@link ListView}
            // so the list can be populated in the user interface
            earthquakeListView.setAdapter(quakeAdapter);
        }
    }
}