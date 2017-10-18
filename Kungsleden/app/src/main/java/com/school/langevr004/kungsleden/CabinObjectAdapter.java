package com.school.langevr004.kungsleden;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

/**
 * Created by langevr004 on 17-10-2017.
 */

public class CabinObjectAdapter extends RecyclerView.Adapter<CabinObjectViewHolder>
{
    private Context context;
    public List<GeoObject> listGeoObject;
    public CabinObjectAdapter(Context context, List<GeoObject> listGeoObject) {
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
        final GeoObject geoObject = listGeoObject.get(position);
        // The holder argument is used to reference the views inside the viewHolder
        // Populate the views with the data from the list
        holder.geoImage.setImageResource(geoObject.getmGeoImageName());
        holder.geoName.setText(geoObject.getmGeoName());
        // The whole layout is used for the onClickListener instead of individual views
        // inside the viewHolder
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, geoObject.getmGeoName() + " is selected",    Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listGeoObject.size();
    }

}
