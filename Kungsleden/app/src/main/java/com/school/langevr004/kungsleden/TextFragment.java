package com.school.langevr004.kungsleden;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TextFragment extends Fragment {

    String mainText;

    public TextFragment(String mainText)
    {
        this.mainText = mainText;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_text, container, false);
        TextView textFragment = (TextView) view.findViewById(R.id.main_text);
        textFragment.setText(mainText);
        // Inflate the layout for this fragment
        return view;
    }
}
