package com.school.langevr004.kungsleden;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;

public class PhotosFragment extends Fragment {

    private View view;
    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;

    private ViewPagerAdapter sliderAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_photos, container, false);

        mSlideViewPager = (ViewPager) view.findViewById(R.id.ViewPager);

        mDotLayout = (LinearLayout) view.findViewById(R.id.dotsLayout);

        sliderAdapter = new ViewPagerAdapter(getContext());

        mSlideViewPager.setAdapter(sliderAdapter);

        //Timer timer = new Timer();
        //timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);

        return view;
    }

    public class  MyTimerTask extends TimerTask
    {

        @Override
        public void run()
        {
            if (mSlideViewPager.getCurrentItem() == 0)
            {
                mSlideViewPager.setCurrentItem(1);
            }
            else if (mSlideViewPager.getCurrentItem() == 1)
            {
                mSlideViewPager.setCurrentItem(2);
            }
            else
            {
                mSlideViewPager.setCurrentItem(0);
            }
        }
    }
}

