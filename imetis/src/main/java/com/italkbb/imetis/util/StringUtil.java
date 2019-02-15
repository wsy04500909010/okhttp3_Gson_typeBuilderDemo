package com.italkbb.imetis.util;

import android.content.Context;

import java.util.List;

/**
 * Created by qwl on 2017/8/28.
 */

public class StringUtil {

    /**
     * Check if a string is empty or only has spaces.
     *
     * @param str Target string.
     * @return true: empty, false: not empty.
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * Convert a null string to an empty string and never return null
     *
     * @param str source string
     * @return Converted string.
     */
    public static String getNonNull(String str) {
        return str == null ? "" : str;
    }

    public static String listToString(String delimiter, List<String> list) {
        if (list == null || list.size() == 0) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            String temp = list.get(i);
            stringBuffer.append(temp);
            if (i < list.size() - 1) {
                stringBuffer.append(delimiter);
            }
        }
        return stringBuffer.toString();
    }

//    /**
//     * Get the category name according to root id.
//     *
//     * @param root_id
//     * @return
//     */
//    public static String getNameByRootId(Context context, int root_id) {
////        switch (root_id) {
////            case AppConstants.ROOT_ID_HOME:
////                return context.getResources().getString(R.string.home);
////            case AppConstants.ROOT_ID_DRAMA:
////                return context.getResources().getString(R.string.drama);
////            case AppConstants.ROOT_ID_VARIETY:
////                return context.getResources().getString(R.string.variety);
////            case AppConstants.ROOT_ID_LIVE:
////                return context.getResources().getString(R.string.live);
////            case AppConstants.ROOT_ID_AUDIO:
////                return context.getResources().getString(R.string.audio);
////            case AppConstants.ROOT_ID_FILM:
////                return context.getResources().getString(R.string.film);
////            case AppConstants.ROOT_ID_NEWS:
////                return context.getResources().getString(R.string.news);
////            default:
////                return "";
////        }
//        String name = PreferencesUtil.getString(context, String.valueOf(root_id));
//        return StringUtil.getNonNull(name);
//    }
}
