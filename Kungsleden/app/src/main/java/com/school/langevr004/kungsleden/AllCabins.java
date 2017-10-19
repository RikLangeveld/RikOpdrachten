package com.school.langevr004.kungsleden;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AllCabins extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_cabins);

        List<GeoObject> mGeoObjects = new ArrayList<>();
        for (int i = 0; i < GeoObject.PRE_DEFINED_GEO_OBJECT_NAMES.length; i++) {
            mGeoObjects.add(new GeoObject(GeoObject.PRE_DEFINED_GEO_OBJECT_NAMES[i], GeoObject.PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[i]));
        }

        RecyclerView mGeoRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1); //2 cells per row
        mGeoRecyclerView.setLayoutManager(mLayoutManager);
        CabinObjectAdapter mAdapter = new CabinObjectAdapter(this, mGeoObjects);
        mGeoRecyclerView.setAdapter(mAdapter);

    }
}
