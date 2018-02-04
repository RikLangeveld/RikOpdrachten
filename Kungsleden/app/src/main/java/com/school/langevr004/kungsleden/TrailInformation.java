package com.school.langevr004.kungsleden;

import org.osmdroid.util.GeoPoint;

/*
 Class that holds all the information of the cabins that are displayed on the map. Being made up from cabin object (static) so it can
 be accesed by whole project.
 */

public class TrailInformation
{
    public enum GeoObjectType{
        CABIN,
        FOOD,
        BOAT
    }

    public static final int [] BEST_IMAGES = {
                    R.drawable.abisko, R.drawable.abiskojaure, R.drawable.ammarnas, R.drawable.placeholder_cabin, R.drawable.viterskalet, R.drawable.syter, R.drawable.sitojaure,
            };

    ///Coördinates van de hutten
    private static final double
            ABISKO_LAT = 68.349751, ABISO_LNG = 18.831246,
            ABISKOJAURE_LAT = 68.286149, ABISKOJAURE_LNG =18.591220,
            Alesjaurestugorna_LAT = 68.138411, Alesjaurestugorna_LNG = 18.414098;


    //namen van de hutten
    private static final String[] PRE_DEFINED_GEO_OBJECT_NAMES = {
            "Cabin Abisko",
            "Cabin ABISKOJAURE",
            "Cabin Alesjaurestugorna",
    };

    //descriptions van de hutten
    private static final String[] PRE_DEFINED_GEO_OBJECT_DESCRIPTIONS = {
            "Het meest noordelijke punt van de route",
            "-",
            "-",
    };

    //Afbeeldingen van de hutten.
    private static final int[][] PRE_DEFINED_GEO_OBJECT_IMAGE_IDS = {
            new int[] {R.drawable.abisko, R.drawable.serve, R.drawable.syter},
            new int[] {R.drawable.abiskojaure, R.drawable.abisko, R.drawable.abisko},
            new int[] {R.drawable.placeholder_cabin, R.drawable.abisko, R.drawable.abisko},
    };

    private static final GeoObjectType[][] PRE_DEFINED_GEO_OBJECT_TYPES = {
            new GeoObjectType[] {GeoObjectType.CABIN, GeoObjectType.FOOD, GeoObjectType.BOAT},
            new GeoObjectType[] {GeoObjectType.BOAT},
            new GeoObjectType[] {GeoObjectType.FOOD}
    };

    //Geopoint samengesteld uit de coördinaten
    private static final GeoPoint[] PRE_DEFINED_CABIN_OBJECT_POINT = {
            new GeoPoint(ABISKO_LAT, ABISO_LNG),
            new GeoPoint(ABISKOJAURE_LAT, ABISKOJAURE_LNG),
            new GeoPoint(Alesjaurestugorna_LAT, Alesjaurestugorna_LNG),
    };

    //final objects van de Cabin class
    public static final GeoObject[] PRE_DEFINED_GEO_OBJECTS = {
            new GeoObject(PRE_DEFINED_CABIN_OBJECT_POINT[0],PRE_DEFINED_GEO_OBJECT_NAMES[0], PRE_DEFINED_GEO_OBJECT_DESCRIPTIONS[0], PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[0], PRE_DEFINED_GEO_OBJECT_TYPES[0]),
            new GeoObject(PRE_DEFINED_CABIN_OBJECT_POINT[1],PRE_DEFINED_GEO_OBJECT_NAMES[1], PRE_DEFINED_GEO_OBJECT_DESCRIPTIONS[1], PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[1], PRE_DEFINED_GEO_OBJECT_TYPES[1]),
            new GeoObject(PRE_DEFINED_CABIN_OBJECT_POINT[2],PRE_DEFINED_GEO_OBJECT_NAMES[2], PRE_DEFINED_GEO_OBJECT_DESCRIPTIONS[2], PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[2], PRE_DEFINED_GEO_OBJECT_TYPES[2])
    };

    private String mGeoName;
    private String mCoördinates;
    private int[] mGeoImageName;

    public TrailInformation(String mGeoName, String mCoördinates, int[] mGeoImageName)
    {
        this.mGeoName = mGeoName;
        this.mGeoImageName = mGeoImageName;
        this.mCoördinates = mCoördinates;
    }
    public String getmGeoName()
    {
        return mGeoName;
    }
    public String getmCoördinatesString(){return mCoördinates;}
    public void setmGeoName(String mGeoName) {
        this.mGeoName = mGeoName;
    }
    public int[] getmGeoImageName()
    {
        return mGeoImageName;
    }
    public void setmGeoImageName(int[] mGeoImageName)
    {
        this.mGeoImageName = mGeoImageName;
    }
}
