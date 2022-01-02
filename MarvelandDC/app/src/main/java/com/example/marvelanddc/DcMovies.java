package com.example.marvelanddc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class DcMovies extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dc_movies);

        ArrayList<Poster> dcList = new ArrayList<>();
        dcList.add(new Poster("Man Of Steel",R.drawable.manofsteel,"https://www.youtube.com/watch?v=T6DJcgm3wNY"));
        dcList.add(new Poster("Batman v/s Superman : Dawn of Justice",R.drawable.batmanvssuperman,"https://www.youtube.com/watch?v=0WWzgGyAH6Y"));
        dcList.add(new Poster("Suicide Squad",R.drawable.suicidesquad,"https://youtu.be/zy6sy8bPOJA"));
        dcList.add(new Poster("Justice League",R.drawable.jl,"https://www.youtube.com/watch?v=3cxixDgHUYw"));
        dcList.add(new Poster("Wonder Woman",R.drawable.ww,"https://www.youtube.com/watch?v=1Q8fG0TtVAY"));
        dcList.add(new Poster("Wonder Women 1984",R.drawable.ww84,"https://www.youtube.com/user/WarnerBrosPictures"));
        dcList.add(new Poster("Aquaman",R.drawable.aquaman,"https://www.youtube.com/watch?v=WDkg3h8PCVU&list=PLku4OZMkmaZ6flKmXnd58fT6ePvfCR-kg&index=30"));
        dcList.add(new Poster("Birds of Prey",R.drawable.bop,"https://www.youtube.com/watch?v=kGM4uYZzfu0&list=PLku4OZMkmaZ6flKmXnd58fT6ePvfCR-kg&index=41"));
        dcList.add(new Poster("Shazam",R.drawable.shazam,"https://www.youtube.com/watch?v=go6GEIrcvFY&list=PLku4OZMkmaZ6flKmXnd58fT6ePvfCR-kg&index=34"));
        dcList.add(new Poster("Zack Synder's Justice League",R.drawable.zsjl,"https://www.youtube.com/watch?v=vM-Bja2Gy04"));
        dcList.add(new Poster("The Suicide Squad",R.drawable.thesuicidesquad,"https://youtu.be/eg5ciqQzmK0"));

        ListView listView = (ListView) findViewById(R.id.dc_layout);

        MovieAdapter dcAdapter = new MovieAdapter(DcMovies.this,dcList);

        listView.setAdapter(dcAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(dcAdapter.getItem(i).getLink())); // only email apps should handle this
                startActivity(intent);
            }
        });
    }
}