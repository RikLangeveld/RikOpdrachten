package com.school.langevr004.kungsleden.DatabaseTest.activity;


import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.school.langevr004.kungsleden.DatabaseTest.adapter.TravelNotesListItemAdapter;
import com.school.langevr004.kungsleden.DatabaseTest.data.DataSource;
import com.school.langevr004.kungsleden.DatabaseTest.model.TravelNotes;
import com.school.langevr004.kungsleden.DatabaseTest.utility.ConfirmDeleteDialog;
import com.school.langevr004.kungsleden.R;

import java.util.Collections;
import java.util.List;

public class MainActivityTravelNotes extends AppCompatActivity
        implements ConfirmDeleteDialog.ConfirmDeleteDialogListener
{
    private RecyclerView gameListView;
    private TravelNotesListItemAdapter mAdapter;
    private List<TravelNotes> mTravelNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_travel_notes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gameListView = (RecyclerView) findViewById(R.id.travelNotesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        gameListView.setLayoutManager(mLayoutManager);
        gameListView.setHasFixedSize(true);

        //Adding ItemAnimator
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(100L);
        itemAnimator.setRemoveDuration(100L);
        gameListView.setItemAnimator(itemAnimator);

        //Adding Gestures, this makes you possible to swipe and move cards inside the ListView
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP
                | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT)
        {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                // Swap the Cards
                Collections.swap(mTravelNotes, viewHolder.getAdapterPosition(), target.getAdapterPosition());
                // Notify com.example.test.gamesbacklog.adapter Content has changed
                mAdapter.updateList(mTravelNotes);
                mAdapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                // Create a DataSource object, and pass it the context of this activity
                DataSource dataSource = new DataSource(MainActivityTravelNotes.this);
                // Delete the list of games from Database
                dataSource.deleteAll();
                for (TravelNotes game : mTravelNotes) {
                    dataSource.saveTravelNotes(game);
                }
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                //Remove swiped item from list and notify the RecyclerView
                // Create a DataSource object, and pass it the context of this activity
                DataSource dataSource = new DataSource(MainActivityTravelNotes.this);
                // Delete the list of games from Database
                dataSource.deleteGame(mTravelNotes.get(viewHolder.getAdapterPosition()).getId());

                // Remove game from temporary list
                mTravelNotes.remove(viewHolder.getAdapterPosition());

                // Notify com.example.test.gamesbacklog.adapter Content has changed
                mAdapter.updateList(mTravelNotes);
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                mAdapter.notifyItemRangeChanged(viewHolder.getAdapterPosition(), mTravelNotes.size());

                // Display toast with Feedback
                //showToast(getString(R.string.swipe_delete));
                Context context = getApplicationContext();
                String text = String.format(getString(R.string.swipe_delete));
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(gameListView);
        //updateUI();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityTravelNotes.this, AddTravelNotesActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_delete_all) {
            // Show the ConfirmDeleteDialog
            DialogFragment dialog = new ConfirmDeleteDialog();
            Bundle bundle = new Bundle();
            bundle.putString("message", getString(R.string.dialog_game_deletion_all));
            bundle.putString("positiveButton", getString(R.string.dialog_game_deletion_positive));
            dialog.setArguments(bundle);
            dialog.show(getFragmentManager(), "ConfirmDeleteDialog");
        }
        return super.onOptionsItemSelected(item);
    }

    public void onResume() {
        super.onResume();  // Always call the superclass method first
        updateUI();
    }

    private void updateUI() {
        DataSource dataSource = new DataSource(this);
        // Get the list of games from Database
        mTravelNotes = dataSource.getGames();
        if (mAdapter == null) {
            mAdapter = new TravelNotesListItemAdapter(mTravelNotes, this);
            gameListView.setAdapter(mAdapter);
        } else {
            mAdapter.updateList(mTravelNotes);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog)
    {
        //User clicked on the confirm button of the Dialog, delete the game from Database
        DataSource dataSource = new DataSource(this);
        //Delete all games
        dataSource.deleteAll();
        //Games have been deleted, go back to the MainActivityTravelNotes
        showGameDeletedToast();
        finish();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog)
    {
        //Do nothing, Dialog will disappear
    }

    private void showGameDeletedToast()
    {
        Context context = getApplicationContext();
        String text = getString(R.string.games_deleted);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
