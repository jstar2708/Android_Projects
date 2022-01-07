package com.example.newmiwok;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MiwokAdapter extends ArrayAdapter<ContentList> {

    MiwokAdapter(Context context, ArrayList<ContentList> list){
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;
        if(listView == null){
            listView = LayoutInflater.from(getContext()).inflate(R.layout.my_layout,parent,false);
        }
        ContentList currentContent = getItem(position);

        TextView contentEng = listView.findViewById(R.id.content_eng_text);
        contentEng.setText(currentContent.getEnglish());

        TextView contentMiwok = listView.findViewById(R.id.content_miwok_text);
        contentMiwok.setText(currentContent.getMiwok());

        ImageView contentImage = listView.findViewById(R.id.content_image);
        contentImage.setImageResource(currentContent.getImage());

        return listView;
    }
}
