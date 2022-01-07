package com.example.newmiwok;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Numbers_Activity extends AppCompatActivity {

    MediaPlayer player = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        ArrayList<ContentList> numbersList = new ArrayList<>();
        numbersList.add(new ContentList("lutti", "one", R.drawable.number_one, R.raw.number_one));
        numbersList.add(new ContentList("otiiko", "two", R.drawable.number_two, R.raw.number_two));
        numbersList.add(new ContentList("tolooosu", "three", R.drawable.number_three, R.raw.number_three));
        numbersList.add(new ContentList("oyyisa", "four", R.drawable.number_four, R.raw.number_four));
        numbersList.add(new ContentList("massokka", "five", R.drawable.number_five, R.raw.number_five));
        numbersList.add(new ContentList("temmokka", "six", R.drawable.number_six, R.raw.number_six));
        numbersList.add(new ContentList("kenekaku", "seven", R.drawable.number_seven, R.raw.number_seven));
        numbersList.add(new ContentList("kawinta", "eight", R.drawable.number_eight, R.raw.number_eight));
        numbersList.add(new ContentList("wo'e", "nine", R.drawable.number_nine, R.raw.number_nine));
        numbersList.add(new ContentList("na'aacha", "ten", R.drawable.number_ten, R.raw.number_ten));

        ListView numberView = (ListView) findViewById(R.id.numberView);
        MiwokAdapter numberAdapter = new MiwokAdapter(this, numbersList);
        numberView.setAdapter(numberAdapter);

        numberView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int currentAudio = numbersList.get(position).getAudio();
                play(currentAudio);
            }
        });
    }

    public void play(int audio){
        MediaPlayerRelease();
        player = MediaPlayer.create(Numbers_Activity.this, audio);
        player.start();
    }

    public void MediaPlayerRelease(){
        if(player == null){
            return;
        }
        player.release();
    }
}