package com.wll.myproject.aggregatedata.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.ColorInt;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

// 状态栏工具类
public class StatusBarUtil {



    /**
     * 增加View的paddingTop,增加的值为状态栏高度 (智能判断，并设置高度)
     *
     * @param context 目标Context
     * @param view    需要增高的View
     */
    public static void setPaddingTop(Context context, @NonNull View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ViewGroup.LayoutParams lp = view.getLayoutParams();
            if (lp != null && lp.height > 0 && view.getPaddingTop() == 0) {
                lp.height += getStatusBarHeight(context);
                view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + getStatusBarHeight(context),
                        view.getPaddingRight(), view.getPaddingBottom());
            }
        }
    }


    /**
     * 设置状态栏darkMode,字体颜色及icon变黑(目前支持MIUI6以上,Flyme4以上,Android M以上)
     *
     * @param activity 目标activity
     */
    public static void setDarkMode(@NonNull Activity activity) {
        darkMode(activity.getWindow(), true);
    }

    /**
     * 设置状态栏darkMode,字体颜色及icon变亮(目前支持MIUI6以上,Flyme4以上,Android M以上)
     *
     * @param activity 目标activity
     */
    public static void setLightMode(@NonNull Activity activity) {
        darkMode(activity.getWindow(), false);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private static void darkMode(Window window, boolean dark) {
        if (isFlyme4()) {
            setModeForFlyme4(window, dark);
        } else if (isMIUI6()) {
            setModeForMIUI6(window, dark);
        }
        darkModeForM(window, dark);
    }


    /**
     * android 6.0设置字体颜色
     *
     * @param window 目标window
     * @param dark   亮色 or 暗色
     */
    @RequiresApi(Build.VERSION_CODES.M)
    private static void darkModeForM(Window window, boolean dark) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
            if (dark) {
                systemUiVisibility |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {
                systemUiVisibility &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
            window.getDecorView().setSystemUiVisibility(systemUiVisibility);
        }
    }


    /**
     * 设置MIUI6+的状态栏的darkMode,darkMode时候字体颜色及icon
     * http://dev.xiaomi.com/doc/p=4769/
     *
     * @param window 目标window
     * @param dark   亮色 or 暗色
     */
    private static void setModeForMIUI6(Window window, boolean dark) {
        Class<? extends Window> clazz = window.getClass();
        try {
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            int darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(window, dark ? darkModeFlag : 0, darkModeFlag);
        } catch (Exception e) {
            Log.e("StatusBar", "darkIcon: failed");
        }

    }

    /**
     * 设置Flyme4+的状态栏的darkMode,darkMode时候字体颜色及icon
     * http://open-wiki.flyme.cn/index.php?title=Flyme%E7%B3%BB%E7%BB%9FAPI
     *
     * @param window 目标window
     * @param dark   亮色 or 暗色
     */
    private static void setModeForFlyme4(Window window, boolean dark) {
        try {
            WindowManager.LayoutParams lp = window.getAttributes();
            Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
            Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
            darkFlag.setAccessible(true);
            meizuFlags.setAccessible(true);
            int bit = darkFlag.getInt(null);
            int value = meizuFlags.getInt(lp);
            if (dark) {
                value |= bit;
            } else {
                value &= ~bit;
            }
            meizuFlags.setInt(lp, value);
            window.setAttributes(lp);
        } catch (Exception e) {
            Log.e("StatusBar", "darkIcon: failed");
        }
    }


    /**
     * 判断是否Flyme4以上
     */
    private static boolean isFlyme4() {
        return Build.FINGERPRINT.contains("Flyme_OS_4") || Build.VERSION.INCREMENTAL.contains("Flyme_OS_4")
                || Pattern.compile("Flyme OS [4|5]", Pattern.CASE_INSENSITIVE).matcher(Build.DISPLAY).find();
    }

    /**
     * 判断是否MIUI6以上
     */
    private static boolean isMIUI6() {
        try {
            Class<?> clz = Class.forName("android.os.SystemProperties");
            Method mtd = clz.getMethod("get", String.class);
            String val = (String) mtd.invoke(null, "ro.miui.ui.version.name");
            val = val.replaceAll("[vV]", "");
            int version = Integer.parseInt(val);
            return version >= 6;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 计算alpha色值
     *
     * @param color 状态栏颜色值
     * @param alpha 状态栏透明度
     */
    private static int cipherColor(@ColorInt int color, int alpha) {
        if (alpha == 0) {
            return color;
        }
        float a = 1 - alpha / 255f;
        int red = color >> 16 & 0xff;
        int green = color >> 8 & 0xff;
        int blue = color & 0xff;
        red = (int) (red * a + 0.5);
        green = (int) (green * a + 0.5);
        blue = (int) (blue * a + 0.5);
        return 0xff << 24 | red << 16 | green << 8 | blue;
    }


    /**
     * 创建透明View
     *
     * @param viewGroup 目标视图
     * @param color     状态栏颜色值
     * @param alpha     状态栏透明度
     */
    private static void setTranslucentView(ViewGroup viewGroup, @ColorInt int color, @IntRange(from = 0, to = 255) int alpha) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int cipherColor = cipherColor(color, alpha);
            View translucentView = viewGroup.findViewById(android.R.id.custom);
            if (translucentView == null && cipherColor != 0) {
                translucentView = new View(viewGroup.getContext());
                translucentView.setId(android.R.id.custom);
                ViewGroup.LayoutParams params =
                        new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(viewGroup.getContext()));
                viewGroup.addView(translucentView, params);
            }
            if (translucentView != null) {
                translucentView.setBackgroundColor(cipherColor);
            }
        }

    }

    /**
     * 设置根布局参数
     *
     * @param activity         目标activity
     * @param fitSystemWindows 是否预留toolbar的高度
     */
    private static void setRootView(Activity activity, boolean fitSystemWindows) {
        ViewGroup parent = activity.findViewById(android.R.id.content);
        for (int i = 0, count = parent.getChildCount(); i < count; i++) {
            View childView = parent.getChildAt(i);
            if (childView instanceof ViewGroup) {
                childView.setFitsSystemWindows(fitSystemWindows);
                ((ViewGroup) childView).setClipToPadding(fitSystemWindows);
            }
        }
    }

    /**
     * 获取状态栏高度
     *
     * @param context 目标Context
     */
    private static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }



    /**
     * 设置透明状态栏。。
     */

    private static String userAgent = "(Android " + Build.VERSION.RELEASE + ";" + Build.MODEL + ","
            + Build.VERSION.SDK + ""
            + ")";
    private static String needToken;

    /**
     * 通过设置全屏，设置状态栏透明，状态栏是指手机最上方显示中国移动，电量，，等等。
     * @param activity
     */
    //1.设置全屏，不隐藏状态栏， 状态栏设置为透明。
    public static void fullScreen(Activity activity) {
        //如果sdk版本大于4.4则设置状态栏透明化 会导致首页状态栏减少。。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
                //导航栏是手机下方的退出，菜单，主页。
                Window window = activity.getWindow();
                window.clearFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS );
                View decorView = window.getDecorView();

                //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                //设置为这个标志位，状态栏不会被隐藏。。
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                //设置顶部状态栏颜色为全透明。。
                window.addFlags( WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor( Color.TRANSPARENT);
                //导航栏颜色也可以正常为正常白色。（默认灰色）。
                window.setNavigationBarColor(Color.TRANSPARENT);
            } else {
                Window window = activity.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                attributes.flags |= flagTranslucentStatus;
//              attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
        }
    }

    //实现隐藏键盘的功能。。点击最大的布局的空白区域可以掩藏键盘的功能。
    public static  void closepan(Context con, View v){
        //对输入法键盘进行关闭。
        InputMethodManager inputMethodManager = (InputMethodManager) con.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);

    }
    //实现自动显示键盘
    public static void openPan(Context context, View edit) {
        InputMethodManager imm = (InputMethodManager) edit.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
    }


    /**
     * 动态设置状态栏颜色。
     */
    public static void changeStatusBarCololr(Activity activity,int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activity.getWindow().setStatusBarColor(activity.getColor( color ) );
            }
        }
    }

}