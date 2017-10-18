package com.school.langevr004.kungsleden.DatabaseTest.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "game.db";
    private static final int DATABASE_VERSION = 1;

    // Creating the table
    private static final String DATABASE_CREATE =
            "CREATE TABLE " + TravelNotesContract.TravelNotesEntry.TABLE_NAME +
                    "(" +
                    TravelNotesContract.TravelNotesEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                    + TravelNotesContract.TravelNotesEntry.COLUMN_NAME_TITLE + " TEXT, "
                    + TravelNotesContract.TravelNotesEntry.COLUMN_NAME_DATE + " TEXT, "
                    + TravelNotesContract.TravelNotesEntry.COLUMN_NAME_STATUS + " TEXT, "
                    + TravelNotesContract.TravelNotesEntry.COLUMN_NAME_NOTES + " TEXT )";

    //Constructor
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TravelNotesContract.TravelNotesEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
