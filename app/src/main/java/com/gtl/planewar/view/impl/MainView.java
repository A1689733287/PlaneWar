package com.gtl.planewar.view.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.gtl.planewar.model.impl.Aid;
import com.gtl.planewar.model.impl.Boom;
import com.gtl.planewar.model.impl.Boss;
import com.gtl.planewar.model.impl.Bullet;
import com.gtl.planewar.model.impl.BulletAdd;
import com.gtl.planewar.model.impl.Enemy;
import com.gtl.planewar.model.impl.GameBg;
import com.gtl.planewar.model.impl.Player;
import com.gtl.planewar.utils.Config;
import com.gtl.planewar.utils.GameGetResource;
import com.gtl.planewar.utils.GetDM;
import com.gtl.planewar.utils.ScoreShare;
import com.gtl.planewar.view.MainActivity;
import com.gtl.planewar.view.ViewActivity;
import com.gtl.planewar.view.IMove;

import java.util.Vector;


/**
 * Created by G on 2017/3/17.
 */

public class MainView extends View implements IMove, Runnable {
    int level;
    int score = 0;
    int count = 0;
    int bulletLevel = 1;
    Bitmap enemy1;
    Vector<Boom> booms;
    boolean play;
    private Bitmap bmplayer;//主角
    private Bitmap pBullet, eBullet;//主角子弹
    private Bitmap enemy, enemy2;//敌机
    private Bitmap back;//背景
    private Bitmap ufo;//大老板
    private Bitmap aid;//主角血量
    private Bitmap bulletadds;
    private Bitmap boom;//爆炸效果
    private Paint paint;//画笔
    private int screenWidth;//屏幕宽度
    private int screenHeight;//屏幕高度
    private GameBg gameBg;//背景类
    private Player player;//主角类
    //countPlayerBullet 主角子弹计数器,countEnemy 敌机计数器;
    private int countPlayerBullet, countEnemyBullet;
    private Vector<Enemy> enemys; //敌机容器
    //主角子弹容器,Vector集合
    private Vector<Bullet> pBullets;
    private Vector<Bullet> eBullets;
    private Vector<Bullet> lBullets;
    private Vector<Bullet> rBullets;
    private Vector<Bullet> bBullets;
    private Vector<Aid> aids;
    private Vector<BulletAdd> bulletAdds;
    private Boss littleBoss;//老板类
    private ViewActivity viewActivity;//ViewActivity类
    private Message msg;//消息
    private ScoreShare scoreShare;//分数工具类
    Bitmap pause;
    Thread th;
    Canvas canvas;

    public MainView(Context context) {
        super(context);
        viewActivity = (ViewActivity) context;
        init(context);
    }

    public void init(Context context) {
        level = 1;
        play = true;
        pause = GameGetResource.pause;
        aid = GameGetResource.aid;
        pBullet = GameGetResource.pBullet;
        eBullet = GameGetResource.eBullet;
        back = GameGetResource.background1;
        bmplayer = GameGetResource.player;
        enemy1 = GameGetResource.enemy1;
        ufo = GameGetResource.ufo;
        enemy2 = GameGetResource.enemy2;
        enemy = GameGetResource.enemy;
        bulletadds = GameGetResource.addBullet;
        boom = GameGetResource.bomb;
        msg = new Message();
        paint = new Paint();
        screenWidth = GetDM.screenW;//屏幕的宽度
        screenHeight = GetDM.screenH;//屏幕的高度
        player = new Player(bmplayer);
        gameBg = new GameBg(back);
        pBullets = new Vector<>();
        eBullets = new Vector<>();
        bBullets = new Vector<>();
        enemys = new Vector<>();
        booms = new Vector<>();
        aids = new Vector<>();
        lBullets = new Vector<>();
        rBullets = new Vector<>();
        bulletAdds = new Vector<>();
        littleBoss = new Boss(ufo, screenWidth / 2 - ufo.getWidth() / 2);
        scoreShare = new ScoreShare(context);
    }

