package com.gtl.planewar.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by G on 2017/6/12.
 */

public class GetDM {
    public static int screenW;
    public static int screenH;

    public GetDM(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        screenW = dm.widthPixels;//获取屏幕的宽度
        screenH = dm.heightPixels;//获取屏幕的高度
    }
}
