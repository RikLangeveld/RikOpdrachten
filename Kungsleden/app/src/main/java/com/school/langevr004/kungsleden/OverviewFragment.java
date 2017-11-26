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

public class OverviewFragment extends Fragment {

    View rootView;


    public OverviewFragment() {

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_overview, container, false);
        final Button findDescriptionBtn = (Button) rootView.findViewById(R.id.description_btn);
        findDescriptionBtn.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_more, 0);
        findDescriptionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout findMagicLl = (LinearLayout) rootView.findViewById(R.id.description_layout);
                if (findMagicLl.getVisibility() == View.VISIBLE) {
                    findDescriptionBtn.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_more, 0);
                    findMagicLl.setVisibility(View.GONE);
                } else {

                    //TO DO: zorg dat de nieuwe view netjes het scherm binnenkomt geanimeert
                    /*
                    ScaleAnimation animation = new ScaleAnimation(1f, 1f, 1f, 0f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f);
                    animation.setDuration(180);
                    animation.setFillAfter(true);
                    findMagicLl.startAnimation(animation);
                    */
                    findDescriptionBtn.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_less, 0);
                    findMagicLl.setVisibility(View.VISIBLE);
                }
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

    }

    private void CreateButtonOnclickEvent()
    {
        
    }

}
