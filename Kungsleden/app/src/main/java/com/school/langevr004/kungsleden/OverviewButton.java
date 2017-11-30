package com.school.langevr004.kungsleden;


import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class OverviewButton
{
    public LinearLayout linearLayout;
    public Button button;
    public int frameLayout;
    public Fragment fragment;

    public OverviewButton(LinearLayout linearLayout, Button button, int frameLayout, Fragment fragment)
    {
        this.linearLayout = linearLayout;
        this.button = button;
        this.frameLayout = frameLayout;
        this.fragment = fragment;
    }
}
