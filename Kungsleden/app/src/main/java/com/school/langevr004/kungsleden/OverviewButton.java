package com.school.langevr004.kungsleden;


import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class OverviewButton
{
    public LinearLayout linearLayout;
    public Button button;
    public FrameLayout frameLayout;

    public OverviewButton(LinearLayout linearLayout, Button button, FrameLayout frameLayout)
    {
        this.linearLayout = linearLayout;
        this.button = button;
        this.frameLayout = frameLayout;
    }
}
