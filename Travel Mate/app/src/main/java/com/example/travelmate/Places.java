package com.example.travelmate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.service.controls.actions.FloatAction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Places extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        ArrayList<Place> placeList = new ArrayList<>();
        placeList.add(new Place("History", R.drawable.historys));
        placeList.add(new Place("Parks", R.drawable.park));
        placeList.add(new Place("Water Falls", R.drawable.falls));
        placeList.add(new Place("Museums", R.drawable.museum));
        placeList.add(new Place("Other", R.drawable.forest));


        PlacesAdapter placesAdapter = new PlacesAdapter(Places.this, placeList);
        ListView listView = (ListView) findViewById(R.id.places_listview);
        listView.setAdapter(placesAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Place clickedItem = placeList.get(i);
                if(clickedItem.getPlaceType().equals("History")){
                    Intent historyIntent = new Intent(Places.this, History.class);
                    startActivity(historyIntent);
                }

                else{
                    Intent intent = new Intent(Places.this, ListActivity.class);
                    intent.putExtra("placeType", clickedItem.getPlaceType());
                    startActivity(intent);
                }
            }
        });
    }
}
