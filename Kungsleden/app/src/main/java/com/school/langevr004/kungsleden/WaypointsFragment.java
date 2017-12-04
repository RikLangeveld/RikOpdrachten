package com.school.langevr004.kungsleden;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;


public class WaypointsFragment extends AccordionView
{
    public WaypointsFragment(int waypointsFragment)
    {
        super.layoutFile = waypointsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.rootView = inflater.inflate(layoutFile, container, false);

        categoriesButtons.add(
                new OverviewButton(
                        (LinearLayout) rootView.findViewById(R.id.shelter_layout),
                        (Button) rootView.findViewById(R.id.shelter_btn),
                        R.id.shelter_frame,
                        null));

        categoriesButtons.add(
                new OverviewButton(
                        (LinearLayout) rootView.findViewById(R.id.supplies_layout),
                        (Button) rootView.findViewById(R.id.supplies_btn),
                        R.id.supplies_frame,
                        null));

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
