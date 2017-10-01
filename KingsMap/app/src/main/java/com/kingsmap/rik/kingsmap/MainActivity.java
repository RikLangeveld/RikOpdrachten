package com.kingsmap.rik.kingsmap;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import android.app.Dialog;
import android.content.pm.PackageManager;
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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.kingsmap.rik.kingsmap.R.layout.activity_map;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap mMap;
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private static final double ABISKO_LAT = 68.349751, ABISO_LNG = 18.831246, KINGSTRAIL_LAT = 67.621841, KINGSTRAIL_LNG = 18.107538;

    /*
    0 = Abisko
    1 = Kingstrail
     */
    private final double SHELTERS_LAT[] = new double[]
            {
                    ABISKO_LAT,
                    KINGSTRAIL_LAT
            };

    private final double SHELTERS_LNG[] = new double[]
            {
                    ABISO_LNG,
                    KINGSTRAIL_LNG
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
        mMap.setMapType(googleMap.MAP_TYPE_TERRAIN);

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

    /*
    Add a marker on the map
     */
    private void AddMarker(double lat, double lng)
    {
        MarkerOptions options = new MarkerOptions()
                .title("Hut")
                .position(new LatLng(lat,lng))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

        options.snippet("Informatie over de hut");
        markers.add( mMap.addMarker(options));
    }
}
