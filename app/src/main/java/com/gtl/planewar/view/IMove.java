package com.gtl.planewar.view;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by G on 2017/6/12.
 */

    public interface IMove {
        void onDraw(Canvas canvas);

        void logic();

        boolean onTouchEvent(MotionEvent event);
}
