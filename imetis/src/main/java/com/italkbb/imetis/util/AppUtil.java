package com.italkbb.imetis.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.NotificationManagerCompat;
import android.text.TextUtils;


import com.italkbb.imetis.SDKManager;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

/**
 * Created by qwl on 2017/8/28.
 */

public class AppUtil {

    private static final String TAG = "AppUtil";

    /**
     * Return the version name of the package.
     *
     * @param context Current context.
     * @return Version name of the package.
     */
    public static String getAppVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        String versionName = "";
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }


    /**
     * Return the versionCode of the package.
     *
     * @return
     */
    public static int getVersionCode() {
        try {
            return SDKManager.mContext.getApplicationContext()
                    .getPackageManager().getPackageInfo(SDKManager.mContext
                            .getApplicationContext().getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Check if the package exists.
     *
     * @param context Current context.
     * @param pkgName Target package name.
     * @return
     */
    public static boolean appExists(Context context, String pkgName) {
        if (StringUtil.isEmpty(pkgName)) {
            return false;
        }

        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> appsList = packageManager.queryIntentActivities(mainIntent, 0);

        for (ResolveInfo info : appsList) {
            if (info.activityInfo.packageName.equals(pkgName)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Start another app by package name.
     *
     * @param context Current context.
     * @param pkgName Package name of the target app.
     * @return true: succeeded, false: failed.
     */
    public static boolean startApp(Context context, String pkgName) {
        if (!appExists(context, pkgName)) {
            Slog.e(TAG, "Package name is empty.");
            return false;
        }
        Intent intent = new Intent(context.getPackageManager().getLaunchIntentForPackage(pkgName));
        context.startActivity(intent);
        return true;
    }

    /**
     * Start activity of another app by package name and activity name.
     *
     * @param context  Current context.
     * @param pkgName  Package name of the app.
     * @param activity Target activity name.
     * @return true: succeeded, false: failed.
     */
    public static boolean startActivity(Context context, String pkgName, String activity) {
        if (!appExists(context, pkgName)) {
            Slog.e(TAG, "Package name is empty.");
            return false;
        }

        Intent intent = new Intent();
        ComponentName comp = new ComponentName(pkgName, activity);
        intent.setComponent(comp);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        return true;
    }

    /**
     * Start a service.
     *
     * @param context Current context.
     * @param pkgName Target service package name.
     * @return
     */
    public static boolean startService(Context context, String pkgName) {
        if (!appExists(context, pkgName)) {
            Slog.e(TAG, "Package name is empty.");
            return false;
        }
        Intent intent = new Intent(context.getPackageManager().getLaunchIntentForPackage(pkgName));
        context.startService(intent);
        return true;
    }

    /**
     * install one application
     *
     * @param context the context
     * @param apkFile the apk file
     */
    public static void installApplication(Context context, File apkFile) {
        if (apkFile == null || !apkFile.exists()) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(apkFile),
                "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    /**
     * Whether to open the notification button above API-19
     *
     * @param context
     * @return
     */
    public static boolean isNotificationEnable(Context context) {
//        AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(APP_OPS_SERVICE);
//        ApplicationInfo appInfo = context.getApplicationInfo();
//
//        String pkg = context.getApplicationContext().getPackageName();
//        int uid = appInfo.uid;
//
//        Class appOpsClass = null; /* Context.APP_OPS_MANAGER */
//
//        try {
//            appOpsClass = Class.forName(AppOpsManager.class.getName());
//            Method checkOpNoThrowMethod = appOpsClass.getMethod("checkOpNoThrow", Integer.TYPE, Integer.TYPE, String.class);
//
//            Field opPostNotificationValue = appOpsClass.getDeclaredField("OP_POST_NOTIFICATION");
//            int value = (int) opPostNotificationValue.get(Integer.class);
//            return ((int) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) == AppOpsManager.MODE_ALLOWED);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return true;
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        return notificationManagerCompat.areNotificationsEnabled();
    }

    /**
     * get UUID
     *
     * @return String
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

//    /**
//     * get UniqueID just once chenged next time
//     * @return
//     */
//    public static String getUniqueId() {
//        Context context = DFApplication.getInstance().getApplicationContext();
//        if (!TextUtils.isEmpty(PreferencesUtil.getString(context, AppConstants.UNIQUE_ID))){
//            return PreferencesUtil.getString(context,AppConstants.UNIQUE_ID);
//        }
//        String androidID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
//        String id = androidID + Build.SERIAL;
//        try {
//            String mixID = toMD5(id + getUUID());
//            PreferencesUtil.putString(context,AppConstants.UNIQUE_ID,mixID);
//            return mixID;
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//            PreferencesUtil.putString(context,AppConstants.UNIQUE_ID,id);
//            return id;
//        }
//    }

    /**
     * md5
     *
     * @param text
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static String toMD5(String text) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] digest = messageDigest.digest(text.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            int digestInt = digest[i] & 0xff;
            String hexString = Integer.toHexString(digestInt);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

}
