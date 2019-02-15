package com.italkbb.imetis.util;

import android.util.Log;

/**
 * Created by qwl on 2017/8/28.
 */

public class Slog {

    public static final boolean FLAG = true;

    public static void i(String tag, String msg) {
        if (FLAG) {
            Log.i(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (FLAG) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (FLAG) {
            Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (FLAG) {
            Log.e(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (FLAG) {
            Log.w(tag, msg);
        }
    }
}
