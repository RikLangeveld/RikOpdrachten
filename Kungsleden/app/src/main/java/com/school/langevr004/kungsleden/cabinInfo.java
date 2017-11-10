package com.school.langevr004.kungsleden;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/*
Class to show information of a single item on the screen
 */
public class cabinInfo extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cabin_info);

        Intent i = getIntent();

        byte cabinNumber = i.getExtras().getByte("cabinNumber");

        ImageView imageView = (ImageView) findViewById(R.id.cabinImageView);
        TextView textView = (TextView) findViewById(R.id.textViewCabin);
        TextView textViewDescription = (TextView) findViewById(R.id.textViewDescription);

        textView.setText(GeoObject.PRE_DEFINED_CABINS[cabinNumber].title);
        textViewDescription.setText(GeoObject.PRE_DEFINED_CABINS[cabinNumber].description);
        imageView.setImageResource(GeoObject.PRE_DEFINED_CABINS[cabinNumber].imageCabin);

    }
}
