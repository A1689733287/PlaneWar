package com.gtl.planewar.utils;

import android.app.Activity;
import android.view.Window;

/**
 * Created by G on 2017/6/12.
 */

public class FullScreen {
    /**
     * 设置隐藏标题栏
     *
     * @param activity
     */
    public static void setNoTitleBar(Activity activity) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

}
