package com.school.langevr004.kungsleden;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class OverviewFragment extends AccordionView {

    public OverviewFragment(int layoutFile)
    {
        super.layoutFile = layoutFile;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(layoutFile, container, false);

        categoriesButtons.add(
                new OverviewButton(
                        (LinearLayout) rootView.findViewById(R.id.description_layout),
                        (Button) rootView.findViewById(R.id.description_btn),
                        R.id.description_frame,
                        new DescriptionFragment()));

        categoriesButtons.add(
                new OverviewButton(
                        (LinearLayout) rootView.findViewById(R.id.the_challange_layout),
                        (Button) rootView.findViewById(R.id.the_challange_btn),
                        R.id.the_challange_frame,
                        new TextFragment
                                ("The route follows tracks and footpaths throughout, " +
                                "but after leaving Cannich it becomes increasingly remote; " +
                                "the upper reaches of Glen Affric being amongst the most isolated in the Highlands. " +
                                "Some experience of remote walking, navigational skills and full hillwalking gear are needed. ")));

        categoriesButtons.add(
                new OverviewButton(
                        (LinearLayout) rootView.findViewById(R.id.accommodations_layout),
                        (Button) rootView.findViewById(R.id.accommodations_btn),
                        R.id.accommodations_frame,
                        new TextFragment
                                ("Drumnadrochit on Loch Ness has shops and a wide choice of accommodation, " +
                                "including B&Bs, guesthouses, hotels and hostels. Along the first stage there is a bed and breakfast at Shenval. \n \n" +
                                "Cannich again has a shop, bed and breakfast and a campsite. \n \n" +
                                "After Cannich there are no further facilities along the route," +
                                "except for the very remote SYHA hostel at Alltbeithe; this is open during the summer only, should be pre-booked and does not provide food.\n\n" +
                                "Morvich and nearby Allt a'Chruinn in Kintail have bed and breakfast; " +
                                "there is also the nearby hotel at Shiel Bridge, which additionally has hostel accommodation.")));

        categoriesButtons.add(
                new OverviewButton(
                        (LinearLayout) rootView.findViewById(R.id.photo_gallery_layout),
                        (Button) rootView.findViewById(R.id.photo_gallery_btn),
                        R.id.photo_gallery_frame,
                        new PhotosFragment(TrailInformation.BEST_IMAGES)));

        categoriesButtons.add(
                new OverviewButton(
                        (LinearLayout) rootView.findViewById(R.id.public_transport_layout),
                        (Button) rootView.findViewById(R.id.public_transport_btn),
                        R.id.public_transport_frame,
                        new TextFragment("Drumnadrochit has a good bus link, being on the route between Inverness and Fort William (both of which have rail stations). Cannich is also served by bus from Inverness.\n" +
                                "\n" +
                                "The end of the road up Glen Affric (at end of stage 2) has a summer service from Ross minibuses.\n" +
                                "\n" +
                                "Morvich at the end of the route is near to the main Citylink bus route between Inverness and Skye, and Glasgow and Skye.\n" +
                                "\n" +
                                "Timetables for all the routes can be found on Traveline Scotland.")));

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
