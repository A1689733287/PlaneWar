package com.gtl.planewar.utils;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by G on 2017/6/13.
 */

public class BGM {
    public static MediaPlayer mediaPlayer;

    public BGM(Context context, int source) {
        mediaPlayer = MediaPlayer.create(context, source);
    }

    //播放bgm
    public void start() {
        try {
            mediaPlayer.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    //停止bgm
    public final void stop() {
        try {
            mediaPlayer.stop();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public void paush() {
        mediaPlayer.pause();
    }

    //设置循环播放
    public final void setLooping() {
        mediaPlayer.isLooping();
    }
}
