package com.wll.myproject.aggregatedata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.wll.myproject.aggregatedata.adapter.MyGridLayoutManager;
import com.wll.myproject.aggregatedata.adapter.MyRecyclerViewAdapter;
import com.wll.myproject.aggregatedata.entity.Model;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.m_recycler_main)
    RecyclerView mRecyclerMain;
    MyRecyclerViewAdapter myRecyclerViewAdapter;
    List<Model> modelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        ButterKnife.bind( this );
        initData();
        initRecyclerView();
    }

    private void initData() {
        modelList = new ArrayList<>();
        modelList.add( new Model(R.mipmap.news,"新闻") );
        modelList.add( new Model(R.mipmap.xhcd,"新华字典") );
        modelList.add( new Model(R.mipmap.tqyb,"天气预报") );
        modelList.add( new Model(R.mipmap.wnl,"万年历") );
        modelList.add( new Model(R.mipmap.cycd,"成语词典") );
    }

    private void initRecyclerView() {
        myRecyclerViewAdapter = new MyRecyclerViewAdapter( this, modelList, new MyRecyclerViewAdapter.OnWllClickListener() {
            @Override
            public void onClickWLlListener(View itemView, int position) {
                switch (position) {
                    //跳转新闻页面
                    case 0:
                        Intent intent = new Intent( MainActivity.this, NewsActivity.class );
                        startActivity( intent );
                        break;
                    case 1:
                        Intent intent1 = new Intent( MainActivity.this, XinhuaDictionaryActivity.class );
                        startActivity( intent1 );
                        break;
                    case 2:
                        Intent intent2 = new Intent( MainActivity.this, WeatherForecastActivity.class );
                        startActivity( intent2 );
                        break;
                    case 3:
                        Intent intent3 = new Intent( MainActivity.this, WNLActivity.class );
                        startActivity( intent3 );
                        break;
                    case 4:
                        Intent intent4 = new Intent( MainActivity.this, CYCDActivity.class );
                        startActivity( intent4 );
                        break;
                }
            }
        } );
        MyGridLayoutManager manager=new MyGridLayoutManager( this,2 );
        //防止卡顿
        manager.setScrollEnabled( false );
        mRecyclerMain.setAdapter( myRecyclerViewAdapter );
        mRecyclerMain.setLayoutManager( manager );

    }
}
