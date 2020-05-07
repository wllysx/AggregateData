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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.wll.myproject.aggregatedata.adapter.MyCycdRecyclerAdapter;
import com.wll.myproject.aggregatedata.address.PostRequestAddress;
import com.wll.myproject.aggregatedata.entity.cycd.Cycd;
import com.wll.myproject.aggregatedata.util.HttpApi;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CYCDActivity extends BaseActivity {

    @BindView(R.id.m_xhcdtv)
    TextView mXhcdtv;
    @BindView(R.id.m_toobar_cycd)
    Toolbar mToobarCycd;
    @BindView(R.id.m_searchview)
    SearchView mSearchview1;
    @BindView(R.id.m_cycd_recycler)
    RecyclerView mCycdRecycler;

    MyCycdRecyclerAdapter myCycdRecyclerAdapter;
    List<String> mList;
    static List<String> mTitles = new ArrayList<>();

    static {
        String[] str = new String[]{"部首", "头", "拼音", "成语解释", "出自", "造句", "语法", "词语解释", "印证解释", "同义词", "反义词"};
        mTitles = Arrays.asList( str );
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage( msg );
            switch (msg.what) {
                case 0:
                    if (mCycd != null) {
                        mList.clear();
                        mList.add( mCycd.getBushou() );
                        mList.add( mCycd.getHead() );
                        mList.add( mCycd.getPinyin() );
                        mList.add( mCycd.getChengyujs() );
                        mList.add( mCycd.getFrom() );
                        mList.add( mCycd.getExample() );
                        mList.add( mCycd.getYufa() );
                        mList.add( mCycd.getCiyujs() );
                        mList.add( mCycd.getYinzhengjs() );
                        //这里要判断是否为null是因为这是是两个数组。不判断会报空指针。
                        if (mCycd.getTongyi() != null) {
                            mList.add( mCycd.getTongyi().toString() );
                        } else {
                            mList.add( "无" );
                        }
                        if (mCycd.getTongyi() != null) {
                            mList.add( mCycd.getFanyi().toString() + "" );
                        } else {
                            mList.add( "无" );
                        }


                        initRecycler();
                    } else {

                    }
                    break;
                case 1:
                    mList.clear();
                    initRecycler();
                    break;
            }
        }
    };

    private void initRecycler() {
        myCycdRecyclerAdapter = new MyCycdRecyclerAdapter( this, mTitles, mList );
        mCycdRecycler.setAdapter( myCycdRecyclerAdapter );
        mCycdRecycler.setLayoutManager( new LinearLayoutManager( this, LinearLayoutManager.VERTICAL, false ) );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_cycd );
        ButterKnife.bind( this );
        initToolbar();
        mList = new ArrayList<>();
        Toast.makeText( activity, "11", Toast.LENGTH_SHORT ).show();
        initSearch();
    }

    private void initToolbar() {
        mToobarCycd.setNavigationIcon( R.mipmap.backs );
        mToobarCycd.setNavigationOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
    }

    private void initSearch() {
        //搜索图标是否显示在搜索框内
        mSearchview1.setIconifiedByDefault( false );
        //设置搜索框展开时是否显示提交按钮，可不显示
        mSearchview1.setSubmitButtonEnabled( true );
        mSearchview1.setQueryHint( "请输入单个中文查询.." );
        //点击提交按钮后得到的字符串。
        mSearchview1.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //这里获得的是汉字。
                Log.i( "wll11", "" + query );
                //清除焦点，收软键盘
                try {
                    postRequest( query );
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                mSearchview1.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        } );
    }

    Cycd mCycd;

    private void postRequest(String query) throws UnsupportedEncodingException {
        String url = PostRequestAddress.postChengYu( query, "" );
        initArrays();

        HttpApi.generalRequest( url, new HttpApi.HttpRequestListener<String>() {
            @Override
            public void requestListener(String result) {
                try {
                    JSONObject jsonObject = JSON.parseObject( result );
                    Message msg = Message.obtain();
                    if (jsonObject.getString( "reason" ).equals( "success" )) {
                        mCycd = JSON.parseObject( jsonObject.getString( "result" ), Cycd.class );
                        msg.what = 0;
                        handler.sendMessage( msg );

                    } else {
                        Toast.makeText( activity, "查无此语！！", Toast.LENGTH_SHORT ).show();
                        msg.what = 1;
                        handler.sendMessage( msg );
                    }


                } catch (JSONException e) {
                    Toast.makeText( activity, "网络异常，网络繁忙", Toast.LENGTH_SHORT ).show();

                }


            }
        }, param, value, false, "post", "" );
    }
}
