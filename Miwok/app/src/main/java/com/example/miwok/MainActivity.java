package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Find the view that shows numbers
        TextView numbers = (TextView) findViewById(R.id.number_text_view);
        //Attaching the event Listener to textview
        numbers.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent numberIntent = new Intent(MainActivity.this,NumberActivity.class);
                startActivity(numberIntent);
            }
        });

        TextView family = (TextView) findViewById(R.id.family_text_view);

        family.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent familyIntent = new Intent(MainActivity.this,FamilyActivity.class);
                startActivity(familyIntent);
            }
        });

        TextView colors = (TextView) findViewById(R.id.color_text_view);
        colors.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent colorsIntent = new Intent(MainActivity.this, ColorsActivity.class);
                startActivity(colorsIntent);
            }
        });

        TextView phrases = (TextView) findViewById(R.id.phrase_text_view);
        phrases.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent phrasesIntent = new Intent(MainActivity.this, PhrasesActivity.class);
                startActivity(phrasesIntent);
            }
        });

    }

    public void openNumbersList(View view){
        Intent numbersList = new Intent(this, NumberActivity.class);
        startActivity(numbersList);
    }
    public void openFamilyList(View view){
        Intent familyList = new Intent(this, FamilyActivity.class);
        startActivity(familyList);
    }
    public void openColorsList(View view){
        Intent colorsList = new Intent(this, ColorsActivity.class);
        startActivity(colorsList);
    }
    public void openPhrasesList(View view){
        Intent phrasesList = new Intent(this, PhrasesActivity.class);
        startActivity(phrasesList);
    }
}