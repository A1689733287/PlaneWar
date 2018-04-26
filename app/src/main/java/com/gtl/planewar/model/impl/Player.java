package com.gtl.planewar.model.impl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.gtl.planewar.utils.GetDM;

/**
 * Created by G on 2017/4/15.
 */

public class Player {
    int screenWidth, screenHeight;
    private Bitmap player;
    int dx, dy;
    public int playerX;
    public int playerY;
    boolean isDead;
    private int hp = 10;
    boolean isTouched;

    public Player(Bitmap player) {
        this.player = player;
        init();
    }

    public void init() {
        screenHeight = GetDM.screenH;
        screenWidth = GetDM.screenW;
        //飞机的位置
        playerX = screenWidth / 2 - player.getWidth() / 2;
        playerY = screenHeight - player.getHeight() * 2;
    }

    public void draw(Canvas canvas, Paint paint) {
        //飞机的绘制
        canvas.save();
        canvas.drawBitmap(player, playerX, playerY, paint);
        canvas.restore();
    }

    /**
     * @param event 触屏事件
     */
    public boolean onTouchEvent(MotionEvent event) {
        //手指按下事件
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //手指按在屏幕的坐标
            dx = (int) event.getX();
            dy = (int) event.getY();
            //判断手指是否在飞机上
            if (dx >= playerX && dx <= playerX + player.getWidth() && dy >= playerY && dy <= playerY + player.getHeight()) {
                isTouched = true;
            } else return isTouched = false;
        }
        //手指移动事件
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (isTouched) {
                playerX = (int) event.getX() - player.getWidth() / 2;
                playerY = (int) event.getY() - player.getHeight() / 2;
                if (playerY + player.getHeight() >= screenHeight) {
                    playerY = screenHeight - player.getHeight();
                }
                if (playerY <= 0) {
                    playerY = 0;
                }
            } else return false;
        }
        //手指离开事件
        if (event.getAction() == MotionEvent.ACTION_UP) {
            return false;
        }
        return false;
    }

    public boolean collision(Enemy enemy) {
        int x = enemy.enemyX;
        int y = enemy.enemyY;
        int w = x + enemy.enemy.getWidth();
        int h = y + enemy.enemy.getHeight();
        if (w < playerX || x > playerX + player.getWidth()) {
            return false;
        }
        if (h < playerY || y > playerY + player.getHeight()) {
            return false;
        }
        if (hp <= 0) {
            isDead = true;
        }
        return true;
    }

    public boolean collisionBullet(Bullet bullet) {
        int x = bullet.bulletX;
        int y = bullet.bulletY;
        int w = x + bullet.pBullet.getWidth();
        int h = y + bullet.pBullet.getHeight();
        if (w < playerX + 10 || x > playerX + player.getWidth() - 10) {
            return false;
        }
        if (h < playerY - 10 || y > playerY + player.getHeight()) {
            return false;
        }
        if (hp <= 0) {
            isDead = true;
        }
        return true;
    }

    public boolean collisionBlood(Aid aid) {
        int x = aid.x;
        int y = aid.y;
        int w = x + aid.aidBlood.getWidth();
        int h = y + aid.aidBlood.getHeight();
        if (w < playerX || x > playerX + player.getWidth()) {
            return false;
        }
        return !(h < playerY || y > playerY + player.getHeight());
    }

    public boolean collisionAdd(BulletAdd add) {
        int x = add.x;
        int y = add.y;
        int w = x + add.bulletAdd.getWidth();
        int h = y + add.bulletAdd.getHeight();
        if (w < playerX || x > playerX + player.getWidth()) {
            return false;
        }
        return !(h < playerY || y > playerY + player.getHeight());
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
