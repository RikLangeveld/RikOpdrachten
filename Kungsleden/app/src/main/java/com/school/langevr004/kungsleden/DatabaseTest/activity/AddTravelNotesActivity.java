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
    Spinner statusSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_travel_notes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        titleInput = (EditText)findViewById(R.id.addTitle);
        notesInput = (EditText)findViewById(R.id.addNotes);
        statusSpinner = (Spinner)findViewById(R.id.statusSpinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter statusAdapter = ArrayAdapter.createFromResource(this,
                R.array.travel_notes_status, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        statusSpinner.setAdapter(statusAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveTravelNotes();
            }
        });
    }

    void saveTravelNotes() {
        // Get the current date in numbered day-month-year format
        String curDate = AddTravelNotesActivity.getSimpleCurrentDate();
        // Retrieve the input from the user
        String title = titleInput.getText().toString();
        String TravelNotesStatus = statusSpinner.getSelectedItem().toString();
        String notes = notesInput.getText().toString();
        if ((title != null) && title.isEmpty()) {
            // Make EditText titleInput display an error message, and display a toast
            // That the title field is empty
            AddTravelNotesActivity.setErrorText(titleInput, getString(R.string.title_is_required));
            showToast(getString(R.string.title_field_is_empty));
        } else {
            // Create a DBCRUD object, and pass it the context of this activity
            DataSource dataSource = new DataSource(this);
            // Make a travelNotes object based on the input. The correct id will be set in DBCRUD.saveTravelNotes()
            TravelNotes travelNotes = new TravelNotes(-1, title, curDate, TravelNotesStatus, notes);
            // Save the travelNotes to the Database
            dataSource.saveTravelNotes(travelNotes);
            // Notify the user with a toast that the travelNotes has been added
            showToast(getString(R.string.travel_note_has_been_added));

            // Go back to MainActivityTravelNotes
            finish();
        }
    }

    private static String getSimpleCurrentDate() {
        // Formatter that will convert dates into the day-month-year format
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        //Today's date, but with time included, which we don't want
        Date today = new Date();
        // Format.format returns a string
        return format.format(today);
    }

    private void showToast(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    private static void setErrorText(EditText editText, String message) {
        // Get the color white in integer form
        int RGB = Color.argb(255, 255, 0, 0);
// Object that contains the color white
        ForegroundColorSpan fgcspan = new ForegroundColorSpan(RGB);
// Object that will hold the message, and makes it possible to change the color of the text
        SpannableStringBuilder ssbuilder = new SpannableStringBuilder(message);
// Give the message from the first till the last character a white color.
// The last '0' means that the message should not display additional behaviour
        ssbuilder.setSpan(fgcspan, 0, message.length(), 0);
// Make the EditText display the error message
        editText.setError(ssbuilder);
    }


}
