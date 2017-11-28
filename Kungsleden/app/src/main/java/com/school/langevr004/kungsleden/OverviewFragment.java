package com.school.langevr004.kungsleden;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class OverviewFragment extends Fragment {

    View rootView;

    //List<Button> buttons = new ArrayList<>();
    //List<LinearLayout> linearLayouts = new ArrayList<>();
    //List<FrameLayout> fragments = new ArrayList<>();

    List<OverviewButton> overviewButtons = new ArrayList<>();

    public OverviewFragment() {

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_overview, container, false);

        overviewButtons.add(
                new OverviewButton(
                        (LinearLayout) rootView.findViewById(R.id.description_layout),
                        (Button) rootView.findViewById(R.id.description_btn),
                        null));

        overviewButtons.add(
                new OverviewButton(
                    (LinearLayout) rootView.findViewById(R.id.the_challange_layout),
                    (Button) rootView.findViewById(R.id.the_challange_btn),
                     null));

        overviewButtons.add(
                new OverviewButton(
                        (LinearLayout) rootView.findViewById(R.id.accommodations_layout),
                        (Button) rootView.findViewById(R.id.accommodations_btn),
                        null));

        overviewButtons.add(
                new OverviewButton(
                        (LinearLayout) rootView.findViewById(R.id.photo_gallery_layout),
                        (Button) rootView.findViewById(R.id.photo_gallery_btn),
                        null));

        overviewButtons.add(
                new OverviewButton(
                        (LinearLayout) rootView.findViewById(R.id.public_transport_layout),
                        (Button) rootView.findViewById(R.id.public_transport_btn),
                        null));

        for (OverviewButton item : overviewButtons)
        {
            item.button.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_more, 0);
            createButtonOnclickEvent(item.linearLayout, item.button, item.frameLayout);
        }

        return rootView;
    }

    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

    }

    private void createButtonOnclickEvent(final LinearLayout linearLayout, final Button button, final FrameLayout frameLayout)
    {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (linearLayout.getVisibility() == View.VISIBLE)
                {
                    button.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_more, 0);
                    linearLayout.setVisibility(View.GONE);

                }
                else
                {
                    //TO DO: Repair animation thats makes sure the content is going in smootly
                    /*
                    ScaleAnimation animation = new ScaleAnimation(1f, 1f, 1f, 0f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f);
                    animation.setDuration(180);
                    animation.setFillAfter(true);
                    findMagicLl.startAnimation(animation);
                    */

                    button.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_less, 0);
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
