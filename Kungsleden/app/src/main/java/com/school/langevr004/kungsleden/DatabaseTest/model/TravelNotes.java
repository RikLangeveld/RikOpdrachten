package com.school.langevr004.kungsleden.DatabaseTest.model;
import java.io.Serializable;

public class TravelNotes implements Serializable {

    // Property help us to keep data
    private int id;
    private String title;
    private String dateAdded; // String, since you cannot save date/time values in SQLite
    private String gameStatus;
    private String notes;

    public TravelNotes(int id, String title, String dateAdded, String gameStatus, String notes) {
        this.id = id;
        this.title = title;
        this.dateAdded = dateAdded;
        this.gameStatus = gameStatus;
        this.notes = notes;
    }

    public TravelNotes() {

    }

    public long getId() {
        return (long) id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getTravelNotesStatus() {
        return gameStatus;
    }

    public void setTravelNotesStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

