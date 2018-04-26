package com.gtl.planewar.model.impl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.gtl.planewar.model.BaseLogic;
import com.gtl.planewar.utils.GetDM;

/**
 * Created by G on 2017/4/15.
 */

public class GameBg implements BaseLogic {
    private Bitmap bg1, bg2;
    private int bg1X, bg2X, bg1Y, bg2Y;
    private int screenWidth, screenHeight;
    private int speed = 3;

    public GameBg(Bitmap bg) {
        this.bg1 = bg;
        this.bg2 = bg;
        init();
    }

    @Override
    public void init() {
        screenHeight = GetDM.screenH;
        screenWidth = GetDM.screenW;
        bg1Y = -Math.abs(bg1.getHeight() - screenHeight);
        bg2Y = bg1Y - bg2.getHeight();
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(bg1, bg1X, bg1Y, paint);
        canvas.drawBitmap(bg2, bg2X, bg2Y, paint);
    }

    /**
     * 背景逻辑
     */
    public void logic() {
        bg1Y += speed;
        bg2Y += speed;
        if (bg1Y > screenHeight) {
            bg1Y = bg2Y - bg1.getHeight();
        } else if (bg2Y > screenHeight) {
            bg2Y = bg1Y - bg1.getHeight();
        }
    }
}
