package com.school.langevr004.kungsleden;

import org.osmdroid.util.GeoPoint;

import static com.school.langevr004.kungsleden.TrailInformation.*;

/**
 * Created by Rik on 3-2-2018.
 * Deze class representeert een point of intrest op de kaart.
 */

public class GeoObject
{


    GeoPoint geoPoint;
    String title;
    String description;
    TrailInformation.GeoObjectType geoObjectTypes[];
    int[] geoObjectImages;
    int icon;

    public GeoObject(GeoPoint geoPoint, String title, String description, int[] geoObjectImages, GeoObjectType geoObjectTypes[])
    {
        this.geoPoint = geoPoint;
        this.title = title;
        this.description = description;
        this.icon = R.drawable.cabin_map_marker;
        this.geoObjectTypes = geoObjectTypes;
        this.geoObjectImages = geoObjectImages;
    }

    public int getIcon(GeoObjectType mainType)
    {
        switch (mainType)
        {
            case CABIN:
                return R.drawable.cabin_map_marker;
            case FOOD:
                return R.drawable.ic_food_icon;
            case BOAT:
                return R.drawable.ic_boats;
            case HOTEL:
                return R.drawable.ic_boats;
            case VILLAGE:
                return R.drawable.ic_village;
            default:
                return R.drawable.cabin_map_marker;

        }
    }
}
