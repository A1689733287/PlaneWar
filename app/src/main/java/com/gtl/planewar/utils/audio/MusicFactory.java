package com.gtl.planewar.utils.audio;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by G on 2017/10/25.
 */

public class MusicFactory {
    public static Music createMusicFromFile(File file)
            throws IOException {
        MediaPlayer mediaPlayer = new MediaPlayer();

        mediaPlayer.setDataSource(new FileInputStream(file).getFD());
        mediaPlayer.prepare();

        Music music = new Music(mediaPlayer);

        return music;
    }

    public static Music createMusicFromAsset(Context context, String assetPath) throws IOException {
        MediaPlayer mediaPlayer = new MediaPlayer();
        AssetFileDescriptor assetFileDescritor = context.getAssets().openFd(assetPath);
        mediaPlayer.setDataSource(assetFileDescritor.getFileDescriptor(), assetFileDescritor.getStartOffset(), assetFileDescritor.getLength());
        mediaPlayer.prepare();
        Music music = new Music(mediaPlayer);
        return music;
    }

    public static Music createMusicFromResource(Context context, int mMusicResID) throws IOException {
        MediaPlayer mediaPlayer = MediaPlayer.create(context, mMusicResID);
        Music music = new Music(mediaPlayer);
        return music;
    }

    public static Music createMusicFromAssetFileD(AssetFileDescriptor assetFileDescriptor) throws IOException {
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
        mediaPlayer.prepare();
        Music music = new Music(mediaPlayer);
        return music;
    }
}
