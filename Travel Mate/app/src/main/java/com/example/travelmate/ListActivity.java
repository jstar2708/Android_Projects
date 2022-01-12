package com.example.travelmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent intent = getIntent();
        String placeType = intent.getStringExtra("placeType");
        ArrayList<String> list = new ArrayList<>();
        if(placeType.equals("Parks")){
            list.add("Tagore Garden");
            list.add("Dumna Nature Reserve Park");
            list.add("Bhawartal Garden");
            list.add("Westland Park");
            list.add("Indra Park");

        }
        else if(placeType.equals("Museums")){
            list.add("Rani Durgavati Museum");
            list.add("Educational Museum");
            list.add("Rajkumari Bal ki Koth");
        }
        else if(placeType.equals("Water Falls")){
            list.add("Dhuandhar Waterfall");
            list.add("Bhadbhada Waterfall");
            list.add("Khandari Lake");
            list.add("Nidan Fall");
        }

        else{
            list.add("Guarighat");
            list.add("Sangram Sagar Lake");
            list.add("Pariyat Dham");
        }

        PlaceTypeAdapter listAdapter = new PlaceTypeAdapter(this, list);
        ListView listView = (ListView) findViewById(R.id.list_activity);
        listView.setAdapter(listAdapter);

        TextView heading = (TextView) findViewById(R.id.heading);
        heading.setText(placeType);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String currentPlace = list.get(position);
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + currentPlace + " Jabalpur");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });


    }
}