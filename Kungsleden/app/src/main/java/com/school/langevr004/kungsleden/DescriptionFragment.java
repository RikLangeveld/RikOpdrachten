package com.school.langevr004.kungsleden;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;

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
                    YPlayer.setPlaybackEventListener(new MyPlaybackEventListener(YPlayer));
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
