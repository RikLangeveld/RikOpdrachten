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

public class AllCabins extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_cabins);

        //Vind de toolbar en zet de tekst, (Zetten van de tekst van de toolbar gebeurd in manifest)
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        List<GeoObject> mGeoObjects = new ArrayList<>();
        for (int i = 0; i < GeoObject.PRE_DEFINED_CABINS.length; i++) {
            mGeoObjects.add(new GeoObject(GeoObject.PRE_DEFINED_CABINS[i].title, GeoObject.PRE_DEFINED_CABINS[i].imageCabin));
        }
        RecyclerView mGeoRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1); //2 cells per row
        mGeoRecyclerView.setLayoutManager(mLayoutManager);
        CabinObjectAdapter mAdapter = new CabinObjectAdapter(this, mGeoObjects);
        mGeoRecyclerView.setAdapter(mAdapter);
    }
}
