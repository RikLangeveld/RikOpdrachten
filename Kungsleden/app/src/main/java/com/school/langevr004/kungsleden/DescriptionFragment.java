package com.school.langevr004.kungsleden;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

/*
* Deze class bezit een fragment met daarin een youtube video.
* TO DO: Op het moment dat je de video draait wordt de activity opnieuw gemaakt en in landscape mode gezet. Hiervoor
* moet ik een oplossing vinden. info: https://stackoverflow.com/questions/20383209/android-youtube-api-video-pause-when-rotate-screen-or-hide-player
*/
public class DescriptionFragment extends Fragment {

    private FragmentActivity myContext;

    private YouTubePlayer YPlayer;
    private static final String YOUTUBE_DEV_KEY = "AIzaSyDOf49OdQrEXznkzOweiNHir1mpHJRycmo";
    private static final String YOUTUBE_VIDEO = "Q7d0on8BBlE";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_description, container, false);

        YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.youtubeFragment, youTubePlayerFragment).commit();

        youTubePlayerFragment.initialize(YOUTUBE_DEV_KEY, new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider arg0, YouTubePlayer youTubePlayer, boolean b) {
                if (!b)
                {
                    YPlayer = youTubePlayer;
                    YPlayer.loadVideo(YOUTUBE_VIDEO);
                    YPlayer.setPlaybackEventListener(new MyPlaybackEventListener(YPlayer, getActivity()));
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {
                // TODO Auto-generated method stub

            }
        });
        return rootView;
    }
}
