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
import android.widget.Toast;

import com.school.langevr004.kungsleden.DatabaseTest.activity.MainActivityTravelNotes;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MapView mMap;

    ArrayList<OverlayItem> overlayItemArray;

    private GeoPoint start = new GeoPoint(27.6803, 85.3303);
    private GeoPoint destinationPoint = new GeoPoint(27.6303, 85.3203);

    private static final double
            ABISKO_LAT = 68.349751, ABISO_LNG = 18.831246,
            KINGSTRAIL_LAT = 67.621841, KINGSTRAIL_LNG = 18.107538,
            ABISKOJAURE_LAT = 68.286149, ABISKOJAURE_LNG =18.591220,
            Alesjaurestugorna_LAT = 68.138411, Alesjaurestugorna_LNG = 18.414098,
            Tjäktjapasset_LAT = 68.018885, Tjäktjapasset_LNG = 18.246111,
            Sälka_LAT = 67.933416, Sälka_LNG = 18.149969,
            //Signi hut mist nog.
            Kaitumjaure_LAT = 67.746860, Kaitumjaure_LNG = 18.295732,
            Teusajaure_LAT = 67.69531, Teusajaure_LNG = 18.15554,
            Vakkotavare_LAT =67.58189, Vakkotavare_LNG = 18.10153,
            Saltoluokta_LAT = 67.39418, Saltoluokta_LNG = 18.52007,
            Sitojaure_LAT = 67.23205, Sitojaure_LNG = 18.44093,
            Aktse_LAT = 67.14855, Aktse_LNG = 18.30592,
            Pårte_LAT = 67.04329, Pårte_LNG = 17.94308,
            Kvikkjokk_LAT = 66.95362, Kvikkjokk_LNG = 17.71955,
            Ammarnäs_LAT = 65.95735, Ammarnäs_LNG = 16.20635,
            Aigert_LAT = 65.94134, Aigert_LNG = 16.09472,
            Serve_LAT = 65.97226, Serve_LNG = 15.76426,
            Tärnasjö_LAT = 65.98882, Tärnasjö_LNG = 15.49727,
            Syter_LAT = 65.89275, Syter_LNG = 15.39667,
            Viterskalet_LAT = 65.88502, Viterskalet_LNG = 15.15836,
            Hemavan_LAT = 65.81733, Hemavan_LNG = 15.09059;

    /*
    public static Cabin cabins[] = new Cabin []
            {
                    new Cabin(new GeoPoint(ABISKO_LAT, ABISO_LNG), "Cabin Abisko", R.drawable.alaska_denali),
                    new Cabin(new GeoPoint(ABISKOJAURE_LAT, ABISKOJAURE_LNG), "Cabin ABISKOJAURE", R.drawable.alaska_denali),
                    new Cabin(new GeoPoint(Alesjaurestugorna_LAT, Alesjaurestugorna_LNG), "Cabin Alesjaurestugorna", R.drawable.alaska_denali),
                    new Cabin(new GeoPoint(Tjäktjapasset_LAT, Tjäktjapasset_LNG), "Cabin Tjäktjapasset", R.drawable.hawaii),
                    new Cabin(new GeoPoint(Sälka_LAT, Sälka_LNG), "Cabin Sälka", R.drawable.hawaii),
                    new Cabin(new GeoPoint(Kaitumjaure_LAT, Kaitumjaure_LNG), "Cabin Kaitumjaure", R.drawable.hawaii),
                    new Cabin(new GeoPoint(Teusajaure_LAT, Teusajaure_LNG), "Cabin Teusajaure", R.drawable.hawaii),
                    new Cabin(new GeoPoint(Vakkotavare_LAT, Vakkotavare_LNG), "Cabin Vakkotavare", R.drawable.hawaii),
                    new Cabin(new GeoPoint(Saltoluokta_LAT, Saltoluokta_LNG), "Cabin Saltoluokta", R.drawable.hawaii),
                    new Cabin(new GeoPoint(Sitojaure_LAT, Sitojaure_LNG), "Cabin Sitojaure", R.drawable.hawaii),
                    new Cabin(new GeoPoint(Aktse_LAT, Aktse_LNG), "Cabin Aktse", R.drawable.hawaii),
                    new Cabin(new GeoPoint(Pårte_LAT, Pårte_LNG), "Cabin Pårte", R.drawable.hawaii),
                    new Cabin(new GeoPoint(Kvikkjokk_LAT, Kvikkjokk_LNG), "Cabin Kvikkjokk", R.drawable.pompeii),
                    new Cabin(new GeoPoint(Ammarnäs_LAT, Ammarnäs_LNG), "Cabin Ammarnäs", R.drawable.pompeii),
                    new Cabin(new GeoPoint(Aigert_LAT, Aigert_LNG), "Cabin Aigert", R.drawable.pompeii),
                    new Cabin(new GeoPoint(Serve_LAT, Serve_LNG), "Cabin Serve", R.drawable.pompeii),
                    new Cabin(new GeoPoint(Tärnasjö_LAT, Tärnasjö_LNG), "Cabin Tärnasjö", R.drawable.pompeii),
                    new Cabin(new GeoPoint(Syter_LAT, Syter_LNG), "Cabin Syter", R.drawable.pompeii),
                    new Cabin(new GeoPoint(Viterskalet_LAT, Viterskalet_LNG), "Cabin Viterskalet", R.drawable.pompeii),
                    new Cabin(new GeoPoint(Hemavan_LAT, Hemavan_LNG), "Cabin Hemavan", R.drawable.pompeii),
            };
     */



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        setContentView(R.layout.activity_main);

        mMap = (MapView) findViewById(R.id.map);
        mMap.setTileSource(TileSourceFactory.MAPNIK);
        mMap.setMultiTouchControls(true);

        Toolbar toolbar = (Toolbar) findViewById(R.id.actionBar);
        setSupportActionBar(toolbar);

        setMap();
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
        mapController.setZoom(8);
        GeoPoint startPoint = new GeoPoint(Aktse_LAT, Aktse_LNG);
        mapController.setCenter(startPoint);

        for (int i = 0; i < GeoObject.PRE_DEFINED_CABINS.length; i++)
        {
            placeMarker(GeoObject.PRE_DEFINED_CABINS[i].geoPoint, R.drawable.cabin_map_marker, GeoObject.PRE_DEFINED_CABINS[i].title, "empty", "empty");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void placeMarker(GeoPoint geoPoint, int icon, String title, String snippet, String subDescription)
    {
        GeoPoint startPoint = geoPoint;
        final Marker startMarker = new Marker(mMap);
        startMarker.setPosition(startPoint);
        startMarker.setIcon(getResources().getDrawable(icon, getApplicationContext().getTheme()));
        startMarker.setTitle(title);
        startMarker.setSnippet(snippet);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        startMarker.setSubDescription(subDescription);

        startMarker.setOnMarkerClickListener(new Marker.OnMarkerClickListener()
        {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView)
            {
                Toast.makeText(getBaseContext() , "Test", Toast.LENGTH_SHORT).show();

                //Create an Intent and specify which activity needs to be started.
                Intent intent = new Intent(mapView.getContext(), cabinInfo.class);
                intent.putExtra("title", startMarker.getTitle());
                startActivityForResult(intent, 1234);

                return true;
            }
        });

        mMap.getOverlays().add(startMarker);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onBackPressed()
    {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

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

