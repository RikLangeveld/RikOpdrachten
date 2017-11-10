package com.school.langevr004.kungsleden.Database.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.school.langevr004.kungsleden.Database.model.TravelNotes;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

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
    // Opens de database voor gebruik
    public void open()  {
        mDatabase = mDBHelper.getWritableDatabase();
    }
    // Sluit de DB als hij niet meer gebruikt wordt
    public void close() {
        mDBHelper.close();
    }

    public void saveTravelNotes(TravelNotes travelNotes) {

        // Open de connectie om een nieuwe travel note in de db op te slaan
        open();
        ContentValues values = new ContentValues();
        values.put(TravelNotesContract.TravelNotesEntry.COLUMN_NAME_TITLE, travelNotes.getTitle());
        values.put(TravelNotesContract.TravelNotesEntry.COLUMN_NAME_DATE, travelNotes.getDateAdded());
        values.put(TravelNotesContract.TravelNotesEntry.COLUMN_NAME_TRAIL, travelNotes.getTravelNotesTrail());
        values.put(TravelNotesContract.TravelNotesEntry.COLUMN_NAME_NOTES, travelNotes.getNotes());
        // Inserting Row
        mDatabase.insert(TravelNotesContract.TravelNotesEntry.TABLE_NAME, null, values);
        close(); // sluit de database connectie
    }

    public void modifyTravelNotes(TravelNotes travelNotes) {
        // Open connection om travel notes aan te passen
        open();
        ContentValues values = new ContentValues();
        values.put(TravelNotesContract.TravelNotesEntry.COLUMN_NAME_TITLE, travelNotes.getTitle());
        values.put(TravelNotesContract.TravelNotesEntry.COLUMN_NAME_DATE, travelNotes.getDateAdded());
        values.put(TravelNotesContract.TravelNotesEntry.COLUMN_NAME_TRAIL, travelNotes.getTravelNotesTrail());
        values.put(TravelNotesContract.TravelNotesEntry.COLUMN_NAME_NOTES, travelNotes.getNotes());

        mDatabase.update(TravelNotesContract.TravelNotesEntry.TABLE_NAME, values, TravelNotesContract.TravelNotesEntry.COLUMN_NAME_ID + "= ?", new String[]{String.valueOf(travelNotes.getId())});
        mDatabase.close(); // sluit database connectie
    }

    public List<TravelNotes> getTravelNotes()
    {
        //Open connectie om de data uit de database te lezen (read only)
        mDatabase = mDBHelper.getReadableDatabase();

        String selectQuery = "SELECT  " +
                TravelNotesContract.TravelNotesEntry.COLUMN_NAME_ID + ',' +
                TravelNotesContract.TravelNotesEntry.COLUMN_NAME_TITLE + ',' +
                TravelNotesContract.TravelNotesEntry.COLUMN_NAME_DATE + ',' +
                TravelNotesContract.TravelNotesEntry.COLUMN_NAME_TRAIL + ',' +
                TravelNotesContract.TravelNotesEntry.COLUMN_NAME_NOTES +
                " FROM " + TravelNotesContract.TravelNotesEntry.TABLE_NAME;

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

    //Open de DB voert een Delere actie uit en sluit de connectie weer.
    public void deleteTravelNotes(long user_Id) {
        open();
        mDatabase.delete(TravelNotesContract.TravelNotesEntry.TABLE_NAME, TravelNotesContract.TravelNotesEntry.COLUMN_NAME_ID + " =?",
                new String[]{Long.toString(user_Id)});
        close(); // Closing database connection
    }

    //Open de DB voert een Delete all actie uit en sluit de connectie weer.
    public void deleteAll()
    {
        open();
        mDatabase.delete(TravelNotesContract.TravelNotesEntry.TABLE_NAME, null,null);
        close();
    }

}