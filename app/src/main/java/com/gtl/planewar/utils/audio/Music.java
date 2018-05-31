package com.gtl.planewar.utils.audio;

import android.media.MediaPlayer;

/**
 * Created by G on 2017/10/25.
 */

public class Music implements IMusic {
    MediaPlayer mediaPlayer;


    public Music(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;

    }

    @Override
    public void onStart() {
        mediaPlayer.start();
    }

    @Override
    public void onPause() {
        mediaPlayer.pause();
    }

    @Override
    public void onStop() {
        mediaPlayer.stop();
    }

    @Override
    public void onRelease() {
        mediaPlayer.release();
    }

    @Override
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }
}
