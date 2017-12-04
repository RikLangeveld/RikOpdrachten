package com.school.langevr004.kungsleden;

import android.app.Activity;
import android.content.pm.ActivityInfo;

import com.google.android.youtube.player.YouTubePlayer;

/**
 * Created by Rik on 30-11-2017.
 * Deze class implements de playbackEventListener en in de onPlay() wordt de youtube video op pauze gezet
 */

public class MyPlaybackEventListener implements YouTubePlayer.PlaybackEventListener
{

    private YouTubePlayer YPlayer;
    private Activity activity;
    private boolean interceptPlay = true;

    public MyPlaybackEventListener(YouTubePlayer Yplayer, Activity activity)
    {
        this.YPlayer = Yplayer;
        this.activity = activity;
    }

    @Override
    public void onPlaying()
    {
        if (interceptPlay) {
            YPlayer.pause();
            interceptPlay = false;
        }
    }

    @Override
    public void onPaused()
    {
    }

    @Override
    public void onStopped()
    {
        //activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public void onBuffering(boolean b)
    {
    }

    @Override
    public void onSeekTo(int i)
    {
    }
}
