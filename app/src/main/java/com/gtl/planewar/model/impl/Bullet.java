package com.gtl.planewar.model.impl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.gtl.planewar.model.BaseLogic;
import com.gtl.planewar.utils.Config;
import com.gtl.planewar.utils.GetDM;

/**
 * Created by G on 2017/4/15.;
 */

public class Bullet implements BaseLogic {
    public Bitmap pBullet;
    public int bulletX, bulletY, playerY;
    int speed = 20;
    int bulletType;
    int screenHeight;
    int screenWidth;
    int dir;
    public boolean isDead;

    public Bullet(Bitmap bullet, int bulletType, int dir, int bulletX, int bulletY) {
        this.pBullet = bullet;
        this.bulletX = bulletX;
        this.bulletY = bulletY;
        this.bulletType = bulletType;
        this.dir = dir;
        init();
    }

    @Override
    public void init() {
        screenHeight = GetDM.screenH;
        screenWidth = GetDM.screenW;
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
        canvas.drawBitmap(pBullet, bulletX, bulletY, paint);
        canvas.restore();
    }

    public void logic() {
        switch (bulletType) {
            case Config.BULLET_CENTER:
                switch (dir) {
                    case Config.DIR_UP:
                        speed = 20;
                        bulletY -= speed;
                        if (bulletY <= 0) {
                            isDead = true;
                        }
                        break;
                }
                break;
            case Config.BULLET_SIDE:
                switch (dir) {
                    case Config.DIR_CENTER_DOWN:
                        bulletY += 8;
                        if (bulletY > screenHeight || bulletY <= 0) {
                            isDead = true;
                        }
                        break;
                    case Config.DIR_RIGHT_RDOWN:
                        bulletY += 7;
                        bulletX += 3;
                        if (bulletY > screenHeight || bulletY < 0 || bulletX > screenWidth) {
                            isDead = true;
                        }
                        break;
                    case Config.DIR_LEFT_LDOWN:
                        bulletY += 7;
                        bulletX -= 3;
                        if (bulletY > screenHeight || bulletY < 0 || bulletX > screenWidth) {
                            isDead = true;
                        }
                        break;
                }
                break;

            case Config.BOSS:
                switch (dir) {
                    case Config.DIR_CENTER_DOWN:
                        bulletY += 8;
                        if (bulletY > screenHeight || bulletY < 0) {
                            isDead = true;
                        }
                        break;
                    case Config.DIR_RIGHT_RDOWN:
                        bulletY += 7;
                        bulletX += 3;
                        if (bulletY > screenHeight || bulletY < 0 || bulletX > screenWidth) {
                            isDead = true;
                        }
                        break;
                    case Config.DIR_LEFT_LDOWN:
                        bulletY += 7;
                        bulletX -= 3;
                        if (bulletY > screenHeight || bulletY < 0 || bulletX < -pBullet.getWidth()) {
                            isDead = true;
                        }
                        break;
                    case 4:
                        bulletY += 6;
                        bulletX += 6;
                        if (bulletY > screenHeight || bulletY < 0 || bulletX > screenWidth) {
                            isDead = true;
                        }
                        break;
                    case 5:
                        bulletY += 6;
                        bulletX -= 6;
                        if (bulletY > screenHeight || bulletY < 0 || bulletX < -pBullet.getWidth()) {
                            isDead = true;
                        }
                        break;
                }
                break;
        }
    }
}
