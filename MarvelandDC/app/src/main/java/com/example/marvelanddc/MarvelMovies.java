package com.example.marvelanddc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MarvelMovies extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marvel_movies);

        ArrayList<Poster> marvelList = new ArrayList<Poster>();
        marvelList.add(new Poster("The Incredible Hulk",R.drawable.hulk,"https://www.youtube.com/watch?v=xbqNb2PFKKA"));
        marvelList.add(new Poster("Iron Man", R.drawable.ironman1,"https://youtu.be/8hYlB38asDY"));
        marvelList.add(new Poster("Iron Man 2",R.drawable.ironman2,"https://youtu.be/wKtcmiifycU"));
        marvelList.add(new Poster("Captain America The First Avenger",R.drawable.captain1,"https://www.youtube.com/watch?v=JerVrbLldXw"));
        marvelList.add(new Poster("Thor",R.drawable.thor,"https://www.youtube.com/watch?v=JOddp-nlNvQ&list=PLqPdFLnEGslw3fVWzQFOQcHr-Rd8ovbc7&index=4"));
        marvelList.add(new Poster("The Avengers",R.drawable.avengers1,"https://www.youtube.com/watch?v=eOrNdBpGMv8&list=PLqPdFLnEGslw3fVWzQFOQcHr-Rd8ovbc7&index=9"));
        marvelList.add(new Poster("Iron Man 3",R.drawable.ironman3,"https://www.youtube.com/watch?v=YLorLVa95Xo"));

        marvelList.add(new Poster("Captain America The Winter Soldier",R.drawable.captain2,"https://www.youtube.com/watch?v=NLWsK1ZFunA&list=PLqPdFLnEGslw3fVWzQFOQcHr-Rd8ovbc7&index=17"));
        marvelList.add(new Poster("Thor The Dark World",R.drawable.thor2,"https://www.youtube.com/watch?v=npvJ9FTgZbM&list=PLqPdFLnEGslw3fVWzQFOQcHr-Rd8ovbc7&index=16"));
        marvelList.add(new Poster("Guardians Of The Galaxy Vol 1",R.drawable.guardian1,"https://www.youtube.com/watch?v=d96cjJhvlMA&list=PLqPdFLnEGslw3fVWzQFOQcHr-Rd8ovbc7&index=19"));
        marvelList.add(new Poster("Ant-man",R.drawable.antman1,"https://www.youtube.com/watch?v=pWdKf3MneyI&list=PLqPdFLnEGslw3fVWzQFOQcHr-Rd8ovbc7&index=28"));
        marvelList.add(new Poster("Avengers Age Of Ultron",R.drawable.avengers2,"https://www.youtube.com/watch?v=MZoO8QVMxkk&list=PLqPdFLnEGslw3fVWzQFOQcHr-Rd8ovbc7&index=22"));
        marvelList.add(new Poster("Captain America Civil War",R.drawable.captain3,"https://www.youtube.com/watch?v=43NWzay3W4s&list=PLqPdFLnEGslw3fVWzQFOQcHr-Rd8ovbc7&index=30"));
        marvelList.add(new Poster("Guardians Of Galaxy Vol 2",R.drawable.guardian2,"https://www.youtube.com/watch?v=2WhQcK-Zaok&list=PLqPdFLnEGslw3fVWzQFOQcHr-Rd8ovbc7&index=35"));
        marvelList.add(new Poster("Thor Ragnarok",R.drawable.thor3,"https://www.youtube.com/watch?v=ue80QwXMRHg&list=PLqPdFLnEGslw3fVWzQFOQcHr-Rd8ovbc7&index=43"));
        marvelList.add(new Poster("Spider-man Homecoming",R.drawable.spiderman1,"https://www.youtube.com/watch?v=xrzXIaTt99U&list=PLqPdFLnEGslw3fVWzQFOQcHr-Rd8ovbc7&index=39"));
        marvelList.add(new Poster("Ant-man And The Wasp",R.drawable.antman2,"https://www.youtube.com/watch?v=8_rTIAOohas&list=PLqPdFLnEGslw3fVWzQFOQcHr-Rd8ovbc7&index=49"));
        marvelList.add(new Poster("Avengers Infinity War",R.drawable.avengers3,"https://www.youtube.com/watch?v=6ZfuNTqbHE8&list=PLqPdFLnEGslw3fVWzQFOQcHr-Rd8ovbc7&index=46"));
        marvelList.add(new Poster("Captain Marvel",R.drawable.marvel,"https://www.youtube.com/watch?v=Z1BCujX3pw8&list=PLqPdFLnEGslw3fVWzQFOQcHr-Rd8ovbc7&index=51"));

        marvelList.add(new Poster("Avengers Endgame",R.drawable.avengers4,"https://www.youtube.com/watch?v=hA6hldpSTF8&list=PLqPdFLnEGslw3fVWzQFOQcHr-Rd8ovbc7&index=54"));
        marvelList.add(new Poster("Spider-Man Far From Home",R.drawable.spiderman2,"https://www.youtube.com/watch?v=LFoz8ZJWmPs&list=PLqPdFLnEGslw3fVWzQFOQcHr-Rd8ovbc7&index=59"));
        marvelList.add(new Poster("Black Widow",R.drawable.widow,"https://www.youtube.com/watch?v=RxAtuMu_ph4&list=PLqPdFLnEGslw3fVWzQFOQcHr-Rd8ovbc7&index=60"));
        marvelList.add(new Poster("Shang Chi",R.drawable.shangchi,"https://www.youtube.com/watch?v=giWIr7U1deA&list=PLqPdFLnEGslw3fVWzQFOQcHr-Rd8ovbc7&index=63"));



        MovieAdapter marvelAdapter = new MovieAdapter(MarvelMovies.this, marvelList);

        ListView listView = (ListView) findViewById(R.id.marvel_list);

        listView.setAdapter(marvelAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(marvelList.get(i).getLink())); // only email apps should handle this
                startActivity(intent);
            }
        });

    }
}