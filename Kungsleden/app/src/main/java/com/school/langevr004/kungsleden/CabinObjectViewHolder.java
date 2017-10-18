package com.school.langevr004.kungsleden;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by langevr004 on 17-10-2017.
 */

public class CabinObjectViewHolder extends RecyclerView.ViewHolder
{
    public TextView geoName;
    public ImageView geoImage;
    public View view;

    public CabinObjectViewHolder(View itemView)
    {
        super(itemView);
        geoName = (TextView)itemView.findViewById(R.id.cabinTextView);
        geoImage = (ImageView) itemView.findViewById(R.id.cabinImageView);
        view = itemView;
    }
}
