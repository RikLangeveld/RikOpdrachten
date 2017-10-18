package com.school.langevr004.kungsleden.DatabaseTest.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.school.langevr004.kungsleden.DatabaseTest.model.TravelNotes;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Remy on 26-9-2017.
 */

public class DataSource {

    //Local variables and constants
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private DBHelper mDBHelper;
    private String[] TRAVEL_NOTES_ALL_COLUMNS = {
            TravelNotesContract.TravelNotesEntry.COLUMN_NAME_ID,
            TravelNotesContract.TravelNotesEntry.COLUMN_NAME_TITLE,
            TravelNotesContract.TravelNotesEntry.COLUMN_NAME_DATE,
            TravelNotesContract.TravelNotesEntry.COLUMN_NAME_TRAIL,
            TravelNotesContract.TravelNotesEntry.COLUMN_NAME_NOTES };


    public DataSource(Context context) {
        mDBHelper = new DBHelper(context);
    }
    // Opens the database to use it
    public void open()  {
        mDatabase = mDBHelper.getWritableDatabase();
    }
    // Closes the database when you no longer need it
    public void close() {
        mDBHelper.close();
    }

    public void saveTravelNotes(TravelNotes travelNotes) {

        // Open connection to write data
        open();
        ContentValues values = new ContentValues();
        values.put(TravelNotesContract.TravelNotesEntry.COLUMN_NAME_TITLE, travelNotes.getTitle());
        values.put(TravelNotesContract.TravelNotesEntry.COLUMN_NAME_DATE, travelNotes.getDateAdded());
        values.put(TravelNotesContract.TravelNotesEntry.COLUMN_NAME_TRAIL, travelNotes.getTravelNotesTrail());
        values.put(TravelNotesContract.TravelNotesEntry.COLUMN_NAME_NOTES, travelNotes.getNotes());
        // Inserting Row
        mDatabase.insert(TravelNotesContract.TravelNotesEntry.TABLE_NAME, null, values);
        close(); // Closing database connection
    }

    public void modifyTravelNotes(TravelNotes travelNotes) {
        // Open connection to write data
        open();
        ContentValues values = new ContentValues();
        values.put(TravelNotesContract.TravelNotesEntry.COLUMN_NAME_TITLE, travelNotes.getTitle());
        values.put(TravelNotesContract.TravelNotesEntry.COLUMN_NAME_DATE, travelNotes.getDateAdded());
        values.put(TravelNotesContract.TravelNotesEntry.COLUMN_NAME_TRAIL, travelNotes.getTravelNotesTrail());
        values.put(TravelNotesContract.TravelNotesEntry.COLUMN_NAME_NOTES, travelNotes.getNotes());

        mDatabase.update(TravelNotesContract.TravelNotesEntry.TABLE_NAME, values, TravelNotesContract.TravelNotesEntry.COLUMN_NAME_ID + "= ?", new String[]{String.valueOf(travelNotes.getId())});
        mDatabase.close(); // Closing database connection
    }

    public Cursor getAllGames() {
        return mDatabase.query(TravelNotesContract.TravelNotesEntry.TABLE_NAME, TRAVEL_NOTES_ALL_COLUMNS, null, null, null, null, null);
    }

    public List<TravelNotes> getTravelNotes()
    {
        //Open connection to read only
        mDatabase = mDBHelper.getReadableDatabase();

        String selectQuery = "SELECT  " +
                TravelNotesContract.TravelNotesEntry.COLUMN_NAME_ID + ',' +
                TravelNotesContract.TravelNotesEntry.COLUMN_NAME_TITLE + ',' +
                TravelNotesContract.TravelNotesEntry.COLUMN_NAME_DATE + ',' +
                TravelNotesContract.TravelNotesEntry.COLUMN_NAME_TRAIL + ',' +
                TravelNotesContract.TravelNotesEntry.COLUMN_NAME_NOTES +
                " FROM " + TravelNotesContract.TravelNotesEntry.TABLE_NAME;

        //User user = new User();
        List<TravelNotes> travelNotesList = new ArrayList<>();

        Cursor cursor = mDatabase.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                TravelNotes travelNotes = new TravelNotes();
                travelNotes.setId(cursor.getInt(cursor.getColumnIndex(TravelNotesContract.TravelNotesEntry.COLUMN_NAME_ID)));
                travelNotes.setTitle(cursor.getString(cursor.getColumnIndex(TravelNotesContract.TravelNotesEntry.COLUMN_NAME_TITLE)));
                travelNotes.setDateAdded(cursor.getString(cursor.getColumnIndex(TravelNotesContract.TravelNotesEntry.COLUMN_NAME_DATE)));
                travelNotes.setTravelNotesTrail(cursor.getString(cursor.getColumnIndex(TravelNotesContract.TravelNotesEntry.COLUMN_NAME_TRAIL)));
                travelNotes.setNotes(cursor.getString(cursor.getColumnIndex(TravelNotesContract.TravelNotesEntry.COLUMN_NAME_NOTES)));
                travelNotesList.add(travelNotes);

            } while (cursor.moveToNext());
        }
        cursor.close();
        close();
        return travelNotesList;
    }

    public void deleteTravelNotes(long user_Id) {
        open();
        // It's a good practice to use parameter ?, instead of concatenate string
        mDatabase.delete(TravelNotesContract.TravelNotesEntry.TABLE_NAME, TravelNotesContract.TravelNotesEntry.COLUMN_NAME_ID + " =?",
                new String[]{Long.toString(user_Id)});
        close(); // Closing database connection
    }


    public void deleteAll()
    {
        open();
        mDatabase.delete(TravelNotesContract.TravelNotesEntry.TABLE_NAME, null,null);
        close();
    }

}