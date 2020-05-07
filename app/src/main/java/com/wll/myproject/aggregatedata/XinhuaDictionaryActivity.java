package com.wll.myproject.aggregatedata;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.wll.myproject.aggregatedata.address.GetRequestAddress;
import com.wll.myproject.aggregatedata.entity.xhzd.Myxhzd;
import com.wll.myproject.aggregatedata.entity.xhzd.XHzidian;
import com.wll.myproject.aggregatedata.fragment.XhzdFragment;
import com.wll.myproject.aggregatedata.util.HttpApi;

import java.io.UnsupportedEncodingException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XinhuaDictionaryActivity extends BaseActivity {

    @BindView(R.id.m_xhcdtv)
    TextView mXhcdtv;
    @BindView(R.id.m_toobar_xhcd)
    Toolbar mToobarXhcd;
    @BindView(R.id.m_searchview)
    SearchView mSearchview;
    XhzdFragment xhzdFragment;
    FragmentManager fragmentManager;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage( msg );
            switch (msg.what) {
                case 0:
                    if (myxhzd != null) {
                        xhzdFragment = new XhzdFragment(myxhzd);
                        fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.m_fragment, xhzdFragment ).commitAllowingStateLoss();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_xinhua_dictionary );
        ButterKnife.bind( this );
        initToolbar();
        initSearch();
    }

    private void initSearch() {
        //搜索图标是否显示在搜索框内
        mSearchview.setIconifiedByDefault( false );
        //设置搜索框展开时是否显示提交按钮，可不显示
        mSearchview.setSubmitButtonEnabled(true);
        mSearchview.setQueryHint("请输入单个中文查询..");
        //点击提交按钮后得到的字符串。
        mSearchview.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //这里获得的是汉字。
                Log.i( "wll11", "" + query );
                //清除焦点，收软键盘
                try {
                    if (query.length() == 1) {
                        getRequest(query);
                    }else{
                        Toast.makeText( activity, "支持单个汉字的查询", Toast.LENGTH_SHORT ).show();
                    }



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

        mToobarXhcd.setNavigationIcon( R.mipmap.backs );
        mToobarXhcd.setNavigationOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
    }

    Myxhzd myxhzd;
    public void getRequest(String query) throws UnsupportedEncodingException {
        initArrays();
        String url = GetRequestAddress.getxhzd( query );
        HttpApi.generalRequest( url, new HttpApi.HttpRequestListener<String>() {
            @Override
            public void requestListener(String result) {
                try {

                    Log.i( "wll11", result );
                    JSONObject jsonObject = JSON.parseObject( result );
                    String json = jsonObject.getString( "result" );
                    XHzidian xHzidian = JSON.parseObject( json, XHzidian.class );
                    Log.i( "wll11", json );
                    //为什么这个是null。。
                    Log.i( "wll11", "what:"+xHzidian.getPingyin());
                    myxhzd= JSON.parseObject( result, Myxhzd.class );
                    Log.i( "wll11", "why:" + myxhzd.getResult().getJijie() );
                    Message message = Message.obtain();
                    message.what = 0;
                    handler.sendMessage( message );

                } catch (JSONException e) {
                    if (e.toString().contains( "网络异常" )) {
                        Toast.makeText( XinhuaDictionaryActivity.this, "网络异常，请检查网络。", Toast.LENGTH_SHORT ).show();
                    } else {
                        Toast.makeText( XinhuaDictionaryActivity.this, "系统繁忙，请稍后重试。", Toast.LENGTH_SHORT ).show();
                    }

                }


            }
        }, param, value, false, "get", "" );

    }
}
