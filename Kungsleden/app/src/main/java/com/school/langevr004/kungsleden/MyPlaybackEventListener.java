package com.school.langevr004.kungsleden;

import com.google.android.youtube.player.YouTubePlayer;

/**
 * Created by Rik on 30-11-2017.
 * Deze class implements de playbackEventListener en in de onPlay() wordt de youtube video op pauze gezet
 */

public class MyPlaybackEventListener implements YouTubePlayer.PlaybackEventListener
{
    private YouTubePlayer YPlayer;
    private boolean interceptPlay = true;

    public MyPlaybackEventListener(YouTubePlayer Yplayer)
    {
    this.YPlayer = Yplayer;
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
