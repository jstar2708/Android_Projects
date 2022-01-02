package com.example.musicplayer;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class audioAdapter extends ArrayAdapter<AudioModel> {


    public audioAdapter(Activity activity, ArrayList<AudioModel> audioList) {
        super(activity, 0, audioList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;
        if(listView == null){
            listView = LayoutInflater.from(getContext()).inflate(R.layout.mylayout,parent,false);
        }
        AudioModel currentItem = getItem(position);
        TextView songName = (TextView) listView.findViewById(R.id.song_name);
        TextView songDuration = (TextView) listView.findViewById(R.id.song_duration);


        songDuration.setText(timerConversion(Long.parseLong(currentItem.getaDuration())));
        songName.setText(currentItem.getaName());

        return listView;
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
