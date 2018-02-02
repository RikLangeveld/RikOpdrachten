package com.school.langevr004.kungsleden;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
Deze class (Activity) maakt met behulp van de CabinObjectAdapter een RecyclerView om alle cabins in de activity te krijgen.
 */
public class AllCabins extends AppCompatActivity {

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_cabins);

        //Vind de toolbar en zet de tekst, (Zetten van de tekst van de toolbar gebeurd in manifest)
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        List<TrailInformation> mGeoObjects = new ArrayList<>();
        for (int i = 0; i < TrailInformation.PRE_DEFINED_CABINS.length; i++) {
            mGeoObjects.add(
                    new TrailInformation(TrailInformation.PRE_DEFINED_CABINS[i].title
                    ,"Coördinates \n"  + TrailInformation.PRE_DEFINED_CABINS[i].geoPoint.getLatitude() + "\n" + TrailInformation.PRE_DEFINED_CABINS[i].geoPoint.getLatitude()
                    ,TrailInformation.PRE_DEFINED_CABINS[i].imagesCabin));
        }
        RecyclerView mGeoRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1); //2 cells per row
        mGeoRecyclerView.setLayoutManager(mLayoutManager);
        CabinObjectAdapter mAdapter = new CabinObjectAdapter(this, mGeoObjects);
        mGeoRecyclerView.setAdapter(mAdapter);
    }
    */
}
