package com.gtl.planewar.model.impl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.gtl.planewar.model.BaseLogic;
import com.gtl.planewar.utils.Config;
import com.gtl.planewar.utils.GetDM;


/**
 * Created by G on 2017/4/15.
 */

public class Enemy implements BaseLogic {
    public int enemyX, enemyY;
    public int screenWidth, screenHeight;
    Bitmap enemy;
    public boolean isDie;
    public int type;
    int count;
    int zSpeed = 4;
    private int speed = 5;
    private int enemyHp = 6;


    public Enemy(Bitmap enemy, int enemyType, int x) {
        init();
        this.enemy = enemy;
        enemyX = x;
        this.type = enemyType;
        switch (type) {
            case Config.TYPE_CENTER_LEFT:
                enemyY = -enemy.getHeight();
                enemyX = screenWidth / 4 - enemy.getWidth();
                break;
            case Config.TYPE_CENTER_RIGHT:
                enemyY = -enemy.getHeight();
                enemyX = screenWidth * 3 / 4;
                break;
            case Config.TYPE_LEFT:
                enemyY = screenHeight / 5;
                break;
            case Config.TYPE_RIGHT:
                enemyY = screenHeight / 5;
                break;
            case Config.TYPE_CENTER:
                enemyY = -enemy.getHeight();
                enemyX = screenWidth / 2 - enemy.getWidth() / 2;
                break;
            case Config.TYPE_RIGHT_SIDE:
                enemyY = screenHeight / 5;
                break;
            case Config.TYPE_LEFT_SIDE:
                enemyY = screenHeight / 5;
                break;
            case Config.TYPE_RIGHT_SIDE_IN:
                enemyY = screenHeight / 5;
                break;
            case Config.TYPE_LEFT_SIDE_IN:
                enemyY = screenHeight / 5;
                break;
        }
    }

    @Override
    public void init() {
        screenHeight = GetDM.screenH;
        screenWidth = GetDM.screenW;
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
        //绘制敌机
        canvas.drawBitmap(enemy, enemyX, enemyY, paint);
        canvas.restore();
    }

    public void logic() {
        switch (type) {
            case Config.TYPE_CENTER_LEFT:
                enemyY += speed;
                if (enemyY > screenHeight) {
                    isDie = true;
                }
                break;
            case Config.TYPE_CENTER_RIGHT:
                enemyY += speed;
                if (enemyY > screenHeight) {
                    isDie = true;
                }
                break;
            case Config.TYPE_LEFT:
                enemyY += 4;
                enemyX += 3;
                if (enemyX > screenWidth / 2)
                    enemyX += 4;
                if (enemyY > screenHeight) {
                    isDie = true;
                }
                break;
            case Config.TYPE_RIGHT:
                enemyY += 4;
                enemyX -= 3;
                if (enemyX < screenWidth / 2)
                    enemyX -= 4;
                if (enemyY > screenHeight) {
                    isDie = true;
                }
                break;
            case Config.TYPE_CENTER:
                enemyY += speed;
                if (enemyY > screenHeight) {
                    isDie = true;
                }
                break;
            case Config.TYPE_RIGHT_SIDE:
                enemyY = screenHeight / 5;
                break;
            case Config.TYPE_LEFT_SIDE:
                enemyY = screenHeight / 5;
                break;
            case Config.TYPE_LEFT_SIDE_IN:
                enemyX += zSpeed;
                if (enemyX >= screenWidth / 2 - enemy.getWidth() * 2) {
                    enemyX -= zSpeed;
                    count++;
                    if (count == 200)
                        zSpeed = -zSpeed;
                }
                if (enemyX < 0 - enemy.getWidth())
                    isDie = true;
                break;
            case Config.TYPE_RIGHT_SIDE_IN:
                enemyX -= zSpeed;
                if (enemyX <= screenWidth / 2 + enemy.getWidth()) {
                    enemyX += zSpeed;
                    count++;
                    if (count == 200)
                        zSpeed = -zSpeed;
                }
                if (enemyX > screenWidth)
                    isDie = true;
                break;
        }
    }

    /**
     * 判断敌机与子弹是否碰撞
     *
     * @param bullet
     * @return
     */
    public boolean collision(Bullet bullet) {
        int x = bullet.bulletX;
        int y = bullet.bulletY;
        int w = x + bullet.pBullet.getWidth();
        int h = y + bullet.pBullet.getHeight();

        if (w < enemyX || x > enemyX + enemy.getWidth()) {
            return false;
        } else if (h < enemyY || y > enemyY + enemy.getHeight()) {
            return false;
        }
        if (enemyHp <= 0) {
            isDie = true;
        }
        return true;
    }

    /* public boolean collision(BulletL bullet) {
         int x = bullet.bulletX;
         int y = bullet.bulletY;
         int w = x + bullet.pBullet.getWidth();
         int h = y + bullet.pBullet.getHeight();
         if (w < enemyX || x > enemyX + enemy.getWidth()) {
             return false;
         }
         if (h < enemyY || y > enemyY + enemy.getHeight()) {
             return false;
         }
         if (enemyHp <= 0) {
             isDie = true;
         }
         return true;
     }

     public boolean collision(BulletR bullet) {
         int x = bullet.bulletX;
         int y = bullet.bulletY;
         int w = x + bullet.pBullet.getWidth();
         int h = y + bullet.pBullet.getHeight();
         if (w < enemyX || x > enemyX + enemy.getWidth()) {
             return false;
         }
         if (h < enemyY || y > enemyY + enemy.getHeight()) {
             return false;
         }
         if (enemyHp <= 0) {
             isDie = true;
         }
         return true;
     }
 */
    public int getHp() {
        return enemyHp;
    }

    public void setHp(int hp) {
        this.enemyHp = hp;
    }
}
