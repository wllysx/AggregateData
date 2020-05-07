package com.wll.myproject.aggregatedata;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.wll.myproject.aggregatedata.address.GetRequestAddress;
import com.wll.myproject.aggregatedata.entity.tianqiyubao.MyTqReason;
import com.wll.myproject.aggregatedata.fragment.tqybfragment.WeatherFragment;
import com.wll.myproject.aggregatedata.util.AppUtil;
import com.wll.myproject.aggregatedata.util.HttpApi;

import java.io.UnsupportedEncodingException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherForecastActivity extends BaseActivity {

    @BindView(R.id.m_xhcdtv)
    TextView mXhcdtv;
    @BindView(R.id.m_toobar_xhcd)
    Toolbar mToobarXhcd;
    @BindView(R.id.m_searchview)
    SearchView mSearchview;
    @BindView(R.id.m_fragment)
    FrameLayout mFragment;
    WeatherFragment weatherFragment;
    FragmentManager fragmentManager;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage( msg );
            switch (msg.what) {
                case 0:
                    initFragment();
                    break;
            }
        }
    };

    private void initFragment() {
        weatherFragment = new WeatherFragment(myTqReason);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace( R.id.m_fragment, weatherFragment ).commitAllowingStateLoss();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_weather_forecast );
        AppUtil.fullScreen( this );
        ButterKnife.bind( this );
        initToolbar();
        initSearch();
    }

    private void initSearch() {
        //让键盘上的回车键变成搜索
        mSearchview.setImeOptions( EditorInfo.IME_ACTION_SEARCH);
        //搜索图标是否显示在搜索框内
        mSearchview.setIconifiedByDefault( false );
        //设置搜索框展开时是否显示提交按钮，可不显示
        mSearchview.setSubmitButtonEnabled(true);
        mSearchview.setQueryHint("请输入要查询的地名...");
        //点击提交按钮后得到的字符串。
        mSearchview.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //这里获得的是汉字。
                Log.i( "wll11", "" + query );
                //清除焦点，收软键盘
                try {
                    getRequest(query);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                mSearchview.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        } );
    }



    private void initToolbar() {
        initArrays();
        mToobarXhcd.setNavigationIcon( R.mipmap.backs );
        mToobarXhcd.setNavigationOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
    }

    MyTqReason myTqReason;
    private void getRequest(String query) throws UnsupportedEncodingException {
        String url = GetRequestAddress.getTqyb( query );
        HttpApi.generalRequest( url, new HttpApi.HttpRequestListener<String>() {
            @Override
            public void requestListener(String result) {
                Log.i( "wll11", "tqyb:" + result );
                try {
                    myTqReason = JSON.parseObject( result, MyTqReason.class );
                    if (myTqReason.getReason().equals( "查询成功!" )) {
                        Message message = Message.obtain();
                        message.what = 0;
                        handler.sendMessage( message );

                    }else{
                        Toast.makeText( activity, "未查询到该地区的天气状况", Toast.LENGTH_SHORT ).show();
                    }

                } catch (JSONException e) {
                    if (e.toString().contains( "网络异常" )) {
                        Toast.makeText( WeatherForecastActivity.this, "网络异常，请检查网络。", Toast.LENGTH_SHORT ).show();
                    } else {
                        Toast.makeText( WeatherForecastActivity.this, "系统繁忙，请稍后重试。", Toast.LENGTH_SHORT ).show();
                    }
                }


            }
        } ,param,value,false,"get","");
    }
}
