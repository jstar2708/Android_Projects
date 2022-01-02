package com.example.musicplayer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PlaySong extends AppCompatActivity {


    Intent intent;

    private AudioManager audioManager;

    MediaPlayer mediaPlayer;

    SeekBar seekBar;

    boolean isPaused = false;

    int currentSong;

    int lastPosition;

    ArrayList<AudioModel> audioList;


    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if(focusChange == AudioManager.AUDIOFOCUS_GAIN_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK ){
                        mediaPlayer.pause();
                        Toast.makeText(PlaySong.this, "Audio Focus Gone", Toast.LENGTH_SHORT).show();
                    }
                    else if(focusChange == AudioManager.AUDIOFOCUS_GAIN){

                        mediaPlayer.seekTo(0);
                        Toast.makeText(PlaySong.this, "Audio Focus Gained", Toast.LENGTH_SHORT).show();
                    }
                    else if(focusChange == AudioManager.AUDIOFOCUS_LOSS){
                        lastPosition = mediaPlayer.getCurrentPosition();
                        mediaPlayer.pause();
                        mediaPlayer.reset();
                        play(currentSong);
                        Toast.makeText(PlaySong.this, "Audio Focus Gone Long", Toast.LENGTH_SHORT).show();
                    }
                }
            };

    public void play(int position){
        String path = audioList.get(position).getaPath();
        mediaPlayer = MediaPlayer.create(PlaySong.this, Uri.parse(path));
    }

    MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            mediaPlayer.release();
            audioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
            int result = audioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
            if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                play(currentSong);
                isPaused = true;
                Toast.makeText(PlaySong.this, "PlayBack Done!", Toast.LENGTH_SHORT).show();
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);

        intent = getIntent();
        currentSong = intent.getIntExtra("File index", 0);

        audioList = getAllAudioFromDevice(PlaySong.this);

        TextView songTitle = (TextView) findViewById(R.id.song_title);
        songTitle.setText(audioList.get(currentSong).getaName());

        TextView songDuration = (TextView) findViewById(R.id.timer2);
        songDuration.setText(timerConversion(Long.parseLong(audioList.get(currentSong).getaDuration())));

        ImageView next = (ImageView) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSong++;
                mediaPlayer.reset();
                play(currentSong);
                mediaPlayer.start();
            }
        });

        ImageView previous = (ImageView) findViewById(R.id.previous);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSong--;
                mediaPlayer.reset();
                play(currentSong);
                mediaPlayer.start();
            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        int result = audioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);

        if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
            play(currentSong);
            mediaPlayer.setLooping(true);
            seekBar = (SeekBar) findViewById(R.id.seekbar);
            seekBar.setMax(mediaPlayer.getDuration() / 1000);

            mediaPlayer.start();
        }

        ImageView playButton = (ImageView) findViewById(R.id.play_two);
        playButton.setImageResource(R.drawable.pause_two);

        TextView songStart = (TextView) findViewById(R.id.timer);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(mediaPlayer != null && fromUser){
                    mediaPlayer.seekTo(progress * 1000);
                }
                if(progress == mediaPlayer.getDuration() / 1000){
                    mediaPlayer.setOnCompletionListener(onCompletionListener);
                    playButton.setImageResource(R.drawable.play_two);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        Handler mHandler = new Handler();
        //Make sure you update Seekbar on UI thread
        PlaySong.this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if(mediaPlayer != null){
                    int mCurrentPosition = mediaPlayer.getCurrentPosition() / 1000;
                    seekBar.setProgress(mCurrentPosition);
                    String actualDurationMinutes = timerConversion(mediaPlayer.getCurrentPosition());
                    songStart.setText(actualDurationMinutes);

                }
                mHandler.postDelayed(this,0);
            }
        });


        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPaused){
                    playButton.setImageResource(R.drawable.pause_two);
                    mediaPlayer.start();
                    isPaused = false;
                }
                else{
                    playButton.setImageResource(R.drawable.play_two);
                    mediaPlayer.pause();
                    isPaused = true;
                }
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    public ArrayList<AudioModel> getAllAudioFromDevice(final Context context) {
        final ArrayList<AudioModel> tempAudioList = new ArrayList<>();

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Audio.AudioColumns.DATA,MediaStore.Audio.AudioColumns.TITLE ,MediaStore.Audio.AudioColumns.ALBUM, MediaStore.Audio.ArtistColumns.ARTIST, MediaStore.Audio.AudioColumns.DURATION};
        Cursor c = context.getContentResolver().query(uri, projection, null , null , null);

        if (c != null) {
            while (c.moveToNext()) {
                // Create a model object.
                AudioModel audioModel = new AudioModel();

                String path = c.getString(0);   // Retrieve path.
                String name = c.getString(1);   // Retrieve name.
                String album = c.getString(2);  // Retrieve album name.
                String artist = c.getString(3); // Retrieve artist name.
                String duration = c.getString(4); //Retrieve duration

                // Set data to the model object.
                audioModel.setaName(name);
                audioModel.setaAlbum(album);
                audioModel.setaArtist(artist);
                audioModel.setaPath(path);
                audioModel.setaDuration(duration);

                Log.e("Name :" + name, " Album :" + album);
                Log.e("Path :" + path, " Artist :" + artist);

                // Add the model object to the list .
                tempAudioList.add(audioModel);
            }
            c.close();
        }
        // Return the list.
        return tempAudioList;
    }

    //time conversion
    public String timerConversion(long value) {
        String audioTime;
        int dur = (int) value;
        int hrs = (dur / 3600000);
        int mns = (dur / 60000) % 60000;
        int scs = dur % 60000 / 1000;

        if (hrs > 0) {
            audioTime = String.format("%02d:%02d:%02d", hrs, mns, scs);
        } else {
            audioTime = String.format("%02d:%02d", mns, scs);
        }
        return audioTime;
    }
}