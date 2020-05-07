package com.wll.myproject.aggregatedata.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.core.widget.NestedScrollView;

/**
 * 滚动回调 获取y的偏移量
 */
public class MyScrollView extends NestedScrollView {

    public MyScrollView(Context context) {
        super(context, null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    private OnScrollChangedListener mOnScrollListener;

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mOnScrollListener != null) {
            mOnScrollListener.onScrollChanged(this, l, t, oldl, oldt);
        }
    }

    public void setOnScrollChangedListener(OnScrollChangedListener listener) {
        mOnScrollListener = listener;
    }

    public interface OnScrollChangedListener {
        void onScrollChanged(NestedScrollView who, int l, int t, int oldl, int oldt);
    }
}
