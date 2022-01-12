package com.example.travelmate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class History extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ImageView imageView = (ImageView) findViewById(R.id.history_image);
        imageView.setImageResource(R.drawable.historyjabalpur);

        String text = "Mythology describes three Asuras (evil spirits) in the Jabalpur region, who were defeated by the Hindu god Shiva. Tripurasura being the main asura, gave the city its puranic name Tripur Tirth.[11] Tripuri region corresponds to the ancient Chedi Kingdom of Mahabharata times, to which king Shishupala belongs.\n" +
                "\n" +
                "Ashokan relics dating to 300 BCE have been found in Rupnath, 84 kilometres (52 mi) north of the city, indicating the presence of the Mauryan Empire (322 to 185 BCE) in the region.[11] When the empire fell, Jabalpur became a city-state before coming under the rule of the Satavahana dynasty (230 BCE to 220 CE). After their reign, the region was ruled locally by the Bodhis and the Senas, following which it became a vassal state of the Gupta Empire (320 to 550).[11]\n" +
                "\n" +
                "From 675 to 800, the region was ruled by Bamraj Dev of the Kalachuri Dynasty from Karanbel. The best known Kalachuri ruler was Yuvraj Dev I (r. 915–945), who married Nohla Devi (a princess of the Chalukya dynasty).\n" +
                "\n" +
                "One of the Kalachuri ministers, Golok Simha Kayastha, was instrumental in founding the Chausath Yogini Temple near Bhedaghat. His descendants include Bhoj Simha, who was the Dewan to Sangramsahi (r. 1491–1543); Dewan Adhar Simha, who was the prime minister to Rani Durgavati (r. 1550–1564), and Beohar Raghuvir Sinha, the last Jagirdar of Jabalpur who reigned until 1947.";

        TextView textView = (TextView) findViewById(R.id.history_textview);
        textView.setText(text);
    }
}