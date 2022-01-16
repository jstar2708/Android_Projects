package com.example.quakereport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class EarthQuake_Activity extends AppCompatActivity {

    private static final String LOG_TAG = EarthQuake_Activity.class.getName();

    /**  Url for a USGS query */

    private static final String urlString = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=5&limit=15";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earth_quake);

        EarthQuakeAsyncTask newObj = new EarthQuakeAsyncTask();
        newObj.execute(urlString);
    }

    private class EarthQuakeAsyncTask extends AsyncTask<String, Void, ArrayList<Data>>{

        //Do this in background
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

            return QueryUtils.extractEarthquakes(jsonResponse);
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

            earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Uri url = Uri.parse(earthquakes.get(position).getUrl());
                    Intent urlIntent = new Intent(Intent.ACTION_VIEW, url);
                    startActivity(urlIntent);
                }
            });
        }
    }
}