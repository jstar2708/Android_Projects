package com.example.miwok;

import android.app.Activity;
import android.media.MediaPlayer;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    MediaPlayer player;

    private int mColor;
    public WordAdapter(Activity context, ArrayList<Word> words,int backColor){
        super(context,0,words);
        mColor = backColor;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;
        if(listView == null){
            listView = LayoutInflater.from(getContext()).inflate(R.layout.my_layout,parent,false);
        }

        Word currentWord = getItem(position);

        TextView currentMiwok = (TextView) listView.findViewById(R.id.miwok_text_view);
        currentMiwok.setText(currentWord.getMiwokTranslation());

        TextView currentDefault = (TextView) listView.findViewById(R.id.english_text_view);
        currentDefault.setText(currentWord.getDefalutTranslation());

        ImageView currentImage = (ImageView) listView.findViewById(R.id.image_app_view);
        if(currentWord.hasImage()){
            currentImage.setImageResource(currentWord.getImageID());
        }
        else{
            currentImage.setVisibility(View.GONE);
        }
        ImageView currentPlay = (ImageView) listView.findViewById(R.id.play_button);
        currentPlay.setImageResource(R.drawable.outline_play_circle_filled_24);
        //Adding color to background of each textView in Miwok app
        View viewContainer =  listView.findViewById(R.id.text_view);
        int color = ContextCompat.getColor(getContext(), mColor);
        viewContainer.setBackgroundColor(color);
        return listView;
    }
}
