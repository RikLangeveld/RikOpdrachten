package com.school.langevr004.kungsleden.Database.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.school.langevr004.kungsleden.Database.activity.TravelNotesDetailsActivity;
import com.school.langevr004.kungsleden.Database.model.TravelNotes;
import com.school.langevr004.kungsleden.R;

import java.util.List;

public class TravelNotesListItemAdapter extends RecyclerView.Adapter<TravelNotesListItemAdapter.ViewHolder> {
    final Context context;
    private final List<TravelNotes> travelNotesArrayList;

    public TravelNotesListItemAdapter(List<TravelNotes> list, Context context) {
        travelNotesArrayList = list;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return travelNotesArrayList.size();
    }
    private TravelNotes getItem(int position) {
        return travelNotesArrayList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return travelNotesArrayList.get(position).getId();
    }
    public void updateList(List<TravelNotes> newlist) {
        // Set new updated list
        travelNotesArrayList.clear();
        travelNotesArrayList.addAll(newlist);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_travel_notes_item, parent, false);
        return new ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.populateRow(getItem(position));
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView title;
        private final TextView trail;
        private final TextView date;

        //initialize the variables
        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.travelNotesTitle);
            trail = (TextView) view.findViewById(R.id.travelNotesTrail);
            date = (TextView) view.findViewById(R.id.travelNotesDate);
            view.setOnClickListener(this);
        }

        //onclick event om naar TravelNotesDetailsActivity van de huidige travel note te gaan.
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, TravelNotesDetailsActivity.class);
            TravelNotes selectedTravelNotes = getItem(getAdapterPosition());
            intent.putExtra("selectedTravelNotes", selectedTravelNotes);
            context.startActivity(intent);
        }

        public void populateRow(TravelNotes travelNotes) {
            title.setText(travelNotes.getTitle());
            trail.setText(travelNotes.getTravelNotesTrail());
            date.setText(travelNotes.getDateAdded());
        }
    }
}
