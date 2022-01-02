package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaActionSound;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NumberActivity extends AppCompatActivity {

    MediaPlayer player;

    private AudioManager mAudioManager;

    private MediaPlayer.OnCompletionListener mOncompletion = new MediaPlayer.OnCompletionListener() {
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
        setContentView(R.layout.activity_number);
        //setting up audio manager for getting audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //Adding words for numbers
        ArrayList<Word> numbersEng = new ArrayList<Word>();
        numbersEng.add(new Word("one","lutti",R.drawable.number_one));
        numbersEng.add(new Word("two","otiiko",R.drawable.number_two,R.raw.number_two));
        numbersEng.add(new Word("three","tolookosu",R.drawable.number_three, R.raw.number_three));
        numbersEng.add(new Word("four","oyyisa",R.drawable.number_four, R.raw.number_four));
        numbersEng.add(new Word("five","massokka",R.drawable.number_five, R.raw.number_five));
        numbersEng.add(new Word("six","temmokka",R.drawable.number_six, R.raw.number_six));
        numbersEng.add(new Word("seven","kenekaku",R.drawable.number_seven, R.raw.number_seven));
        numbersEng.add(new Word("eight","kawinta",R.drawable.number_eight, R.raw.number_eight));
        numbersEng.add(new Word("nine","wo'e",R.drawable.number_nine, R.raw.number_nine));
        numbersEng.add(new Word("ten","na'aacha",R.drawable.number_ten, R.raw.number_ten));

        WordAdapter Adapter = new WordAdapter(this,numbersEng,R.color.orange);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(Adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word currentWord = numbersEng.get(i);

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    //startplaybacks
                    releaseMediaPlayer();
                    player = MediaPlayer.create(NumberActivity.this,currentWord.getAudioId());
                    player.start();
                    player.setOnCompletionListener(mOncompletion);
                }

            }
        });

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

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}