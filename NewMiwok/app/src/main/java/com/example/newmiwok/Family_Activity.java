package com.example.newmiwok;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Family_Activity extends AppCompatActivity {

    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);

        ArrayList<ContentList> familyList = new ArrayList<>();
        familyList.add(new ContentList("father", "әpә", R.drawable.family_father, R.raw.family_father));
        familyList.add(new ContentList("mother", "әṭa", R.drawable.family_mother, R.raw.family_mother));
        familyList.add(new ContentList("son", "angsi", R.drawable.family_son, R.raw.family_son));
        familyList.add(new ContentList("daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter));
        familyList.add(new ContentList("older brother", "taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        familyList.add(new ContentList("younger brother", "chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        familyList.add(new ContentList("older sister", "teṭe", R.drawable.family_older_sister, R.raw.family_older_sister));
        familyList.add(new ContentList("younger sister", "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        familyList.add(new ContentList("grandmother", "ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        familyList.add(new ContentList("grandfather", "paapa", R.drawable.family_grandfather, R.raw.family_grandfather));


        ListView familyView = (ListView) findViewById(R.id.family_listview);
        MiwokAdapter familyAdapter = new MiwokAdapter(this, familyList);
        familyView.setAdapter(familyAdapter);

        familyView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int audio = familyList.get(position).getAudio();
                play(audio);
            }
        });

    }

    public void play(int audio){
        MediaPlayerRelease();
        player = MediaPlayer.create(Family_Activity.this, audio);
        player.start();
    }

    public void MediaPlayerRelease(){
        if(player == null){
            return;
        }
        player.release();
    }
}