package com.example.marvelanddc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView marvelView = (ImageView) findViewById(R.id.marvel_image_view);
        marvelView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MarvelMovies.class);
                startActivity(intent);
            }
        });

        ImageView dcView = (ImageView) findViewById(R.id.dc_image_view);
        dcView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DcMovies.class);
                startActivity(intent);
            }
        });
    }
}