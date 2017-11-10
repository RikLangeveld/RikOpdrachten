package com.school.langevr004.kungsleden;

import android.graphics.drawable.Drawable;

import org.osmdroid.util.GeoPoint;

/**
 * Information of a single cabin (created in GeoObject project)
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
