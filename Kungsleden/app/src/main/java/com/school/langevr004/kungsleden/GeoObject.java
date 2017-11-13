package com.school.langevr004.kungsleden;

import org.osmdroid.util.GeoPoint;

/*
 Class that holds all the information of the cabins that are displayed on the map. Being made up from cabin object (static) so it can
 be accesed by whole project.
 */

public class GeoObject {

    ///Coördinates van de hutten
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

    //namen van de hutten
    private static final String[] PRE_DEFINED_GEO_OBJECT_NAMES = {
            "Cabin Abisko",
            "Cabin ABISKOJAURE",
            "Cabin Alesjaurestugorna",
            "Cabin Tjäktjapasset",
            "Cabin Sälka",
            "Cabin Kaitumjaure",
            "Cabin Teusajaure",
            "Cabin Vakkotavare",
            "Cabin Saltoluokta",
            "Cabin Sitojaure",
            "Cabin Aktse",
            "Cabin Pårte",
            "Cabin Kvikkjokk",
            "Cabin Ammarnäs",
            "Cabin Aigert",
            "Cabin Serve",
            "Cabin Tärnasjö",
            "Cabin Syter",
            "Cabin Viterskalet",
            "Cabin Hemavan"
    };

    //descriptions van de hutten
    private static final String[] PRE_DEFINED_GEO_OBJECT_DESCRIPTIONS = {
            "Het meest noordelijke punt van de route",
            "-",
            "-",
            "-",
            "-",
            "-",
            "-",
            "-",
            "-",
            "-",
            "-",
            "-",
            "-",
            "-",
            "-",
            "-",
            "-",
            "-",
            "-",
            "Het meest zuidelijke punt van de route"
    };

    //omschijving van de hutten
    private static final int[] PRE_DEFINED_GEO_OBJECT_IMAGE_IDS = {
            R.drawable.abisko,
            R.drawable.abiskojaure,
            R.drawable.placeholder_cabin,
            R.drawable.tjaktjapasset,
            R.drawable.salka,
            R.drawable.placeholder_cabin,
            R.drawable.placeholder_cabin,
            R.drawable.placeholder_cabin,
            R.drawable.saltoluokta,
            R.drawable.sitojaure,
            R.drawable.aktse,
            R.drawable.parte,
            R.drawable.kvikkjokk,
            R.drawable.ammarnas,
            R.drawable.aigert,
            R.drawable.serve,
            R.drawable.tarnasjo,
            R.drawable.syter,
            R.drawable.viterskalet,
            R.drawable.placeholder_cabin,
    };

    //Geopoint samengesteld uit de coördinaten
    private static final GeoPoint[] PRE_DEFINED_CABIN_OBJECT_POINT = {
            new GeoPoint(ABISKO_LAT, ABISO_LNG),
            new GeoPoint(ABISKOJAURE_LAT, ABISKOJAURE_LNG),
            new GeoPoint(Alesjaurestugorna_LAT, Alesjaurestugorna_LNG),
            new GeoPoint(Tjäktjapasset_LAT, Tjäktjapasset_LNG),
            new GeoPoint(Sälka_LAT, Sälka_LNG),
            new GeoPoint(Kaitumjaure_LAT, Kaitumjaure_LNG),
            new GeoPoint(Teusajaure_LAT, Teusajaure_LNG),
            new GeoPoint(Vakkotavare_LAT, Vakkotavare_LNG),
            new GeoPoint(Saltoluokta_LAT, Saltoluokta_LNG),
            new GeoPoint(Sitojaure_LAT, Sitojaure_LNG),
            new GeoPoint(Aktse_LAT, Aktse_LNG),
            new GeoPoint(Pårte_LAT, Pårte_LNG),
            new GeoPoint(Kvikkjokk_LAT, Kvikkjokk_LNG),
            new GeoPoint(Ammarnäs_LAT, Ammarnäs_LNG),
            new GeoPoint(Aigert_LAT, Aigert_LNG),
            new GeoPoint(Serve_LAT, Serve_LNG),
            new GeoPoint(Tärnasjö_LAT, Tärnasjö_LNG),
            new GeoPoint(Syter_LAT, Syter_LNG),
            new GeoPoint(Viterskalet_LAT, Viterskalet_LNG),
            new GeoPoint(Hemavan_LAT, Hemavan_LNG),
    };