    /**
     * 绘图
     *
     * @param canvas 画布
     */
    public void onDraw(Canvas canvas) {
        super.draw(canvas);
        this.canvas = canvas;
        th = new Thread(this);
        th.start();
        //背景的绘制
        gameBg.draw(canvas, paint);
        //主角子弹的绘制
        try {
            if (bulletLevel == 1) {
                for (int i = 0; i < pBullets.size(); i++) {
                    pBullets.get(i).draw(canvas, paint);
                }
            } else if (bulletLevel >= 3 && bulletLevel < 5) {

                for (int i = 0; i < lBullets.size(); i++) {
                    lBullets.get(i).draw(canvas, paint);
                }
                for (int i = 0; i < rBullets.size(); i++) {
                    rBullets.get(i).draw(canvas, paint);
                }
            } else if (bulletLevel >= 5) {
                for (int i = 0; i < lBullets.size(); i++) {
                    lBullets.get(i).draw(canvas, paint);
                }
                for (int i = 0; i < pBullets.size(); i++) {
                    pBullets.get(i).draw(canvas, paint);
                }
                for (int i = 0; i < rBullets.size(); i++) {
                    rBullets.get(i).draw(canvas, paint);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < bulletAdds.size(); i++) {
            bulletAdds.get(i).draw(canvas, paint);
        }
        //敌机的绘制
        for (int j = 0; j < enemys.size(); j++) {
            enemys.get(j).draw(canvas, paint);
        }
        //Boss的绘制
        littleBoss.draw(canvas, paint);
        //爆炸效果
        for (int j = 0; j < booms.size(); j++) {

            booms.get(j).draw(canvas, paint);
        }
        //敌机子弹
        for (int i = 0; i < eBullets.size(); i++) {
            eBullets.get(i).draw(canvas, paint);
        }
        //boss子弹
        for (int i = 0; i < bBullets.size(); i++) {
            bBullets.get(i).draw(canvas, paint);
        }
        for (int i = 0; i < aids.size(); i++) {
            aids.get(i).draw(canvas, paint);
        }
        //绘制飞机
        player.draw(canvas, paint);
        //设置画笔颜色为白色
        canvas.drawBitmap(pause, screenWidth - pause.getWidth(), 10, paint);
        paint.setColor(Color.WHITE);
        //设置文本大小为100
        paint.setTextSize(50);
        //绘制分数
        canvas.drawText("分数：" + score, 10, 60, paint);
        //主角生命值
        paint.setTextSize(50);
        canvas.drawText("生命值：" + player.getHp() / 2, 10, screenHeight - 100, paint);
        paint.setTextSize(30);
        //大老板生命的绘制
        paint.setColor(Color.RED);
        canvas.drawText("" + littleBoss.getBoosHp() / 2, littleBoss.litBossX + ufo.getWidth() / 2, littleBoss.litBossY, paint);
        invalidate();
    }

    /**
     * 游戏逻辑
     */
    public void logic() {
        try {
            if (!play) {
                pau();
            }
            MainActivity.bgm.start();
            //背景逻辑
            gameBg.logic();
            //绘制敌机逻辑
            count++;
            //中间两侧敌机
            if (count % 140 == 0) {
                enemys.addElement(new Enemy(enemy, Config.TYPE_CENTER_LEFT, screenWidth));
                enemys.addElement(new Enemy(enemy, Config.TYPE_CENTER_RIGHT, screenWidth));
            }
            //中间敌机
            if (count % 200 == 0) {
                enemys.addElement(new Enemy(enemy, Config.TYPE_CENTER, screenWidth));
            }
            //两侧敌机
            if (count % 160 == 0) {
                enemys.addElement(new Enemy(enemy1, Config.TYPE_LEFT, -enemy1.getWidth()));
                enemys.addElement(new Enemy(enemy1, Config.TYPE_RIGHT, screenWidth));
            }
            if (count % 400 == 0) {
                enemys.addElement(new Enemy(enemy2, Config.TYPE_LEFT_SIDE_IN, -enemy1.getWidth()));
                enemys.addElement(new Enemy(enemy2, Config.TYPE_RIGHT_SIDE_IN, screenWidth));
            }
            if (score >= 1500) {
                littleBoss.logic();
            }
            if (count % 800 == 0) {
                aids.addElement(new Aid(aid, screenWidth / 2 - aid.getWidth() / 2, -aid.getHeight()));
            }
            if (count % 500 == 0) {
                bulletAdds.addElement(new BulletAdd(bulletadds, screenWidth / 2 - aid.getWidth() / 2, -aid.getHeight()));
            }
            //敌机逻辑
            for (int j = 0; j < enemys.size(); j++) {
                Enemy e = enemys.get(j);
                //敌机出界处理
                if (e.isDie) {
                    enemys.remove(e);
                } else {
                    enemys.get(j).logic();
                }
            }
            //主角子弹逻辑

            //每0.1添加一个子弹
            countPlayerBullet++;

            if (bulletLevel == 1) {
                if (countPlayerBullet % 5 == 0) {
                    pBullets.addElement(new Bullet(pBullet, Config.BULLET_CENTER, Config.DIR_UP, player.playerX + bmplayer.getWidth() / 2 - pBullet.getWidth() / 2, player.playerY));
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            MainActivity.sound.playSound(3, 0);
                        }
                    }).start();


                }
            } else if (bulletLevel >= 3 && bulletLevel < 5) {
                if (countPlayerBullet % 5 == 0) {
                    lBullets.addElement(new Bullet(pBullet, Config.BULLET_CENTER, Config.DIR_UP, player.playerX + bmplayer.getWidth() / 2 - pBullet.getWidth() / 2 - 40, player.playerY));
                    rBullets.addElement(new Bullet(pBullet, Config.BULLET_CENTER, Config.DIR_UP, player.playerX + bmplayer.getWidth() / 2 - pBullet.getWidth() / 2 + 40, player.playerY));
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            MainActivity.sound.playSound(3, 0);
                        }
                    }).start();

                }
            } else if (bulletLevel >= 5) {
                if (countPlayerBullet % 5 == 0) {
                    pBullets.addElement(new Bullet(pBullet, Config.BULLET_CENTER, Config.DIR_UP, player.playerX + bmplayer.getWidth() / 2 - pBullet.getWidth() / 2, player.playerY));
                    lBullets.addElement(new Bullet(pBullet, Config.BULLET_CENTER, Config.DIR_UP, player.playerX + bmplayer.getWidth() / 2 - pBullet.getWidth() / 2 - 40, player.playerY + 20));
                    rBullets.addElement(new Bullet(pBullet, Config.BULLET_CENTER, Config.DIR_UP, player.playerX + bmplayer.getWidth() / 2 - pBullet.getWidth() / 2 + 40, player.playerY + 20));
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            MainActivity.sound.playSound(3, 0);
                        }
                    }).start();
                }
            }
            //主角子弹
            for (int i = 0; i < pBullets.size(); i++) {
                Bullet b = pBullets.get(i);
                //子弹出界处理
                if (b.isDead) {
                    pBullets.remove(b);
                } else {
                    b.logic();
                }
                //主角子弹与敌机碰撞
                for (int j = 0; j < enemys.size(); j++) {
                    if (enemys.get(j).collision(b)) {
                        enemys.get(j).setHp(enemys.get(j).getHp() - 1);
                        if (enemys.get(j).getHp() <= 0) {
                            score += 10;
                            booms.addElement(new Boom(boom, enemys.get(j).enemyX, enemys.get(j).enemyY));
                            MainActivity.sound.playSound(1, 0);
                            enemys.remove(j);
                        }
                        b.isDead = true;
                    }
                }

                //Boss与主角子弹碰撞
                if (littleBoss.collision(b)) {
                    littleBoss.setBoosHp(littleBoss.getBoosHp() - 1);
                    if (littleBoss.getBoosHp() <= 0) {
                        score += 100;
                        MainActivity.bgm.stop();
                        msg.what = Config.WIN_VIEW;
                        msg.arg1 = score;
                        viewActivity.getHandler().sendMessage(msg);
                        scoreShare.saveScore(score);
                    }
                    b.isDead = true;
                }
            }
            //主角子弹
            for (int i = 0; i < lBullets.size(); i++) {
                Bullet b = lBullets.get(i);
                //子弹出界处理
                if (b.isDead) {
                    lBullets.remove(b);
                } else {
                    b.logic();
                }
                //主角子弹与敌机碰撞
                for (int j = 0; j < enemys.size(); j++) {
                    if (enemys.get(j).collision(b)) {
                        enemys.get(j).setHp(enemys.get(j).getHp() - 1);
                        if (enemys.get(j).getHp() <= 0) {
                            score += 10;
                            booms.addElement(new Boom(boom, enemys.get(j).enemyX, enemys.get(j).enemyY));
                            MainActivity.sound.playSound(1, 0);
                            enemys.remove(j);
                        }
                        b.isDead = true;
                    }
                }

                //Boss与主角子弹碰撞
                if (littleBoss.collision(b)) {
                    littleBoss.setBoosHp(littleBoss.getBoosHp() - 1);
                    if (littleBoss.getBoosHp() <= 0) {
                        score += 100;
                        MainActivity.bgm.stop();
                        msg.what = Config.WIN_VIEW;
                        msg.arg1 = score;
                        viewActivity.getHandler().sendMessage(msg);
                        scoreShare.saveScore(score);
                    }
                    b.isDead = true;
                }
            }
            //主角子弹
            for (int i = 0; i < rBullets.size(); i++) {
                Bullet b = rBullets.get(i);
                //子弹出界处理
                if (b.isDead) {
                    rBullets.remove(b);
                } else {
                    b.logic();
                }
                //主角子弹与敌机碰撞
                for (int j = 0; j < enemys.size(); j++) {
                    if (enemys.get(j).collision(b)) {
                        enemys.get(j).setHp(enemys.get(j).getHp() - 1);
                        if (enemys.get(j).getHp() <= 0) {
                            score += 10;
                            booms.addElement(new Boom(boom, enemys.get(j).enemyX, enemys.get(j).enemyY));
                            MainActivity.sound.playSound(1, 0);
                            enemys.remove(j);
                        }
                        b.isDead = true;
                    }
                }

                //Boss与主角子弹碰撞
                if (littleBoss.collision(b)) {
                    littleBoss.setBoosHp(littleBoss.getBoosHp() - 1);
                    if (littleBoss.getBoosHp() <= 0) {
                        score += 100;
                        MainActivity.bgm.stop();
                        msg.what = Config.WIN_VIEW;
                        msg.arg1 = score;
                        viewActivity.getHandler().sendMessage(msg);
                        scoreShare.saveScore(score);
                    }
                    b.isDead = true;
                }
            }

            //爆炸逻辑
            for (int i = 0; i < booms.size(); i++) {
                if (booms.get(i).isCollision) {
                    booms.remove(i);
                } else {
                    booms.get(i).logic();
                }
            }

            //敌机子弹
            countEnemyBullet++;
            if (countEnemyBullet % 90 == 0) {
                for (int i = 0; i < enemys.size(); i++) {
                    Enemy e = enemys.elementAt(i);
                    switch (e.type) {
                        case Config.TYPE_CENTER_LEFT:
                            eBullets.addElement(new Bullet(eBullet, Config.BULLET_SIDE, Config.DIR_CENTER_DOWN, e.enemyX + enemy.getWidth() / 2 - pBullet.getWidth() / 2, e.enemyY + enemy.getHeight()));
                            break;
                        case Config.TYPE_CENTER_RIGHT:
                            eBullets.addElement(new Bullet(eBullet, Config.BULLET_SIDE, Config.DIR_CENTER_DOWN, e.enemyX + enemy.getWidth() / 2 - pBullet.getWidth() / 2, e.enemyY + enemy.getHeight()));
                            break;
                        case Config.TYPE_LEFT:
                            eBullets.addElement(new Bullet(eBullet, Config.BULLET_SIDE, Config.DIR_CENTER_DOWN, e.enemyX + enemy.getWidth() / 2 - pBullet.getWidth() / 2, e.enemyY + enemy.getHeight()));
                            break;
                        case Config.TYPE_RIGHT:
                            eBullets.addElement(new Bullet(eBullet, Config.BULLET_SIDE, Config.DIR_CENTER_DOWN, e.enemyX + enemy.getWidth() / 2 - pBullet.getWidth() / 2, e.enemyY + enemy.getHeight()));
                            break;
                        case Config.TYPE_CENTER:
                            eBullets.addElement(new Bullet(eBullet, Config.BULLET_SIDE, Config.DIR_CENTER_DOWN, e.enemyX + enemy.getWidth() / 2 - pBullet.getWidth() / 2, e.enemyY + enemy.getHeight()));
                            break;
                        case Config.TYPE_LEFT_SIDE:
                            eBullets.addElement(new Bullet(eBullet, Config.BULLET_SIDE, Config.DIR_CENTER_DOWN, e.enemyX + enemy.getWidth() / 2 - pBullet.getWidth() / 2, e.enemyY + enemy.getHeight()));
                            break;
                        case Config.TYPE_LEFT_SIDE_IN:
                            eBullets.addElement(new Bullet(eBullet, Config.BULLET_SIDE, Config.DIR_CENTER_DOWN, e.enemyX + enemy.getWidth() / 2 - pBullet.getWidth() / 2, e.enemyY + enemy.getHeight()));
                            eBullets.addElement(new Bullet(eBullet, Config.BULLET_SIDE, Config.DIR_RIGHT_RDOWN, e.enemyX + enemy.getWidth() / 2 - pBullet.getWidth() / 2, e.enemyY + enemy.getHeight()));
                            break;
                        case Config.TYPE_RIGHT_SIDE_IN:
                            eBullets.addElement(new Bullet(eBullet, Config.BULLET_SIDE, Config.DIR_CENTER_DOWN, e.enemyX + enemy.getWidth() / 2 - pBullet.getWidth() / 2, e.enemyY + enemy.getHeight()));
                            eBullets.addElement(new Bullet(eBullet, Config.BULLET_SIDE, Config.DIR_LEFT_LDOWN, e.enemyX + enemy.getWidth() / 2 - pBullet.getWidth() / 2, e.enemyY + enemy.getHeight()));
                            break;
                    }
                }
            }
            if (countEnemyBullet % 80 == 0) {
                bBullets.addElement(new Bullet(eBullet, Config.BOSS, Config.DIR_CENTER_DOWN, littleBoss.litBossX + ufo.getWidth() / 2 - pBullet.getWidth() / 2, littleBoss.litBossY + ufo.getHeight() / 2));
                bBullets.addElement(new Bullet(eBullet, Config.BOSS, Config.DIR_RIGHT_RDOWN, littleBoss.litBossX + ufo.getWidth() / 2 - pBullet.getWidth() / 2, littleBoss.litBossY + ufo.getHeight() / 2));
                bBullets.addElement(new Bullet(eBullet, Config.BOSS, Config.DIR_LEFT_LDOWN, littleBoss.litBossX + ufo.getWidth() / 2 - pBullet.getWidth() / 2, littleBoss.litBossY + ufo.getHeight() / 2));
                bBullets.addElement(new Bullet(eBullet, Config.BOSS, 4, littleBoss.litBossX + ufo.getWidth() / 2 - pBullet.getWidth() / 2, littleBoss.litBossY + ufo.getHeight() / 2));
                bBullets.addElement(new Bullet(eBullet, Config.BOSS, 5, littleBoss.litBossX + ufo.getWidth() / 2 - pBullet.getWidth() / 2, littleBoss.litBossY + ufo.getHeight() / 2));
            }
            //敌机子弹逻辑
            for (int i = 0; i < eBullets.size(); i++) {
                Bullet eb = eBullets.elementAt(i);
                if (eb.isDead) {
                    eBullets.remove(i);
                } else {
                    eb.logic();
                }
                //主角与敌机子弹碰撞
                if (player.collisionBullet(eb)) {
                    player.setHp(player.getHp() - 1);
                    if (player.getHp() <= 0) {
                        MainActivity.bgm.stop();
                        msg.what = Config.DIE_VIEW;
                        msg.arg1 = score;
                        viewActivity.getHandler().sendMessage(msg);
                        scoreShare.saveScore(score);

                    }
                    eb.isDead = true;
                }
            }
            //boss子弹逻辑
            for (int i = 0; i < bBullets.size(); i++) {
                Bullet bb = bBullets.get(i);
                //子弹出界处理
                if (bb.isDead) {
                    bBullets.remove(bb);
                } else {
                    bb.logic();
                }
                if (player.collisionBullet(bb)) {
                    player.setHp(player.getHp() - 1);
                    if (player.getHp() <= 0) {
                        MainActivity.bgm.stop();
                        msg.what = Config.DIE_VIEW;
                        msg.arg1 = score;
                        viewActivity.getHandler().sendMessage(msg);
                        scoreShare.saveScore(score);

                    }
                    bb.isDead = true;
                }
            }
            //血量逻辑
            for (int i = 0; i < aids.size(); i++) {
                Aid aid = aids.get(i);
                if (aid.isDead) {
                    aids.remove(i);
                } else {
                    aid.logic();
                }
                //敌机与血量碰撞
                if (player.collisionBlood(aid)) {
                    player.setHp(player.getHp() + 2);
                    aid.isDead = true;
                    MainActivity.sound.playSound(2, 0);
                }
            }
            //添加子弹的逻辑
            for (int i = 0; i < bulletAdds.size(); i++) {
                BulletAdd ba = bulletAdds.get(i);
                if (ba.isDead) {
                    bulletAdds.remove(i);
                } else {
                    ba.logic();
                }
                //主角与添加子弹的逻辑
                if (player.collisionAdd(ba)) {
                    bulletLevel = bulletLevel + 1;
                    MainActivity.sound.playSound(2, 0);
                    ba.isDead = true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

/*
    public void release() {
        if (!back.isRecycled()) {
            back.recycle();
        }
        if (!eBullet.isRecycled()) {
            eBullet.recycle();
        }
        if (!pBullet.isRecycled()) {
            pBullet.recycle();
        }
        if (!enemy.isRecycled()) {
            enemy.recycle();
        }
        if (!enemy1.isRecycled()) {
            enemy1.recycle();
        }
        if (!enemy2.isRecycled()) {
            enemy2.recycle();
        }
        if (!aid.isRecycled()) {
            aid.recycle();
        }
        if (!bmplayer.isRecycled()) {
            bmplayer.recycle();
        }
        if (!ufo.isRecycled()) {
            ufo.recycle();
        }
        if (!bulletadds.isRecycled()) {
            bulletadds.recycle();
        }
        if (!boom.isRecycled()) {
            boom.recycle();
        }
    }*/

    /**
     * 触屏事件
     *
     * @param event 事件
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x;
        int y;
        player.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = (int) event.getX();
                y = (int) event.getY();
                if (x < screenWidth && x > screenWidth - pause.getWidth() && y > 10 && y < pause.getHeight()) {
                    if (play) {
                        play = false;
                    } else {
                        play = true;
                        continu();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    public void pau() {
        synchronized (th) {
            try {
                th.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void continu() {
        synchronized (th) {
            try {
                th.notify();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        logic();
        long end = System.currentTimeMillis();
        try {
            if (end - start < 50) {
                th.sleep(50 - (end - start));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
