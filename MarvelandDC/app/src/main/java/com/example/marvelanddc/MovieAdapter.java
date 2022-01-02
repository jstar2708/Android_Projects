package com.example.marvelanddc;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends ArrayAdapter<Poster> {
    public MovieAdapter(Activity context, ArrayList<Poster> moviesList){
        super(context,0,  moviesList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;
        if(listView == null){
            listView = LayoutInflater.from(getContext()).inflate(R.layout.my_layout,parent,false);
        }

        Poster currentPoster = getItem(position);

        ImageView imageView = (ImageView) listView.findViewById(R.id.poster_view_1);
        imageView.setImageResource(currentPoster.getImageId());

        TextView textView = (TextView) listView.findViewById(R.id.text_view);
        textView.setText(currentPoster.getName());

        return listView;
    }
}
