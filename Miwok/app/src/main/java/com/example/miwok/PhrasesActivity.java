package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_phrases);

        //setting up audio manager for getting audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        ArrayList<Word> phrasesEng = new ArrayList<Word>();
        phrasesEng.add(new Word("Where are you" +
                "going?","minto wuksus",-1, R.raw.phrase_where_are_you_going));
        phrasesEng.add(new Word("What is your" +
                "name?","tinnә oyaase'nә",-1, R.raw.phrase_what_is_your_name));
        phrasesEng.add(new Word("My name is...","oyaaset...",-1, R.raw.phrase_my_name_is));
        phrasesEng.add(new Word("How are you" +
                "feeling?","michәksәs?",-1, R.raw.phrase_how_are_you_feeling));
        phrasesEng.add(new Word("I’m feeling good.","kuchi achit",-1, R.raw.phrase_im_feeling_good));
        phrasesEng.add(new Word("Are you coming?","әәnәs'aa?",-1, R.raw.phrase_are_you_coming));
        phrasesEng.add(new Word("Yes, I’m coming.","hәә’ әәnәm",-1, R.raw.phrase_yes_im_coming));
        phrasesEng.add(new Word("I’m coming","әәnәm",-1, R.raw.phrase_im_coming));
        phrasesEng.add(new Word("Let’s go.","yoowutis",-1, R.raw.phrase_lets_go));
        phrasesEng.add(new Word("Come here.","әnni'nem",-1, R.raw.phrase_come_here));

        WordAdapter phrasesAdapter = new WordAdapter(this, phrasesEng,R.color.light_blue_500);

        ListView listView = (ListView) findViewById(R.id.phrase_list_view);

        listView.setAdapter(phrasesAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word currentWord = phrasesEng.get(i);

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    //startplaybacks
                    releaseMediaPlayer();
                    player = MediaPlayer.create(PhrasesActivity.this,currentWord.getAudioId());
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