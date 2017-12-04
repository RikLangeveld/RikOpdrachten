package com.school.langevr004.kungsleden;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rik on 4-12-2017.
 *
 * Super class voor alle Fragments die een accordion view gebruiken.
 *
 * Alle Buttons van de layout file moeten toegevoed worden in de child class, aan de categoriesButtons lijst in de onCreate.
 *
 * Note: kijk in de overviewFragment class hoe de Oncreate methode er uit dient te zien.
 *
 */

public class AccordionView extends Fragment
{

    int layoutFile;
    View rootView;
    List<OverviewButton> categoriesButtons = new ArrayList<>();

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        for (OverviewButton item : categoriesButtons)
        {
            item.button.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_more, 0);
            createButtonOnclickEvent(item.linearLayout, item.button, item.frameLayout, item.fragment);
        }

        return rootView;
    }

    private void createButtonOnclickEvent(final LinearLayout linearLayout, final Button button, final int frameLayout, final Fragment fragment)
    {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();

                if (linearLayout.getVisibility() == View.VISIBLE)
                {
                    if(frameLayout != 0 && fragment != null)
                    {
                        fragmentTransaction.remove(fragment);
                        fragmentTransaction.commit();
                    }

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
                    if(frameLayout != 0 && fragment != null)
                    {
                        fragmentTransaction.replace(frameLayout, fragment);
                        fragmentTransaction.commit();
                    }

                    button.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_less, 0);
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

    }
}
