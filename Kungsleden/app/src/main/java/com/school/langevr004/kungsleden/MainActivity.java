package com.school.langevr004.kungsleden;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

/*
Main activity, classe die de logic heeft van de startkaart en alle markers op het scherm zet.
Ook het wisselen naar de andere activities wordt in deze classe geregeld
 */
public class MainActivity extends AppCompatActivity {

    private MapView mMap;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    private Toolbar mToolbar;

    private final int START_ZOOM_LEVEL = 8; //Level of zoom on the map on startup
    private final double AKTSE_LAT = 67.14855, AKTSE_LNG = 18.30592; //Start Coördinates map

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

        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);

        //initialize drawlayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.open,R.string.close );

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Listeners for pages
        NavigationView mNavigationView = (NavigationView) findViewById(R.id.navBar);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem){
                switch (menuItem.getItemId()){
                    case(R.id.nav_overview):
                        MapFragment mapFragment = new MapFragment();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.mainFrame, mapFragment, "mapFragment");
                        fragmentTransaction.commit();
                        break;
                    case (R.id.nav_map):
                        Intent mapActivity = new Intent(getApplicationContext(), AllCabins.class);
                        startActivity(mapActivity);
                        break;
                    case(R.id.nav_waypoints):
                        Intent waypointActivity = new Intent(getApplicationContext(), AllCabins.class);
                        startActivity(waypointActivity);
                        break;
                    default:
                        //do nothing
                        break;

                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (mToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
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
}