    //final objects van de Cabin class
    public static final Cabin[] PRE_DEFINED_CABINS = {
            new Cabin(PRE_DEFINED_CABIN_OBJECT_POINT[0],PRE_DEFINED_GEO_OBJECT_NAMES[0], PRE_DEFINED_GEO_OBJECT_DESCRIPTIONS[0], PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[0]),
            new Cabin(PRE_DEFINED_CABIN_OBJECT_POINT[1],PRE_DEFINED_GEO_OBJECT_NAMES[1], PRE_DEFINED_GEO_OBJECT_DESCRIPTIONS[1], PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[1]),
            new Cabin(PRE_DEFINED_CABIN_OBJECT_POINT[2],PRE_DEFINED_GEO_OBJECT_NAMES[2], PRE_DEFINED_GEO_OBJECT_DESCRIPTIONS[2], PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[2]),
            new Cabin(PRE_DEFINED_CABIN_OBJECT_POINT[3],PRE_DEFINED_GEO_OBJECT_NAMES[3], PRE_DEFINED_GEO_OBJECT_DESCRIPTIONS[3], PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[3]),
            new Cabin(PRE_DEFINED_CABIN_OBJECT_POINT[4],PRE_DEFINED_GEO_OBJECT_NAMES[4], PRE_DEFINED_GEO_OBJECT_DESCRIPTIONS[4], PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[4]),
            new Cabin(PRE_DEFINED_CABIN_OBJECT_POINT[5],PRE_DEFINED_GEO_OBJECT_NAMES[5], PRE_DEFINED_GEO_OBJECT_DESCRIPTIONS[5], PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[5]),
            new Cabin(PRE_DEFINED_CABIN_OBJECT_POINT[6],PRE_DEFINED_GEO_OBJECT_NAMES[6], PRE_DEFINED_GEO_OBJECT_DESCRIPTIONS[6], PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[6]),
            new Cabin(PRE_DEFINED_CABIN_OBJECT_POINT[7],PRE_DEFINED_GEO_OBJECT_NAMES[7], PRE_DEFINED_GEO_OBJECT_DESCRIPTIONS[7], PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[7]),
            new Cabin(PRE_DEFINED_CABIN_OBJECT_POINT[8],PRE_DEFINED_GEO_OBJECT_NAMES[8], PRE_DEFINED_GEO_OBJECT_DESCRIPTIONS[8], PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[8]),
            new Cabin(PRE_DEFINED_CABIN_OBJECT_POINT[9],PRE_DEFINED_GEO_OBJECT_NAMES[9], PRE_DEFINED_GEO_OBJECT_DESCRIPTIONS[9], PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[9]),
            new Cabin(PRE_DEFINED_CABIN_OBJECT_POINT[10],PRE_DEFINED_GEO_OBJECT_NAMES[10], PRE_DEFINED_GEO_OBJECT_DESCRIPTIONS[10], PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[10]),
            new Cabin(PRE_DEFINED_CABIN_OBJECT_POINT[11],PRE_DEFINED_GEO_OBJECT_NAMES[11], PRE_DEFINED_GEO_OBJECT_DESCRIPTIONS[11], PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[11]),
            new Cabin(PRE_DEFINED_CABIN_OBJECT_POINT[12],PRE_DEFINED_GEO_OBJECT_NAMES[12], PRE_DEFINED_GEO_OBJECT_DESCRIPTIONS[12], PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[12]),
            new Cabin(PRE_DEFINED_CABIN_OBJECT_POINT[13],PRE_DEFINED_GEO_OBJECT_NAMES[13], PRE_DEFINED_GEO_OBJECT_DESCRIPTIONS[13], PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[13]),
            new Cabin(PRE_DEFINED_CABIN_OBJECT_POINT[14],PRE_DEFINED_GEO_OBJECT_NAMES[14], PRE_DEFINED_GEO_OBJECT_DESCRIPTIONS[14], PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[14]),
            new Cabin(PRE_DEFINED_CABIN_OBJECT_POINT[15],PRE_DEFINED_GEO_OBJECT_NAMES[15], PRE_DEFINED_GEO_OBJECT_DESCRIPTIONS[15], PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[15]),
            new Cabin(PRE_DEFINED_CABIN_OBJECT_POINT[16],PRE_DEFINED_GEO_OBJECT_NAMES[16], PRE_DEFINED_GEO_OBJECT_DESCRIPTIONS[16], PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[16]),
            new Cabin(PRE_DEFINED_CABIN_OBJECT_POINT[17],PRE_DEFINED_GEO_OBJECT_NAMES[17], PRE_DEFINED_GEO_OBJECT_DESCRIPTIONS[17], PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[17]),
            new Cabin(PRE_DEFINED_CABIN_OBJECT_POINT[18],PRE_DEFINED_GEO_OBJECT_NAMES[18], PRE_DEFINED_GEO_OBJECT_DESCRIPTIONS[18], PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[18]),
            new Cabin(PRE_DEFINED_CABIN_OBJECT_POINT[19],PRE_DEFINED_GEO_OBJECT_NAMES[19], PRE_DEFINED_GEO_OBJECT_DESCRIPTIONS[19], PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[19]),
    };

    private String mGeoName;
    private String mCoördinates;
    private int mGeoImageName;

    public GeoObject(String mGeoName, String mCoördinates, int mGeoImageName) {
        this.mGeoName = mGeoName;
        this.mGeoImageName = mGeoImageName;
        this.mCoördinates = mCoördinates;
    }
    public String getmGeoName() {
        return mGeoName;
    }
    public String getmCoördinatesString(){return mCoördinates;}
    public void setmGeoName(String mGeoName) {
        this.mGeoName = mGeoName;
    }
    public int getmGeoImageName() {
        return mGeoImageName;
    }
    public void setmGeoImageName(int mGeoImageName) {
        this.mGeoImageName = mGeoImageName;
    }

}
