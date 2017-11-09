package com.school.langevr004.kungsleden.DatabaseTest.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.school.langevr004.kungsleden.DatabaseTest.data.DataSource;
import com.school.langevr004.kungsleden.DatabaseTest.model.TravelNotes;
import com.school.langevr004.kungsleden.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddTravelNotesActivity extends AppCompatActivity {

    EditText titleInput;
    EditText notesInput;
    Spinner trailSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_travel_notes);

        //Vind de toolbar en zet de tekst
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Zet alle refrences nodig voor de page
        titleInput = (EditText)findViewById(R.id.addTitle);
        notesInput = (EditText)findViewById(R.id.addNotes);
        trailSpinner = (Spinner)findViewById(R.id.trailSpinner);

        // Create een ArrayAdapter met de lijst van lijt met trails, vervolgens wordt de layout gekozen die gebruikt moet worden.
        ArrayAdapter trailAdapter = ArrayAdapter.createFromResource(this, R.array.travel_notes_trail, android.R.layout.simple_spinner_item);
        trailAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        trailSpinner.setAdapter(trailAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveTravelNotes();
            }
        });
    }


    void saveTravelNotes() {
        //Sla de datum op om op te slaan waneer een note is gecreeërd
        String curDate = AddTravelNotesActivity.getSimpleCurrentDate();
        //Sla de ingevoede data van de gebruiker op
        String title = titleInput.getText().toString();
        String TravelNotesTrail = trailSpinner.getSelectedItem().toString();
        String notes = notesInput.getText().toString();
        if ((title != null) && title.isEmpty()) {
            // Zorgt dat je een title heb ingevoerd anders komt er een error message
            AddTravelNotesActivity.setErrorText(titleInput, getString(R.string.title_is_required));
            showToast(getString(R.string.title_field_is_empty));
        } else {
            // Create een DBCRUD object
            DataSource dataSource = new DataSource(this);
            // maak een travelNotes object. (NOTE: de id wordt gezet in DBCRUD.saveTravelNotes())
            TravelNotes travelNotes = new TravelNotes(-1, title, curDate, TravelNotesTrail, notes);
            // Save the travelNotes to the Database
            dataSource.saveTravelNotes(travelNotes);
            // Stuur een bericht naar de gebruiker dat de note is toegevoegd
            showToast(getString(R.string.travel_note_has_been_added));

            // Go back to MainActivityTravelNotes
            finish();
        }
    }

    private static String getSimpleCurrentDate() {
        // Format dates
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        //Today's date, but with time included, which we don't want
        Date date = new Date();
        // Format.format returns een string uit de dataformat class
        return format.format(date);
    }

    private void showToast(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    private static void setErrorText(EditText editText, String message) {
        int RGB = Color.argb(255, 255, 0, 0);
        ForegroundColorSpan fgcspan = new ForegroundColorSpan(RGB);
        SpannableStringBuilder ssbuilder = new SpannableStringBuilder(message);
        ssbuilder.setSpan(fgcspan, 0, message.length(), 0);
        editText.setError(ssbuilder);
    }
}
