package com.school.langevr004.kungsleden;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class OverviewFragment extends Fragment {

    View rootView;

    List<Button> buttons = new ArrayList<Button>();
    List<LinearLayout> linearLayouts = new ArrayList<LinearLayout>();

    public OverviewFragment() {

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_overview, container, false);

        //NOTE: the first of linearLayouts needs to be for the first button. Make sure the length is the same!
        linearLayouts.add((LinearLayout) rootView.findViewById(R.id.description_layout));
        linearLayouts.add((LinearLayout) rootView.findViewById(R.id.the_challange_layout));
        linearLayouts.add((LinearLayout) rootView.findViewById(R.id.accommodations_layout));
        linearLayouts.add((LinearLayout) rootView.findViewById(R.id.photo_gallery_layout));
        linearLayouts.add((LinearLayout) rootView.findViewById(R.id.public_transport_layout));

        buttons.add((Button) rootView.findViewById(R.id.description_btn));
        buttons.add((Button) rootView.findViewById(R.id.the_challange_btn));
        buttons.add((Button) rootView.findViewById(R.id.accommodations_btn));
        buttons.add((Button) rootView.findViewById(R.id.photo_gallery_btn));
        buttons.add((Button) rootView.findViewById(R.id.public_transport_btn));

        for (int i = 0; i < buttons.size(); i++)
        {
            Button btn = buttons.get(i);
            LinearLayout linearLayout = linearLayouts.get(i);

            btn.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_more, 0);
            CreateButtonOnclickEvent(btn, linearLayout);
        }

        return rootView;
    }

    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

    }

    private void CreateButtonOnclickEvent(final Button button, final LinearLayout content) {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (content.getVisibility() == View.VISIBLE)
                {
                    button.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_more, 0);
                    content.setVisibility(View.GONE);
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
                    content.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
