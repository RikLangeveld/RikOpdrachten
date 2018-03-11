package com.school.langevr004.kungsleden;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.routing.MapQuestRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;

import java.util.ArrayList;


public class MapFragment extends Fragment {

    private MapView mMap;
    private View view;

    private final int START_ZOOM_LEVEL = 9; //Level of zoom on the map on startup

    private GeoObject geoObject;

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
        GeoPoint startPoint = new GeoPoint(TrailInformation.START_LAT, TrailInformation.START_LNG);
        mapController.setCenter(startPoint);

        //Place all the markers on the map
        for (int i = 0; i < TrailInformation.PRE_DEFINED_GEO_OBJECTS.length; i++)
        {
            placeMarker(TrailInformation.PRE_DEFINED_GEO_OBJECTS[i], TrailInformation.PRE_DEFINED_GEO_OBJECTS[i].title, i);
        }
        //new UpdateRoadTask().doInBackground();
        Thread newThread = new Thread(MAKE_POLYLINE);
        newThread.start();
    }


    /*
    Create a line created from al the waypoints.
    Dit moet een apparte Thread gedaan worden omdat het even duurt om het pad te berekenen.

    TODO: Dit is nu niet houdbaar. Er moet een database komen met alle waypoints. Ik moet nog uitzoeken of de snelste route wordt gekozen of dat de volgorde wordt aangehouden.
    */
    private Runnable MAKE_POLYLINE = new Runnable() {
        @Override
        public void run() {
            RoadManager roadManager = new MapQuestRoadManager("PkdWCcofeeuvooqoluxg394KY1dAxvNi");

            roadManager.addRequestOption("routeType=pedestrian");
            ArrayList<GeoPoint> waypoints = new ArrayList<>();


            for (int i = 0; i < TrailInformation.PRE_DEFINED_GEO_OBJECTS.length; i++)
            {
                waypoints.add(TrailInformation.PRE_DEFINED_GEO_OBJECTS[i].geoPoint);
            }

            Road road = roadManager.getRoad(waypoints);
            Polyline roadOverlay1 = roadManager.buildRoadOverlay(road);
            mMap.getOverlays().add(roadOverlay1);
            mMap.postInvalidate();//Refresh de map. Kan gebruikt worden is dezelfde thread.
        }
    };

    //All the information stored in a single marker
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void placeMarker(GeoObject geoObject, String snippet, final int cabinNumber)
    {
        GeoPoint startPoint = geoObject.geoPoint;
        final Marker marker = new Marker(mMap);
        marker.setPosition(startPoint);
        marker.setIcon(getResources().getDrawable(geoObject.getIcon(geoObject.geoObjectTypes[0]), getActivity().getApplicationContext().getTheme()));
        marker.setTitle(geoObject.title);
        marker.setSnippet(snippet);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

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
