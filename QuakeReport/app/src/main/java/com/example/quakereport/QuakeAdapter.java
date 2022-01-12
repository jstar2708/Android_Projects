package com.example.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class QuakeAdapter extends ArrayAdapter<Data> {

    public QuakeAdapter(@NonNull Context context, int resource, ArrayList<Data> list) {
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;
        if(listView == null){
            listView = LayoutInflater.from(getContext()).inflate(R.layout.mylayout,parent,false);
        }

        Data currentData = getItem(position);

        TextView richterScale = listView.findViewById(R.id.richter);
        richterScale.setText(String.valueOf(currentData.getRichterScale()));

        TextView city = listView.findViewById(R.id.city);
        city.setText(currentData.getCity());

        TextView date = listView.findViewById(R.id.date);
        date.setText(currentData.getDate());

        return listView;
    }
}
