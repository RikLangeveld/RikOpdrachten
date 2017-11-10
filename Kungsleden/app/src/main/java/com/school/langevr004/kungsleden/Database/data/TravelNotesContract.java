package com.school.langevr004.kungsleden.Database.data;

import android.provider.BaseColumns;

public final class TravelNotesContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.

    private TravelNotesContract() {}

    /* inner class voor het defineren van de table */
    public static class TravelNotesEntry implements BaseColumns {
        //table name
        public static final String TABLE_NAME = "Games";
        //Table columns names
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_TRAIL = "status";
        public static final String COLUMN_NAME_NOTES = "notes";
    }
}
