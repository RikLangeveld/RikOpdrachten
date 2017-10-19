package com.school.langevr004.kungsleden;

import org.osmdroid.util.GeoPoint;

/**
 * Created by langevr004 on 17-10-2017.
 */

public class GeoObject {

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

    public static final String[] PRE_DEFINED_GEO_OBJECT_NAMES = {
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

    public static final int[] PRE_DEFINED_GEO_OBJECT_IMAGE_IDS = {
            R.drawable.placeholder_cabin,
            R.drawable.abiskojaure,
            R.drawable.placeholder_cabin,
            R.drawable.tjaktjapasset,
            R.drawable.salka,
            R.drawable.placeholder_cabin,
            R.drawable.placeholder_cabin,
            R.drawable.placeholder_cabin,
            R.drawable.placeholder_cabin,
            R.drawable.placeholder_cabin,
            R.drawable.placeholder_cabin,
            R.drawable.placeholder_cabin,
            R.drawable.placeholder_cabin,
            R.drawable.placeholder_cabin,
            R.drawable.placeholder_cabin,
            R.drawable.placeholder_cabin,
            R.drawable.placeholder_cabin,
            R.drawable.placeholder_cabin,
            R.drawable.placeholder_cabin,
            R.drawable.placeholder_cabin,
    };

    public static final GeoPoint[] PRE_DEFINED_CABIN_OBJECT_POINT = {
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

    private String mGeoName;
    private int mGeoImageName;

    public GeoObject(String mGeoName, int mGeoImageName) {
        this.mGeoName = mGeoName;
        this.mGeoImageName = mGeoImageName;
    }
    public String getmGeoName() {
        return mGeoName;
    }
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
