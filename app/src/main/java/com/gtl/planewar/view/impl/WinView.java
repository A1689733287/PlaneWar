package com.gtl.planewar.view.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;

import com.gtl.planewar.utils.Config;
import com.gtl.planewar.utils.GameGetResource;
import com.gtl.planewar.utils.GetDM;
import com.gtl.planewar.view.ViewActivity;
import com.gtl.planewar.view.IMove;

/**
 * Created by G on 2017/6/21.
 */

public class WinView extends View implements IMove {
    private int screenWidth, screenHeight;
    private Bitmap back;
    private Paint paint;
    private int score;
    private Bitmap start;
    private Bitmap menu;
    private Bitmap end;
    int start_x, menu_x, end_x;
    int start_y, menu_y, end_y;
    private ViewActivity viewActivity;
    private Message msg;

    public WinView(Context context, int score) {
        super(context);
        viewActivity = (ViewActivity) context;
        this.score = score;
        init();
    }

    public void init() {
        paint = new Paint();
        menu = GameGetResource.menu;
        end = GameGetResource.exit;
        start = GameGetResource.restart;
        back = GameGetResource.background;
        screenHeight = GetDM.screenH;
        screenWidth = GetDM.screenW;
        msg = new Message();
        start_x = screenWidth / 2 - start.getWidth() / 2;
        start_y = screenHeight / 3;
        menu_x = screenWidth / 2 - start.getWidth() / 2;
        menu_y = screenHeight / 2;
        end_x = screenWidth / 2 - start.getWidth() / 2;
        end_y = screenHeight * 2 / 3;
    }

    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(Color.WHITE);
        paint.setTextSize(60);
        canvas.drawBitmap(back, 0, 0, paint);
        canvas.drawText("你赢了分数为：" + score, screenWidth / 3 - 120, screenHeight / 3 - 100, paint);
        canvas.drawBitmap(start, start_x, start_y, paint);
        canvas.drawBitmap(menu, menu_x, menu_y, paint);
        canvas.drawBitmap(end, end_x, end_y, paint);
    }

    @Override
    public void logic() {

    }

    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (x > start_x && x < start_x + start.getWidth() && y > start_y && y < start_y + start.getWidth()) {
                    msg.what = Config.MAIN_VIEW;
                    viewActivity.getHandler().sendMessage(msg);
                }
                if (x > menu_x && x < menu_x + menu.getWidth() && y > menu_y && y < menu_y + menu.getWidth()) {
                    msg.what = Config.TO_MAIN;
                    viewActivity.getHandler().sendMessage(msg);
                }
                if (x > end_x && x < end_x + end.getWidth() && y > end_y && y < end_y + end.getWidth()) {
                    msg.what = Config.END_VIEW;
                    viewActivity.getHandler().sendMessage(msg);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}
