package com.gtl.planewar.model;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by G on 2017/6/12.
 */

public interface BaseLogic {
    void init();

    void draw(Canvas canvas, Paint paint);

    void logic();
}
