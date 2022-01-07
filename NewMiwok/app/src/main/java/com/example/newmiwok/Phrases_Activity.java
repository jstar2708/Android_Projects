package com.example.newmiwok;

import androidx.appcompat.app.AppCompatActivity;

import android.media.CamcorderProfile;
import android.media.MediaActionSound;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Phrases_Activity extends AppCompatActivity {

    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);

        ArrayList<ContentList> phrasesList = new ArrayList<>();
        phrasesList.add(new ContentList("Where are you going?", "minto wuksus", 0, R.raw.phrase_where_are_you_going));
        phrasesList.add(new ContentList("What is your name?", "tinnә oyaase'nә", 0, R.raw.phrase_what_is_your_name));
        phrasesList.add(new ContentList("My name is...", "oyaaset...", 0, R.raw.phrase_my_name_is));
        phrasesList.add(new ContentList("How are you feeling?", "michәksәs?", 0, R.raw.phrase_how_are_you_feeling));
        phrasesList.add(new ContentList("I'm feeling good.", "kuchi achit", 0, R.raw.phrase_im_feeling_good));
        phrasesList.add(new ContentList("Are you coming?", "әәnәs'aa?", 0, R.raw.phrase_are_you_coming));
        phrasesList.add(new ContentList("Yes, I'm  coming.", "hәә’ әәnәm", 0, R.raw.phrase_yes_im_coming));
        phrasesList.add(new ContentList("I'm coming", "әәnәm", 0, R.raw.phrase_im_coming));
        phrasesList.add(new ContentList("Let's go", "yoowutis", 0, R.raw.phrase_lets_go));
        phrasesList.add(new ContentList("Come here.", "әnni'nem", 0, R.raw.phrase_come_here));

        ListView phrasesView = (ListView) findViewById(R.id.phrases_listview);
        MiwokAdapter phrasesAdapter = new MiwokAdapter(this, phrasesList);
        phrasesView.setAdapter(phrasesAdapter);

        phrasesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int audio = phrasesList.get(position).getAudio();
                play(audio);
            }
        });

    }

    public void play(int audio){
        MediaPlayerRelease();
        player = MediaPlayer.create(Phrases_Activity.this, audio);
        player.start();
    }

    public void MediaPlayerRelease(){
        if(player == null){
            return;
        }
        player.release();
    }
}