package com.school.langevr004.kungsleden;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class PhotosFragment extends Fragment {

    private View view;
    private ViewPager mSlideViewPager;

    private ViewPagerAdapter sliderAdapter;

    private LinearLayout dotsLayout;
    private int dotsCount;
    private ImageView[] dots;

    private int[] imagesCabins;

    public PhotosFragment(int[] images)
    {
        this.imagesCabins = images;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_photos, container, false);
        mSlideViewPager = (ViewPager) view.findViewById(R.id.ViewPager);

        //Dots for the slider.
        dotsLayout = (LinearLayout) view.findViewById(R.id.dotsLayout);

        /*Verplaatst naar constructor*/
        sliderAdapter = new ViewPagerAdapter(getContext(), imagesCabins);

        mSlideViewPager.setAdapter(sliderAdapter);

        dotsCount = sliderAdapter.getCount();
        dots = new ImageView[dotsCount];

        for(int i = 0; i < dotsCount; i ++)
        {
            dots[i] = new ImageView(getContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getContext().getApplicationContext(), R.drawable.nonactive_dot ));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            dotsLayout.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getContext().getApplicationContext(), R.drawable.active_dot));

        mSlideViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position)
            {
                for(int i = 0; i < dotsCount; i++)
                {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getContext().getApplicationContext(), R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getContext().getApplicationContext(),R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return view;
    }

}

