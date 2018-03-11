package com.school.langevr004.kungsleden;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/*
Class to show information of a single item on the screen
 */
public class cabinInfo extends AppCompatActivity {

    private Toolbar mToolbar;//De toolbar van de activity
    private GeoObject geoObject;
    private LinearLayout geoObjectTypesImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cabin_info);

        Intent i = getIntent();

        int cabinNumber = i.getExtras().getByte("cabinNumber");

        geoObject = TrailInformation.PRE_DEFINED_GEO_OBJECTS[cabinNumber];
        geoObjectTypesImages = (LinearLayout) findViewById(R.id.geoTypeLinearLayout);

        //ImageView imageView = (ImageView) findViewById(R.id.cabinImageView);
        TextView textViewDescription = (TextView) findViewById(R.id.textViewDescription);

        textViewDescription.setText(geoObject.description);
        //imageView.setImageResource(TrailInformation.PRE_DEFINED_CABINS[cabinNumber].imageCabin);

        mToolbar = (Toolbar) findViewById(R.id.nav_action); //De toolbar van de activity
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null)
        {
            //Voeg de back arrow toe
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            //Verander de titel naar de Cabin naam.
            getSupportActionBar().setTitle(geoObject.title);
        }


        createFotoFragment();
        createGeoObjectTypeImagesLayout();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    private void createGeoObjectTypeImagesLayout()
    {
        ImageView[] imageView = new ImageView[geoObject.geoObjectTypes.length];

        int i = 0; //foreach loop counter;

        for (TrailInformation.GeoObjectType item : geoObject.geoObjectTypes)
        {
            imageView[i] = new ImageView(this);
            imageView[i].setImageDrawable(ContextCompat.getDrawable(this.getApplicationContext(), geoObject.getIcon(geoObject.geoObjectTypes[i])));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);
            geoObjectTypesImages.addView(imageView[i], params);

            i++;
        }
    }

    private void createFotoFragment()
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment photoFragment = new PhotosFragment(geoObject.geoObjectImages);
        fragmentTransaction.replace(R.id.photo_frame, photoFragment);
        fragmentTransaction.commit();
    }
}
