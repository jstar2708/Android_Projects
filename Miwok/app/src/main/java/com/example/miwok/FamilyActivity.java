package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FamilyActivity extends AppCompatActivity {

    MediaPlayer player;
    private AudioManager mAudioManager;
    final private MediaPlayer.OnCompletionListener mOncompletion = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if(focusChange == AudioManager.AUDIOFOCUS_GAIN_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                        player.pause();
                        player.seekTo(0);
                    }
                    else if(focusChange == AudioManager.AUDIOFOCUS_GAIN){
                        player.start();
                    }
                    else if(focusChange == AudioManager.AUDIOFOCUS_LOSS){
                        releaseMediaPlayer();
                    }
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);

        //setting up audio manager for getting audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        ArrayList<Word> familyEng = new ArrayList<Word>();
        familyEng.add(new Word("father","әpә",R.drawable.family_father, R.raw.family_father));
        familyEng.add(new Word("mother","әṭa",R.drawable.family_mother, R.raw.family_mother));
        familyEng.add(new Word("son","angsi",R.drawable.family_son, R.raw.family_son));
        familyEng.add(new Word("daughter","tune",R.drawable.family_daughter, R.raw.family_daughter));
        familyEng.add(new Word("elder brother","taachi",R.drawable.family_older_brother, R.raw.family_older_brother));
        familyEng.add(new Word("younger brother","chalitti",R.drawable.family_younger_brother, R.raw.family_younger_brother));
        familyEng.add(new Word("elder sister","teṭe",R.drawable.family_older_sister, R.raw.family_older_sister));
        familyEng.add(new Word("younger sister","kolliti",R.drawable.family_younger_sister, R.raw.family_younger_sister));
        familyEng.add(new Word("grandmother","ama",R.drawable.family_grandmother, R.raw.family_grandmother));
        familyEng.add(new Word("grandfather","paapa",R.drawable.family_grandfather, R.raw.family_grandfather));

        WordAdapter familyAdapter = new WordAdapter(this, familyEng,R.color.green_500);

        ListView listView = (ListView) findViewById(R.id.family_list);

        listView.setAdapter(familyAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word currentWord = familyEng.get(i);

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    //startplaybacks
                    releaseMediaPlayer();
                    player = MediaPlayer.create(FamilyActivity.this,currentWord.getAudioId());
                    player.start();
                    player.setOnCompletionListener(mOncompletion);
                }
            }
        });


    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (player != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            player.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            player = null;

            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }



}