package com.example.haitao.crowdfunding.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by haitao on 2020/6/24.
 */

public class CacheUtils {
    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sp=context.getSharedPreferences("athaitao",Context.MODE_PRIVATE);
        return sp.getBoolean(key,false);
    }

    public static void putBoolean(Context context, String key, boolean value) {
    SharedPreferences sp=context.getSharedPreferences("athaitao",Context.MODE_PRIVATE);
    sp.edit().putBoolean(key,value).commit();
    }
}
