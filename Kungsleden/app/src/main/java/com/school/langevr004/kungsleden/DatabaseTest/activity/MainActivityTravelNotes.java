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
    private RecyclerView travelNotesListView;
    private TravelNotesListItemAdapter mAdapter;
    private List<TravelNotes> mTravelNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_travel_notes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        travelNotesListView = (RecyclerView) findViewById(R.id.travelNotesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        travelNotesListView.setLayoutManager(mLayoutManager);
        travelNotesListView.setHasFixedSize(true);

        //Adding ItemAnimator
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(100L);
        itemAnimator.setRemoveDuration(100L);
        travelNotesListView.setItemAnimator(itemAnimator);

        //Adding Gestures, this makes you possible to swipe and move cards inside the ListView
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP
                | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT)
        {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

                Collections.swap(mTravelNotes, viewHolder.getAdapterPosition(), target.getAdapterPosition());
                mAdapter.updateList(mTravelNotes);
                mAdapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());

                DataSource dataSource = new DataSource(MainActivityTravelNotes.this);
                dataSource.deleteAll();
                for (TravelNotes travelNotes : mTravelNotes) {
                    dataSource.saveTravelNotes(travelNotes);
                }
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

                DataSource dataSource = new DataSource(MainActivityTravelNotes.this);

                dataSource.deleteTravelNotes(mTravelNotes.get(viewHolder.getAdapterPosition()).getId());

                mTravelNotes.remove(viewHolder.getAdapterPosition());

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
        itemTouchHelper.attachToRecyclerView(travelNotesListView);
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
            bundle.putString("message", getString(R.string.dialog_travel_note_deletion_all));
            bundle.putString("positiveButton", getString(R.string.dialog_travel_notes_deletion_positive));
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

        mTravelNotes = dataSource.getTravelNotes();
        if (mAdapter == null) {
            mAdapter = new TravelNotesListItemAdapter(mTravelNotes, this);
            travelNotesListView.setAdapter(mAdapter);
        } else {
            mAdapter.updateList(mTravelNotes);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog)
    {
        DataSource dataSource = new DataSource(this);
        dataSource.deleteAll();
        showTravelNotesDeletedToast();
        finish();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog)
    {
        //Do nothing, Dialog will disappear
    }

    private void showTravelNotesDeletedToast()
    {
        Context context = getApplicationContext();
        String text = getString(R.string.travel_notes_deleted);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
