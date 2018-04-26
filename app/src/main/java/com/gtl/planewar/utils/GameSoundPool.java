package com.gtl.planewar.utils;

import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

public class GameSoundPool {
    Activity activity;
    private SoundPool soundPool;
    private HashMap<Integer, Integer> map;
    private float stramMaxVolumeCurrent;
    private float stramVolumeCurrent;
    public static float volume;

    public GameSoundPool(Activity Activity) {
        this.activity = Activity;
        map = new HashMap<Integer, Integer>();
        AudioManager am = (AudioManager) activity.getSystemService(Context.AUDIO_SERVICE);
        stramVolumeCurrent = am.getStreamVolume(AudioManager.STREAM_MUSIC);
        stramMaxVolumeCurrent = am.getStreamVolume(AudioManager.STREAM_MUSIC);
        volume = stramVolumeCurrent / stramMaxVolumeCurrent;
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
    }

    public void initGameSound() {
        map.put(1, soundPool.load(activity, GameGetResource.boom, 1));
        map.put(2, soundPool.load(activity, GameGetResource.goods, 1));
        map.put(3, soundPool.load(activity, GameGetResource.shoot, 1));
    }

    public void playSound(int sound, int loop) {
        soundPool.play(map.get(sound), volume, volume, 1, loop, 1.0f);
    }

    public float getStramMaxVolumeCurrent() {
        return stramMaxVolumeCurrent;
    }

    public void setStramMaxVolumeCurrent(float stramMaxVolumeCurrent) {
        this.stramMaxVolumeCurrent = stramMaxVolumeCurrent;
    }

    public float getStramVolumeCurrent() {
        return stramVolumeCurrent;
    }

    public void setStramVolumeCurrent(float stramVolumeCurrent) {
        this.stramVolumeCurrent = stramVolumeCurrent;
    }
}
