package com.school.langevr004.kungsleden;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.MapView;


public class MapFragment extends Fragment {

    private MapView mMap;

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //initialize streetmaps
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        //mMap = (MapView) v.findViewById(R.id.map);
        //mMap.setTileSource(TileSourceFactory.MAPNIK);
        //mMap.setMultiTouchControls(true);
        return inflater.inflate(R.layout.fragment_map, container, false);
    }
}
