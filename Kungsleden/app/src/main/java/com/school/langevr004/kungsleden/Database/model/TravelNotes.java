package com.school.langevr004.kungsleden.Database.model;
import java.io.Serializable;

public class TravelNotes implements Serializable {

    private int id;
    private String title;
    private String dateAdded; // String, since you cannot save date/time values in SQLite
    private String trail;
    private String notes;

    public TravelNotes(int id, String title, String dateAdded, String trail, String notes) {
        this.id = id;
        this.title = title;
        this.dateAdded = dateAdded;
        this.trail = trail;
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

    public String getTravelNotesTrail() {
        return trail;
    }

    public void setTravelNotesTrail(String trail) {
        this.trail = trail;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

