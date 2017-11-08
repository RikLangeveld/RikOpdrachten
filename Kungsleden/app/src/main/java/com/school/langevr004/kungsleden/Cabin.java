package com.school.langevr004.kungsleden;

import android.graphics.drawable.Drawable;

import org.osmdroid.util.GeoPoint;

/**
 * Created by langevr004 on 16-10-2017.
 */

public class Cabin
{
    GeoPoint geoPoint;
    String title;
    String description;
    int imageCabin;
    int icon;


    Cabin(GeoPoint geoPoint, String title, String description, int imageCabin)
    {
        this.geoPoint = geoPoint;
        this.title = title;
        this.description = description;
        this.icon = R.drawable.cabin_map_marker;
        this.imageCabin = imageCabin;
    }
}
