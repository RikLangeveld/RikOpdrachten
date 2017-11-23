package com.school.langevr004.kungsleden;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;


public class MapFragment extends Fragment {

    private MapView mMap;
    private View view;

    private final int START_ZOOM_LEVEL = 8; //Level of zoom on the map on startup
    private final double AKTSE_LAT = 67.14855, AKTSE_LNG = 18.30592; //Start Coördinates map

    public MapFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //initialize streetmaps
        view = inflater.inflate(R.layout.fragment_map, container, false);
        //initialize streetmaps
        mMap = (MapView) view.findViewById(R.id.mapView);
        mMap.setTileSource(TileSourceFactory.MAPNIK);
        mMap.setMultiTouchControls(true);
        setMap();
        return view;
    }

    public void onResume()
    {
        super.onResume();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        Configuration.getInstance().load(getActivity(), PreferenceManager.getDefaultSharedPreferences(getActivity()));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setMap()
    {
        //Set the startpoint of the map
        IMapController mapController = mMap.getController();
        mapController.setZoom(START_ZOOM_LEVEL);
        GeoPoint startPoint = new GeoPoint(AKTSE_LAT, AKTSE_LNG);
        mapController.setCenter(startPoint);

        //Place all the markers on the map
        for (int i = 0; i < GeoObject.PRE_DEFINED_CABINS.length; i++)
        {
            placeMarker(GeoObject.PRE_DEFINED_CABINS[i], R.drawable.cabin_map_marker, GeoObject.PRE_DEFINED_CABINS[i].title, "", "", i);
        }
    }

    //All the information stored in a single marker
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void placeMarker(Cabin cabin, int icon, String title, String snippet, String subDescription, final int cabinNumber)
    {
        GeoPoint startPoint = cabin.geoPoint;
        final Marker marker = new Marker(mMap);
        marker.setPosition(startPoint);
        marker.setIcon(getResources().getDrawable(icon, getActivity().getApplicationContext().getTheme()));
        marker.setTitle(title);
        marker.setSnippet(snippet);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.setSubDescription(subDescription);

        marker.setOnMarkerClickListener(new Marker.OnMarkerClickListener()
        {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView)
            {
                //Create an Intent and specify which activity needs to be started.
                Intent intent = new Intent(mapView.getContext(), cabinInfo.class);
                intent.putExtra("cabinNumber", (byte) cabinNumber);
                startActivityForResult(intent, 1234);
                return true;
            }
        });
        mMap.getOverlays().add(marker);
    }
}
