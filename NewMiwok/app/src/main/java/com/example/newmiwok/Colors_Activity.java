package com.example.newmiwok;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Colors_Activity extends AppCompatActivity {

    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        ArrayList<ContentList> colorsList = new ArrayList<>();
        colorsList.add(new ContentList("red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        colorsList.add(new ContentList("green", "chokokki", R.drawable.color_green, R.raw.color_green));
        colorsList.add(new ContentList("brown", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        colorsList.add(new ContentList("gray", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        colorsList.add(new ContentList("black", "kululli", R.drawable.color_black, R.raw.color_black));
        colorsList.add(new ContentList("white", "kelelli", R.drawable.color_white, R.raw.color_white));
        colorsList.add(new ContentList("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        colorsList.add(new ContentList("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

        ListView colorView = findViewById(R.id.colorsView);
        MiwokAdapter colorAdapter = new MiwokAdapter(this, colorsList);
        colorView.setAdapter(colorAdapter);

        colorView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int currentAudio = colorsList.get(position).getAudio();
                play(currentAudio);
            }
        });
    }

    public void play(int audio){
        MediaPlayerRelease();
        player = MediaPlayer.create(Colors_Activity.this, audio);
        player.start();
    }

    public void MediaPlayerRelease(){
        if(player == null){
            return;
        }
        player.release();
    }
}