package com.wll.myproject.aggregatedata.dialog;
/*
    Create by WLL on 2020/4/8 DATA: 14:03
*/

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

public class CodeDialog extends Dialog {
    Context context;
    View view;

    public CodeDialog(@NonNull Context context, View view) {
        super( context);//此处可以加样式的参数id。
        this.context = context;
        this.view = view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        Window window = getWindow();
        //设置dialog居中对齐
        window.setGravity( Gravity.CENTER );
        //绑定自定义视图。
        setContentView( view );

        //设置dialog显示的宽度。
        WindowManager windowManager = ((Activity)context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = display.getWidth()*1;// 设置dialog宽度为屏幕的1:1
        getWindow().setAttributes(lp);
        //设置点击对话框以外的部分的时候对话框消失。
        setCanceledOnTouchOutside(false);//点击外部Dialog消失


    }
}
