package com.school.langevr004.kungsleden.DatabaseTest.activity;

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

import com.school.langevr004.kungsleden.DatabaseTest.data.DataSource;
import com.school.langevr004.kungsleden.DatabaseTest.model.TravelNotes;
import com.school.langevr004.kungsleden.DatabaseTest.utility.ConfirmDeleteDialog;
import com.school.langevr004.kungsleden.R;

public class TravelNotesDetailsActivity extends AppCompatActivity
        implements ConfirmDeleteDialog.ConfirmDeleteDialogListener
{

    private TravelNotes travelNotes;
    private TextView title;
    private TextView platform;
    private TextView status;
    private TextView date;
    private TextView notes;

    /*Open TravelNotesDetailsActivity.java, declare a TravelNotes object and five TextView objects.
    Call them title, platform, status, date and notes. Inside a separate method (called from ‘onCreate’ of course), initialize the TextViews.
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_notes_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Get the travelNotes from the intent, which was passed as parameter
        this.travelNotes = (TravelNotes) getIntent().getSerializableExtra("selectedTravelNotes");
        setGameViews();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.action_modify_game);
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
        //Set the TravelNotes Card with updated travelNotes
        travelNotes = (TravelNotes) data.getSerializableExtra("selectedGame");
        setGameViews();
    }


    public void setGameViews(){
        title = (TextView)findViewById(R.id.detailTitle);
        platform = (TextView)findViewById(R.id.detailPlatformB);
        status = (TextView)findViewById(R.id.detailStatusB);
        date = (TextView)findViewById(R.id.detailDateB);
        notes = (TextView)findViewById(R.id.detailNotesB);

        title.setText(travelNotes.getTitle().toString());
        platform.setText(travelNotes.getPlatform().toString());
        status.setText(travelNotes.getGameStatus().toString());
        date.setText(travelNotes.getDateAdded().toString());
        notes.setText(travelNotes.getNotes().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_details, menu);
        return true;
    }


    @Override
    public void onDialogPositiveClick(android.app.DialogFragment dialog)
    {
        //User clicked on the confirm button of the Dialog, delete the travelNotes from Database
        DataSource datasource = new DataSource(this);
        //We only need the id of the travelNotes to delete it
        datasource.deleteGame(travelNotes.getId());
        //TravelNotes has been deleted, go back to the MainActivityTravelNotes
        showGameDeletedToast();
        finish();
    }

    @Override
    public void onDialogNegativeClick(android.app.DialogFragment dialog)
    {
        //Do nothing, Dialog will disappear
    }

    private void showGameDeletedToast()
    {
        Context context = getApplicationContext();
        String text = String.format("%s %s", travelNotes.getTitle(), getString(R.string.game_deleted));
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_delete_game)
        {
            // Show the ConfirmDeleteDialog
            android.app.DialogFragment dialog = new ConfirmDeleteDialog();
            Bundle bundle = new Bundle();
            bundle.putString("message", getString(R.string.dialog_game_deletion_single));
            bundle.putString("positiveButton", getString(R.string.dialog_game_deletion_positive));
            dialog.setArguments(bundle);
            dialog.show(getFragmentManager(), "ConfirmDeleteDialog");
        }
        return super.onOptionsItemSelected(item);
    }

}
