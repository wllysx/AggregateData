package com.wll.myproject.aggregatedata.util;
/**
 *将activity存入Map中统一管理。比如当从首页强制跳转到
    Create by WLL on 2020/4/13 DATA: 15:56
*/

import android.app.Activity;

import java.util.HashMap;
import java.util.Map;

public  class ActivityController {
    public static Map<String, Activity> activities = new HashMap<>( );

    public static void addActivity(String name,Activity activity){
        if (activities != null) {
            activities.put( name, activity );
        }
    }

    //移出指定key对应的acitivity
    public static void removeActivity(String activityName) {
        if (activities.get( activityName) != null ) {
            activities.get( activityName ).finish();
            activities.remove( activityName );
        }

    }
}
