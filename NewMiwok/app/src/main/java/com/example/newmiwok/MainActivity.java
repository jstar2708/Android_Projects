package com.example.newmiwok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView numbersMain = findViewById(R.id.numbers_main);
        numbersMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Numbers_Activity.class);
                startActivity(intent);
            }
        });

        TextView colorsMain = findViewById(R.id.colors_main);
        colorsMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Colors_Activity.class);
                startActivity(intent);
            }
        });

        TextView familyMain = findViewById(R.id.family_main);
        familyMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Family_Activity.class);
                startActivity(intent);
            }
        });

        TextView phrasesMain = findViewById(R.id.phrases_main);
        phrasesMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Phrases_Activity.class);
                startActivity(intent);
            }
        });
    }
}