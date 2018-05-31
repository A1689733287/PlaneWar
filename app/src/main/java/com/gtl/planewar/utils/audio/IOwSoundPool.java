package com.gtl.planewar.utils.audio;

/**
 * Created by G on 2017/10/24.
 */

public interface IOwSoundPool {
    void loadSound(int i, int resId);
    void onPlay(int i, int loop);
    void onPause(int resId);
    void onRelease();
}
