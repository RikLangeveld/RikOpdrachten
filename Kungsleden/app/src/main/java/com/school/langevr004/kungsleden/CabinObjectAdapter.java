package com.school.langevr004.kungsleden;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

/**
 * Adapter to fill the recyclerview
 */

public class CabinObjectAdapter extends RecyclerView.Adapter<CabinObjectViewHolder>
{
    @Override
    public CabinObjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CabinObjectViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    /*
    private Context context;
    public List<TrailInformation> listGeoObject;


    public CabinObjectAdapter(Context context, List<TrailInformation> listGeoObject) {
        this.context = context;
        this.listGeoObject = listGeoObject;
    }

    @Override
    public CabinObjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_cel, parent, false);
        return new CabinObjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CabinObjectViewHolder holder, final int position) {
        // Gets a single item in the list from its position
        final TrailInformation geoObject = listGeoObject.get(position);

        // The holder argument is used to reference the views inside the viewHolder
        // Populate the views with the data from the list
        holder.geoImage.setImageResource(geoObject.getmGeoImageName());
        holder.geoName.setText(geoObject.getmGeoName());
        holder.Coördinates.setText(geoObject.getmCoördinatesString());

        // The whole layout is used for the onClickListener instead of individual views
        // inside the viewHolder
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, geoObject.getmGeoName() + " is selected",    Toast.LENGTH_SHORT).show();

                //Create an Intent and specify which activity needs to be started.
                Intent intent = new Intent(v.getContext(), cabinInfo.class);
                intent.putExtra("cabinNumber", (byte) position);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listGeoObject.size();
    }

*/
}
