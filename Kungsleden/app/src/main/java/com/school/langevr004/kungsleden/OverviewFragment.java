package com.school.langevr004.kungsleden;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class OverviewFragment extends AccordionView {

    public OverviewFragment(int layoutFile)
    {
        super.layoutFile = layoutFile;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(layoutFile, container, false);

        categoriesButtons.add(
                new OverviewButton(
                        (LinearLayout) rootView.findViewById(R.id.description_layout),
                        (Button) rootView.findViewById(R.id.description_btn),
                        R.id.description_frame,
                        new DescriptionFragment()));

        categoriesButtons.add(
                new OverviewButton(
                        (LinearLayout) rootView.findViewById(R.id.the_challange_layout),
                        (Button) rootView.findViewById(R.id.the_challange_btn),
                        R.id.the_challange_frame,
                        null));

        categoriesButtons.add(
                new OverviewButton(
                        (LinearLayout) rootView.findViewById(R.id.accommodations_layout),
                        (Button) rootView.findViewById(R.id.accommodations_btn),
                        R.id.accommodations_frame,
                        null));

        categoriesButtons.add(
                new OverviewButton(
                        (LinearLayout) rootView.findViewById(R.id.photo_gallery_layout),
                        (Button) rootView.findViewById(R.id.photo_gallery_btn),
                        R.id.photo_gallery_frame,
                        new PhotosFragment(GeoObject.PRE_DEFINED_CABINS[0].imagesCabin)));

        categoriesButtons.add(
                new OverviewButton(
                        (LinearLayout) rootView.findViewById(R.id.public_transport_layout),
                        (Button) rootView.findViewById(R.id.public_transport_btn),
                        R.id.public_transport_frame,
                        null));

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
