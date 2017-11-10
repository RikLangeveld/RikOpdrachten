package com.school.langevr004.kungsleden.Database.activity;


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

import com.school.langevr004.kungsleden.Database.adapter.TravelNotesListItemAdapter;
import com.school.langevr004.kungsleden.Database.data.DataSource;
import com.school.langevr004.kungsleden.Database.model.TravelNotes;
import com.school.langevr004.kungsleden.Database.utility.ConfirmDeleteDialog;
import com.school.langevr004.kungsleden.R;

import java.util.Collections;
import java.util.List;

public class MainActivityTravelNotes extends AppCompatActivity
        implements ConfirmDeleteDialog.ConfirmDeleteDialogListener
{
    private RecyclerView travelNotesListView;
    private TravelNotesListItemAdapter mTravelNotesListItemAdapter;
    private List<TravelNotes> mTravelNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Set activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_travel_notes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Set de basis waarde van de RecyclerView
        travelNotesListView = (RecyclerView) findViewById(R.id.travelNotesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        travelNotesListView.setLayoutManager(mLayoutManager);
        travelNotesListView.setHasFixedSize(true);

        //Adding ItemAnimator voor de RecyclerView
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(100L);
        itemAnimator.setRemoveDuration(100L);
        travelNotesListView.setItemAnimator(itemAnimator);

        //Adding Gestures, dit maakt het mogelijk om items van de recyclerview te swipen
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP
                | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT)
        {
            //Deze methode is er om het object van plaats te kunnen laten wisselen met degene er boven of onder.
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

                Collections.swap(mTravelNotes, viewHolder.getAdapterPosition(), target.getAdapterPosition());
                mTravelNotesListItemAdapter.updateList(mTravelNotes);
                mTravelNotesListItemAdapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());

                DataSource dataSource = new DataSource(MainActivityTravelNotes.this);
                dataSource.deleteAll();
                for (TravelNotes travelNotes : mTravelNotes) {
                    dataSource.saveTravelNotes(travelNotes);
                }
                return true;
            }

            //Delete een item uit de Recycler view om objecten te verwijderen
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

                DataSource dataSource = new DataSource(MainActivityTravelNotes.this);

                dataSource.deleteTravelNotes(mTravelNotes.get(viewHolder.getAdapterPosition()).getId());

                mTravelNotes.remove(viewHolder.getAdapterPosition());

                mTravelNotesListItemAdapter.updateList(mTravelNotes);
                mTravelNotesListItemAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                mTravelNotesListItemAdapter.notifyItemRangeChanged(viewHolder.getAdapterPosition(), mTravelNotes.size());

                Context context = getApplicationContext();
                String text = String.format(getString(R.string.swipe_delete));
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        };

        //This is a utility class to add swipe to dismiss and drag & drop support to RecyclerView.
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(travelNotesListView);

        //Onclick ga naar de AddTravelNotesActivity om objecten toe te voegen
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityTravelNotes.this, AddTravelNotesActivity.class);
                startActivity(intent);
            }
        });
    }

    //Zet het menu voor de activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //onclick delete all the objects from the recyclerview
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_delete_all) {
            //ConfirmDeleteDialog box voor verwijderen
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

    //Update UI, get all the current travel notes
    private void updateUI() {
        DataSource dataSource = new DataSource(this);

        mTravelNotes = dataSource.getTravelNotes();
        if (mTravelNotesListItemAdapter == null) {
            mTravelNotesListItemAdapter = new TravelNotesListItemAdapter(mTravelNotes, this);
            travelNotesListView.setAdapter(mTravelNotesListItemAdapter);
        } else {
            mTravelNotesListItemAdapter.updateList(mTravelNotes);
            mTravelNotesListItemAdapter.notifyDataSetChanged();
        }
    }

    //Positive onclick confirmDeleteDialog Delete all travel notes
    @Override
    public void onDialogPositiveClick(DialogFragment dialog)
    {
        DataSource dataSource = new DataSource(this);
        dataSource.deleteAll();
        showTravelNotesDeletedToast();
        finish();
    }

    //negative click onclick confirmDeleteDialog
    @Override
    public void onDialogNegativeClick(DialogFragment dialog)
    {
        //Dialog venster verwijderd gebeurd niks
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
