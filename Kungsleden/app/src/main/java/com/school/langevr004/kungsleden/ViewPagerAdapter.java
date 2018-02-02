package com.school.langevr004.kungsleden;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Rik on 24-1-2018.
 */

public class ViewPagerAdapter extends PagerAdapter
{
    private Context context;
    private LayoutInflater layoutInflater;
    private int[] images; //De images die getoont moeten worden in de viewpager.

    /*
    Constructor heeft 2 paramaters. 1 voor de context van de applicatie, en een tweede voor de Afbeeldingen die op het scherm moeten verschijnen.
     */
    public ViewPagerAdapter(Context context, int[] images)
    {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_picture_layout, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.slide_image);
        imageView.setImageResource(images[position]);

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        ViewPager vp = (ViewPager)container;
        View view = (View) object;
        vp.removeView(view);
    }
}
