package com.wll.myproject.aggregatedata.util;
/*
    Create by WLL on 2019/12/20 DATA: 13:52
*/

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.util.Random;

public class AppUtil {
    //用户代理：固定版本号+机型+sdk版本号
    private static String userAgent = "(Android " + Build.VERSION.RELEASE + ";" + Build.MODEL + ","
            + Build.VERSION.SDK_INT + ""
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
     * 静态方法，随机生成客户端秘钥。。
     * @return
     */
    public  static  String getsecret(){
        String [] secret={"a","c","D","G","I","z","K","H","r","0","5","9","3","-","V","q","Z","j","S","#"};
        Random rand = new Random();
        String ss="";
        for (int i = 0; i < 20; i++) {
            if (i==0){
                ss=ss+secret[rand.nextInt(secret.length-1)];

            }else {
                ss=ss+","+secret[rand.nextInt(secret.length-1)];

            }
        }
        return ss;
    }

    //判断手机号码的开头是否符合三网规范。
    public static boolean isMobileNO(String mobiles) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String telRegex = "^(13[0-9]|14[45]|15[012356789]|17[0-9]|18[0-9]|19[1]|14[57]|17[013678])[0-9]{8}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)){
            return false;
        }else {
            return mobiles.matches(telRegex);
        }
    }
}
