package com.gtl.planewar.model.impl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.gtl.planewar.model.BaseLogic;

/**
 * Created by G on 2017/4/20.
 */

public class Boom implements BaseLogic {
    int boomX, boomY;
    public boolean isCollision;
    Bitmap boom;
    int boomW, boomH;
    //爆炸动画播放当前的帧下标
    private int cureentFrameIndex;

    public Boom(Bitmap boom, int x, int y) {
        this.boom = boom;
        this.boomX = x;
        boomW = boom.getWidth() / 6;
        boomH = boom.getHeight();
        this.boomY = y;
    }

    @Override
    public void init() {

    }

    public void draw(final Canvas canvas, final Paint paint) {
        canvas.save();
        canvas.clipRect(boomX, boomY, boomX + boomW, boomY + boomH);
        canvas.drawBitmap(boom, boomX - cureentFrameIndex * boomW, boomY, paint);
        canvas.restore();
    }

    public void logic() {
        if (cureentFrameIndex < 6) {
            cureentFrameIndex++;
        } else {
            isCollision = true;
        }
    }
}
