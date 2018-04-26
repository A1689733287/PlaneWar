package com.gtl.planewar.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.gtl.planewar.R;


/**
 * Created by G on 2017/6/12.
 */

public class GameGetResource {
    public static Bitmap player;//主角
    public static Bitmap boss;//boss资源
    public static Bitmap addBlood;//添加血量
    public static Bitmap addBullet;//添加子弹
    public static Bitmap background, background1;//背景资源
    public static Bitmap start;//开始资源
    public static int bgm;//背景音乐
    public static Bitmap pBullet, eBullet;//主角子弹
    public static Bitmap enemy, enemy1, enemy2;//敌机
    public static Bitmap ufo;//背景
    public static Bitmap aid;//主角血量
    public static Bitmap bomb;//爆炸效果
    public static Bitmap end;//结束按钮
    public static Bitmap menu;//菜单按钮
    public static int boom;//爆炸音效
    public static int goods;//得到物品音效
    public static int shoot;//子弹音效
    public static Bitmap back;//返回按钮
    public static Bitmap exit;//结束按钮
    public static Bitmap restart;//开始资源
    public static Bitmap pause;//暂停按钮

    public GameGetResource(Context context) {
        exit=BitmapFactory.decodeResource(context.getResources(), R.drawable.rexit);
        menu = BitmapFactory.decodeResource(context.getResources(), R.drawable.menu);
        start = BitmapFactory.decodeResource(context.getResources(), R.drawable.start);
        restart = BitmapFactory.decodeResource(context.getResources(), R.drawable.restart);
        end = BitmapFactory.decodeResource(context.getResources(), R.drawable.exit);
        aid = BitmapFactory.decodeResource(context.getResources(), R.drawable.blood);
        pBullet = BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet);
        eBullet = BitmapFactory.decodeResource(context.getResources(), R.drawable.ebullet);
        enemy1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy3);
        ufo = BitmapFactory.decodeResource(context.getResources(), R.drawable.boss);
        enemy2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy2);
        enemy = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy);
        background1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.background1);
        background = BitmapFactory.decodeResource(context.getResources(), R.drawable.background);
        player = BitmapFactory.decodeResource(context.getResources(), R.drawable.player);
        boss = BitmapFactory.decodeResource(context.getResources(), R.drawable.boss);
        enemy = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy);
        addBlood = BitmapFactory.decodeResource(context.getResources(), R.drawable.blood);
        addBullet = BitmapFactory.decodeResource(context.getResources(), R.drawable.addbullet);
        bomb = BitmapFactory.decodeResource(context.getResources(), R.drawable.boom);
        pause=BitmapFactory.decodeResource(context.getResources(),R.drawable.pause);
        bgm = R.raw.bgm;
        boom = R.raw.boom;
        shoot = R.raw.shoot;
        goods = R.raw.getgoods;
    }
}
