package com.kingsmap.rik.kingsmap;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.auth.GooglePlayServicesAvailabilityException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.internal.IPolylineDelegate;
import com.google.android.gms.vision.barcode.Barcode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.kingsmap.rik.kingsmap.R.layout.activity_map;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static boolean useTerainMode;

    public static GoogleMap mMap;
    private static final int ERROR_DIALOG_REQUEST = 9001;
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




    //Teusajaure 67.69531, 18.15554
    //Vakkotavare 67.58189, 18.10153
    //Saltoluokta 67.39418, 18.52007
    //Sitojaure 67.23205, 18.44093
    //Aktse 67.14855, 18.30592
    //Pårte 67.04329, 17.94308
    //Kvikkjokk 66.95362, 17.71955
    //Ammarnäs 65.95761, 16.20378
    //Aigert 65.94133, 16.09472
    //Serve 65.97226, 15.76426
    //Tärnasjö 65.98882, 15.49727
    //Syter 65.89275, 15.39667
    //Viterskalet 65.88502, 15.15836
    //Hemavan 65.81733, 15.09059

    private final double SHELTERS_LAT[] = new double[]
            {
                    ABISKO_LAT,
                    //KINGSTRAIL_LAT,
                    ABISKOJAURE_LAT,
                    Alesjaurestugorna_LAT,
                    Tjäktjapasset_LAT,
                    Sälka_LAT,
                    Kaitumjaure_LAT,
                    Teusajaure_LAT,
                    Vakkotavare_LAT,
                    Saltoluokta_LAT,
                    Sitojaure_LAT,
                    Aktse_LAT,
                    Pårte_LAT,
                    Kvikkjokk_LAT,
                    Ammarnäs_LAT,
                    Aigert_LAT,
                    Serve_LAT,
                    Tärnasjö_LAT,
                    Syter_LAT,
                    Viterskalet_LAT,
                    Hemavan_LAT
            };

    private final double SHELTERS_LNG[] = new double[]
            {
                    ABISO_LNG,
                    //KINGSTRAIL_LNG,
                    ABISKOJAURE_LNG,
                    Alesjaurestugorna_LNG,
                    Tjäktjapasset_LNG,
                    Sälka_LNG,
                    Kaitumjaure_LNG,
                    Teusajaure_LNG,
                    Vakkotavare_LNG,
                    Saltoluokta_LNG,
                    Sitojaure_LNG,
                    Aktse_LNG,
                    Pårte_LNG,
                    Kvikkjokk_LNG,
                    Ammarnäs_LNG,
                    Aigert_LNG,
                    Serve_LNG,
                    Tärnasjö_LNG,
                    Syter_LNG,
                    Viterskalet_LNG,
                    Hemavan_LNG
            };

    private List<Marker> markers = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (servicesOK())
        {
            setContentView(activity_map);

            if (mMap == null)
            {
                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                mapFragment.getMapAsync(this);
            }
        }
        else
        {
            setContentView(R.layout.activity_main);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.item_share:
                Intent intent = new Intent(this, Settings.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Share", Toast.LENGTH_SHORT).show();
                return (true);
            case R.id.item_delete:
                Toast.makeText(MainActivity.this, "Delete", Toast.LENGTH_SHORT).show();
                return (true);
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    public boolean servicesOK() {

        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int isAvailable = googleAPI.isGooglePlayServicesAvailable(this);

        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (googleAPI.isUserResolvableError(isAvailable)) {
            Dialog dialog = googleAPI.getErrorDialog(this, isAvailable, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "Can't connect to mapping service", Toast.LENGTH_SHORT).show();

        }
        return false;
    }

    private void goToLocation(double lat, double lng, float zoom) {
        LatLng latLng = new LatLng(lat, lng);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, zoom);
        mMap.moveCamera(cameraUpdate);
    }


    /*
    being called by getMapAsync method.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Set the style of the map
        MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style);
        googleMap.setMapStyle(style);

        if (useTerainMode)
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        }
        else
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            mMap.setMyLocationEnabled(true);
            Toast.makeText(this, "Ready to map", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Permission Error", Toast.LENGTH_SHORT).show();
        }

        goToLocation(KINGSTRAIL_LAT, KINGSTRAIL_LNG, 7);

        for (int i = 0; i < SHELTERS_LAT.length; i++)
        {
            AddMarker(SHELTERS_LAT[i], SHELTERS_LNG[i]);
        }
    }

    /* Add a marker on the map */
    private void AddMarker(double lat, double lng)
    {
        MarkerOptions options = new MarkerOptions()
                .title("Hut")
                .position(new LatLng(lat,lng))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.cabin_map_marker));

        options.snippet("Informatie over de hut");
        markers.add( mMap.addMarker(options));
    }
}
