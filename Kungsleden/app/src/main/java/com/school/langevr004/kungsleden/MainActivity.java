package com.school.langevr004.kungsleden;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.school.langevr004.kungsleden.Database.activity.MainActivityTravelNotes;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class MainActivity extends AppCompatActivity {

    private MapView mMap;
    private final int START_ZOOM_LEVEL = 8;

    //Start Coördinates map
    private final double AKTSE_LAT = 67.14855, AKTSE_LNG = 18.30592;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        setContentView(R.layout.activity_main);

        //initialize streetmaps
        mMap = (MapView) findViewById(R.id.map);
        mMap.setTileSource(TileSourceFactory.MAPNIK);
        mMap.setMultiTouchControls(true);
        setMap();

        //initialize toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.actionBar);
        setSupportActionBar(toolbar);
    }

    public void onResume()
    {
        super.onResume();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setMap()
    {
        //Set the startpoint of the map
        IMapController mapController = mMap.getController();
        mapController.setZoom(START_ZOOM_LEVEL);
        GeoPoint startPoint = new GeoPoint(AKTSE_LAT, AKTSE_LNG);
        mapController.setCenter(startPoint);

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
        marker.setIcon(getResources().getDrawable(icon, getApplicationContext().getTheme()));
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

    //option menu creator
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Start new activity on option select
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_cabins:
                Intent intentCabins = new Intent(this, AllCabins.class);
                startActivityForResult(intentCabins, 4321);
                break;
            case R.id.action_travel_notes:
                Intent intentNotes = new Intent(this, MainActivityTravelNotes.class);
                startActivityForResult(intentNotes, 2311);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

