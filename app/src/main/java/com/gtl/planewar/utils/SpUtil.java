package com.gtl.planewar.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by G on 2017/6/27.
 */

public class SpUtil {
    private static SharedPreferences sp;

    /**
     * 编辑储存节点
     * @param context 上下文
     * @param key 储存节点名称
     * @param value 储存节点boolean值
     */
    public static void putBoolean(Context context,String key,boolean value)
    {
        if (sp==null)
        {
            sp=context.getSharedPreferences("config",Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key,value).commit();
    }

    /**
     * 读取储存节点
     * @param context 上下文环境
     * @param key 储存节点
     * @param defValue 默认值
     * @return 默认取道的结果
     */
    public static boolean getBoolean(Context context,String key,boolean defValue)
    {
        if (sp==null)
        {
            sp=context.getSharedPreferences("config",Context.MODE_PRIVATE);
        }
        return sp.getBoolean(key,defValue);
    }
}
