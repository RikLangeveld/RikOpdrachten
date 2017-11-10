package com.school.langevr004.kungsleden.Database.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.school.langevr004.kungsleden.Database.data.DataSource;
import com.school.langevr004.kungsleden.Database.model.TravelNotes;
import com.school.langevr004.kungsleden.Database.utility.ConfirmDeleteDialog;
import com.school.langevr004.kungsleden.R;

public class TravelNotesDetailsActivity extends AppCompatActivity
        implements ConfirmDeleteDialog.ConfirmDeleteDialogListener
{

    private TravelNotes travelNotes;
    private TextView title;
    private TextView trail;
    private TextView date;
    private TextView notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_notes_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.travelNotes = (TravelNotes) getIntent().getSerializableExtra("selectedTravelNotes");
        setTravelNotesViews();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.action_modify_travel_notes);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(TravelNotesDetailsActivity.this, ModifyTravelNotesActivity.class);
                intent.putExtra("selectedGame", travelNotes);
                startActivityForResult(intent, 1000);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        travelNotes = (TravelNotes) data.getSerializableExtra("selectedGame");
        setTravelNotesViews();
    }


    public void setTravelNotesViews(){
        title = (TextView)findViewById(R.id.detailTitle);
        trail = (TextView)findViewById(R.id.detailTrailB);
        date = (TextView)findViewById(R.id.detailDateB);
        notes = (TextView)findViewById(R.id.detailNotesB);

        title.setText(travelNotes.getTitle().toString());
        trail.setText(travelNotes.getTravelNotesTrail().toString());
        date.setText(travelNotes.getDateAdded().toString());
        notes.setText(travelNotes.getNotes().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_travel_notes_details, menu);
        return true;
    }


    @Override
    public void onDialogPositiveClick(android.app.DialogFragment dialog)
    {
        //Als de user op confirm klikt wordt het object uit de db verwijderd.
        DataSource datasource = new DataSource(this);
        //Pak de id van de travel note om het te verwijderen.
        datasource.deleteTravelNotes(travelNotes.getId());
        //na verwijderen gaat de scene terug naar mainActivityTravelNotes
        showTravelNotesDeletedToast();
        finish();
    }

    @Override
    public void onDialogNegativeClick(android.app.DialogFragment dialog)
    {
        //Do nothing, Dialog will disappear
    }

    private void showTravelNotesDeletedToast()
    {
        Context context = getApplicationContext();
        String text = String.format("%s %s", travelNotes.getTitle(), getString(R.string.travel_note_deleted));
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_delete_travel_notes)
        {
            android.app.DialogFragment dialog = new ConfirmDeleteDialog();
            Bundle bundle = new Bundle();
            bundle.putString("message", getString(R.string.dialog_travel_note_deletion_single));
            bundle.putString("positiveButton", getString(R.string.dialog_travel_notes_deletion_positive));
            dialog.setArguments(bundle);
            dialog.show(getFragmentManager(), "ConfirmDeleteDialog");
        }
        return super.onOptionsItemSelected(item);
    }

}
