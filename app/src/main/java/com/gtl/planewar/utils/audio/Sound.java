package com.gtl.planewar.utils.audio;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.HashMap;

/**
 * 声音类
 */

public class Sound implements IOwSoundPool {
    private SoundPool soundPool;
    private Context context;
    private HashMap<Integer, Integer> map;
    private float streamMaxVolumeCurrent;
    private float streamVolumeCurrent;
    public float volume;
    private float leftVolume;
    private float rightVolume;
    private AudioManager audioManager;

    /**
     * 初始化声音类
     *
     * @param context 上下文环境
     */
    public Sound(Context context) {
        this.context = context;
        map = new HashMap<>();
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        streamVolumeCurrent = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        streamMaxVolumeCurrent = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume = streamVolumeCurrent / streamMaxVolumeCurrent;
        leftVolume = volume;
        rightVolume = volume;
        soundPool = new SoundPool(8, AudioManager.STREAM_MUSIC, 0);
    }

    /**
     * 加载声音文件
     *
     * @param i     文件序号
     * @param resId 资源id
     */
    public void loadSound(int i, int resId) {
        map.put(i, soundPool.load(context, resId, 1));
    }

    /**
     * 播放声音文件
     *
     * @param i    要播放声音文件的序号
     * @param loop 是否循环播放0为不循环，-1为循环
     */
    @Override
    public void onPlay(int i, int loop) {
        soundPool.play(i, leftVolume, rightVolume, 1, loop, 1);
    }

    @Override
    public void onPause(int resId) {
        soundPool.pause(resId);
    }

    /**
     * 释放soundPool
     */
    @Override
    public void onRelease() {
        soundPool.release();

    }


    public float getStreamMaxVolumeCurrent() {
        return streamMaxVolumeCurrent;
    }

    public void setStreamMaxVolumeCurrent(float streamMaxVolumeCurrent) {
        this.streamMaxVolumeCurrent = streamMaxVolumeCurrent;
    }

    public float getStreamVolumeCurrent() {
        return streamVolumeCurrent;
    }

    public void setStreamVolumeCurrent(float streamVolumeCurrent) {
        this.streamVolumeCurrent = streamVolumeCurrent;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public float getLeftVolume() {
        return leftVolume;
    }

    public void setLeftVolume(float leftVolume) {
        this.leftVolume = leftVolume;
    }

    public float getRightVolume() {
        return rightVolume;
    }

    public void setRightVolume(float rightVolume) {
        this.rightVolume = rightVolume;
    }
}
