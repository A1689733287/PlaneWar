package com.gtl.planewar.utils.audio;

/**
 * Created by G on 2017/10/25.
 */

public interface IMusic {
    void onStart();
    void onPause();
    void onStop();
    void onRelease();
    boolean isPlaying();
}
