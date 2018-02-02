package com.school.langevr004.kungsleden;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        int cabinNumber = i.getExtras().getByte("cabinNumber");

        //ImageView imageView = (ImageView) findViewById(R.id.cabinImageView);
        TextView textView = (TextView) findViewById(R.id.textViewCabin);
        TextView textViewDescription = (TextView) findViewById(R.id.textViewDescription);

        textView.setText(GeoObject.PRE_DEFINED_CABINS[cabinNumber].title);
        textViewDescription.setText(GeoObject.PRE_DEFINED_CABINS[cabinNumber].description);
        //imageView.setImageResource(GeoObject.PRE_DEFINED_CABINS[cabinNumber].imageCabin);

        createFotoFragment(cabinNumber);
    }

    private void createFotoFragment(int cabinNumber)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment photoFragment = new PhotosFragment(GeoObject.PRE_DEFINED_CABINS[cabinNumber].imagesCabin);
        fragmentTransaction.replace(R.id.photo_frame, photoFragment);
        fragmentTransaction.commit();
    }
}
