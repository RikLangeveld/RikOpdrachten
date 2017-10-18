package com.school.langevr004.kungsleden.DatabaseTest.activity;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.school.langevr004.kungsleden.DatabaseTest.data.DataSource;
import com.school.langevr004.kungsleden.DatabaseTest.model.TravelNotes;
import com.school.langevr004.kungsleden.DatabaseTest.utility.ConfirmDeleteDialog;
import com.school.langevr004.kungsleden.R;

public class ModifyTravelNotesActivity extends AppCompatActivity
        implements ConfirmDeleteDialog.ConfirmDeleteDialogListener
{

    private TravelNotes travelNotes;
    private EditText titleInput;
    private EditText platformInput;
    private Spinner statusSpinner;
    private EditText notesInput;
    private ArrayAdapter statusAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_travel_notes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        travelNotes = (TravelNotes) intent.getSerializableExtra("selectedGame");
        setTravelNotesView();
        statusAdapter = ArrayAdapter.createFromResource(this,
                R.array.travel_notes_status, android.R.layout.simple_spinner_item);


        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(statusAdapter);
        setSpinnerPosition(statusAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modifyTravelNotes();
            }
        });
    }

    private void setTravelNotesView()
    {
        titleInput = (EditText)findViewById(R.id.modifyTitle);
        platformInput = (EditText)findViewById(R.id.modifyPlatform);
        statusSpinner = (Spinner)findViewById(R.id.modifyStatusSpinner);
        notesInput = (EditText)findViewById(R.id.modifyNotes);
        titleInput.setText(travelNotes.getTitle());
        platformInput.setText(travelNotes.getPlatform());
        notesInput.setText(travelNotes.getNotes());
    }


    private void setSpinnerPosition(ArrayAdapter adapter){
        if (!travelNotes.getTravelNotesStatus().equals(null)){
            //Gets the position of the correct spinner item by comparing
            //which item of the Spinner matches with the gameStatus
            int spinnerPosition = adapter.getPosition(travelNotes.getTravelNotesStatus());
            //Display the correct gameStatus in the Spinner based on the found position
            statusSpinner.setSelection(spinnerPosition);
        }
    }

    void modifyTravelNotes() {
        // Get the input from the Views
        String title = titleInput.getText().toString();
        String platform = platformInput.getText().toString();
        String gameStatus = statusSpinner.getSelectedItem().toString();
        String notes = notesInput.getText().toString();

        if ((title != null) && title.isEmpty()) {
            // Make EditText titleInput display an error message, and display a toast
            // That the title field is empty
            ModifyTravelNotesActivity.setErrorText(titleInput, getString(R.string.title_is_required));
            showToast(getString(R.string.title_field_is_empty));
        } else if ((platform != null) && platform.isEmpty()) {
            // Make EditText platformInput display an error message, and display a toast
            // That the platform field is empty
            ModifyTravelNotesActivity.setErrorText(platformInput, getString(R.string.platform_is_required));
            showToast(getString(R.string.platform_field_is_empty));
        } else {
            // Update the travelNotes with the new data
            travelNotes.setTitle(title);
            travelNotes.setPlatform(platform);
            travelNotes.setTravelNotesStatus(gameStatus);
            travelNotes.setNotes(notes);

            // Create a DataSource object, and pass it the context of this activity
            DataSource datasource = new DataSource (this);
            datasource.modifyTravelNotes(travelNotes);

            //Notify the user of the success
            showToast(getString(R.string.travel_note_has_been_modified));

            // Starting the previous Intent
            Intent previousActivity = new Intent(this, TravelNotesDetailsActivity.class);
            // Sending the data to TravelNotesDetailsActivity
            previousActivity.putExtra("selectedGame", travelNotes);
            setResult(1000, previousActivity);
            finish();
        }
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


    @Override
    public void onBackPressed() {
        super.onResume();  // Always call the superclass method first
        // Save travelNotes and go back to MainActivityTravelNotes
        modifyTravelNotes();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_modify, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_cancel)
        {
            // Show the ConfirmDiscardDialog
            DialogFragment dialog = new ConfirmDeleteDialog();
            Bundle bundle = new Bundle();
            bundle.putString("message", getString(R.string.dialog_travel_note_discard));
            bundle.putString("positiveButton", getString(R.string.dialog_travel_notes_modify_positive));
            dialog.setArguments(bundle);
            dialog.show(getFragmentManager(), "ConfirmDeleteDialog");

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog)
    {
        Intent previousActivity = new Intent(this, TravelNotesDetailsActivity.class);
        //Sending the origional data to GameDetailActivity
        previousActivity.putExtra("selectedGame", travelNotes);
        setResult(1000, previousActivity);
        finish();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog)
    {

    }

}
