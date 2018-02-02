package com.school.langevr004.kungsleden;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;


/*
Class to show information of a single item on the screen
 */
public class cabinInfo extends AppCompatActivity {

    private Toolbar mToolbar;//De toolbar van de activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cabin_info);

        Intent i = getIntent();

        int cabinNumber = i.getExtras().getByte("cabinNumber");

        //ImageView imageView = (ImageView) findViewById(R.id.cabinImageView);
        TextView textView = (TextView) findViewById(R.id.textViewCabin);
        TextView textViewDescription = (TextView) findViewById(R.id.textViewDescription);

        textViewDescription.setText(TrailInformation.PRE_DEFINED_CABINS[cabinNumber].description);
        //imageView.setImageResource(TrailInformation.PRE_DEFINED_CABINS[cabinNumber].imageCabin);

        mToolbar = (Toolbar) findViewById(R.id.nav_action); //De toolbar van de activity
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null)
        {
            //Voeg de back arrow toe
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            //Verander de titel naar de Cabin naam.
            getSupportActionBar().setTitle(TrailInformation.PRE_DEFINED_CABINS[cabinNumber].title);
        }


        createFotoFragment(cabinNumber);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    private void createFotoFragment(int cabinNumber)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment photoFragment = new PhotosFragment(TrailInformation.PRE_DEFINED_CABINS[cabinNumber].imagesCabin);
        fragmentTransaction.replace(R.id.photo_frame, photoFragment);
        fragmentTransaction.commit();
    }
}
