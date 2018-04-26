package com.gtl.planewar.model.impl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.gtl.planewar.model.BaseLogic;
import com.gtl.planewar.utils.GetDM;

/**
 * Created by G on 2017/5/9.
 */

public class BulletAdd implements BaseLogic {
    Bitmap bulletAdd;
    int x;
    int y;
    int ySpeed = 4;
    int xSpeed = 6;
    int screenWidth;
    int screenHeight;
    public boolean isDead = false;

    public BulletAdd(Bitmap bulletAdd, int x, int y) {
        this.bulletAdd = bulletAdd;
        this.x = x;
        this.y = y;
        init();
    }

    @Override
    public void init() {
        screenHeight = GetDM.screenH;
        screenWidth = GetDM.screenW;
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
        canvas.drawBitmap(bulletAdd, x, y, paint);
        canvas.restore();
    }

    public void logic() {
        y += ySpeed;
        x += xSpeed;
        if (x + bulletAdd.getWidth() >= screenWidth) {
            xSpeed = -4;
        }
        if (x <= 0) {
            xSpeed = 8;
        }
        if (y >= screenHeight) {
            isDead = true;
        }
    }
}
