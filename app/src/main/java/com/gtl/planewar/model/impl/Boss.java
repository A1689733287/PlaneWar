package com.gtl.planewar.model.impl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.gtl.planewar.model.BaseLogic;
import com.gtl.planewar.utils.GetDM;

/**
 * Created by G on 2017/5/7.
 */

public class Boss implements BaseLogic {
    int screenHeight;
    int screenWidth;
    Bitmap litBoss;
    public int litBossX;
    public int litBossY;
    int ySpeed = 4;
    int xSpeeed = 4;
    private int boosHp = 1000;
    boolean isDie = false;

    public Boss(Bitmap litBoss, int boosX) {
        this.litBoss = litBoss;
        litBossX = boosX;
        litBossY = -litBoss.getHeight() - 20;
        init();
    }

    @Override
    public void init() {
        screenHeight = GetDM.screenH;
        screenWidth = GetDM.screenW;
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
        canvas.drawBitmap(litBoss, litBossX, litBossY, paint);
        canvas.restore();
    }

    public void logic() {
        if (litBossY + litBoss.getHeight() >= screenHeight / 4) {
            ySpeed = 0;
            litBossX += xSpeeed;
            if (litBossX + litBoss.getWidth() >= screenWidth) {
                xSpeeed = -4;
            }
            if (litBossX <= 0) {
                xSpeeed = 4;
            }
        }
        litBossY += ySpeed;
    }

    public boolean collision(Bullet bullet) {
        int x = bullet.bulletX;
        int y = bullet.bulletY;
        int w = x + bullet.pBullet.getWidth();
        int h = y + bullet.pBullet.getHeight();

        if (w < litBossX || x > litBossX + litBoss.getWidth()) {
            return false;
        } else if (h < litBossY || y > litBossY + litBoss.getHeight()) {
            return false;
        }
        if (boosHp <= 0) {
            isDie = true;
        }
        return true;
    }

    public void setBoosHp(int boosHp) {
        this.boosHp = boosHp;
    }

    public int getBoosHp() {
        return boosHp;
    }

}
