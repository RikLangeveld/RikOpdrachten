package com.school.langevr004.kungsleden;

import org.osmdroid.util.GeoPoint;

/*
 Class that holds all the information of the cabins that are displayed on the map. Being made up from cabin object (static) so it can
 be accesed by whole project.
 */

public class TrailInformation {
    public enum GeoObjectType {
        VILLAGE,
        HOTEL,
        CABIN,
        FOOD,
        BOAT
    }

    //Beste Images van de Trail. Wordt onderandere gebruikt voor
    public static final int[] BEST_IMAGES = {
            R.drawable.abisko, R.drawable.abiskojaure, R.drawable.ammarnas, R.drawable.placeholder_cabin, R.drawable.viterskalet, R.drawable.syter, R.drawable.sitojaure,
    };

    ///Coördinates van de startlocatie
    public static final double START_LAT = 57.2976, START_LNG = -4.9576;

    /*
    * Paramaters uitleg:
     *1: Geopoint (positie op de kaart)
     *2: Naam van Gepoint
     *3: De omschijving van het punt
     *4: De foto's bij het punt
     *5: De categoriën dat bij het punt horen.
    * */
    public static final GeoObject[] PRE_DEFINED_GEO_OBJECTS = {
            new GeoObject(
                    new GeoPoint(57.33538, -4.47991),
                    "Drumnadrochit",
                    "Drumnadrochit (Scottish Gaelic, Druim na Drochaid) is a village in the Highland local government council area of Scotland, " +
                    "lying on the west shore of Loch Ness, at the foot of Glen Urquhart.",
                    new int[]{R.drawable.abisko, R.drawable.serve, R.drawable.syter},
                    new GeoObjectType[]{GeoObjectType.VILLAGE}),

            new GeoObject(
                    new GeoPoint(57.34682, -4.76431),
                    "Cannich",
                    "Cannich (Gaelic: Canaich) is a village at the southern end of Strathglass, in the Highlands of Scotland, " +
                    "about 26 miles (42 km) west of the city of Inverness. It is at the furthest point of the A831 that loops around the Aird from Beauly to Drumnadrochit.",
                    new int[]{R.drawable.abisko, R.drawable.serve},
                    new GeoObjectType[]{GeoObjectType.VILLAGE}),

            new GeoObject(
                    new GeoPoint(57.23490, -5.38033),
                    "Morvich",
                    "Morvich (Gaelic:A’ Mhormhaich[1]) is a very small settlement in Glen Shiel near the southern end of Loch Duich, " +
                    "and to the north of Kintail, in Lochalsh, in the Highland council area of Scotland. " +
                    "The name \"Morvich\" may be from the Gaelic for \"sea plain\" or \"the carse\".[1]",
                    new int[]{R.drawable.abisko, R.drawable.serve},
                    new GeoObjectType[]{GeoObjectType.VILLAGE})
    };
}
