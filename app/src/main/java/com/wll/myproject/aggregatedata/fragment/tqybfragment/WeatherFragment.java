package com.wll.myproject.aggregatedata.fragment.tqybfragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wll.myproject.aggregatedata.R;
import com.wll.myproject.aggregatedata.adapter.DividerGridItemDecoration;
import com.wll.myproject.aggregatedata.adapter.MyTqybRecyclerAdapter;
import com.wll.myproject.aggregatedata.entity.tianqiyubao.FutureTQ;
import com.wll.myproject.aggregatedata.entity.tianqiyubao.MyTqReason;
import com.wll.myproject.aggregatedata.entity.tianqiyubao.RealTime;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends Fragment {
    MyTqReason myTqReason;
    RealTime realTime;
    List<FutureTQ> mList;
    @BindView(R.id.m_tq_tv05)
    TextView mTqTv05;
    @BindView(R.id.m_tq_iv01)
    ImageView mTqIv01;
    @BindView(R.id.m_tq_tv01)
    TextView mTqTv01;
    @BindView(R.id.m_tq_tv02)
    TextView mTqTv02;
    @BindView(R.id.m_tq_tv03)
    TextView mTqTv03;
    @BindView(R.id.m_tq_tv04)
    TextView mTqTv04;
    @BindView(R.id.m_recycler_fragment_tqyb)
    RecyclerView mRecyclerFragmentTqyb;
    @BindView(R.id.m_tq_tv07)
    TextView mTqTv07;

    MyTqybRecyclerAdapter myTqybRecyclerAdapter;

    public WeatherFragment() {
        // Required empty public constructor
    }

    public WeatherFragment(MyTqReason myTqReason) {
        this.myTqReason = myTqReason;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_weather, container, false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        ButterKnife.bind( this, view );
        initDate();
        initRecycler();

    }

    private void initRecycler() {
        myTqybRecyclerAdapter = new MyTqybRecyclerAdapter( getContext(), mList );
        mRecyclerFragmentTqyb.setAdapter( myTqybRecyclerAdapter );
        mRecyclerFragmentTqyb.setLayoutManager( new LinearLayoutManager( getContext(), LinearLayoutManager.HORIZONTAL, false ) );
        mRecyclerFragmentTqyb.addItemDecoration( new DividerGridItemDecoration( getContext() ) );
    }

    private void initDate() {
        initDateTime();
        realTime = myTqReason.getResult().getRealTime();
        mList = myTqReason.getResult().getFuture();
        mTqTv05.setText( myTqReason.getResult().getCity() );
        mTqTv07.setText( realTime.getTemperature()+"℃" );
        mTqTv01.setText( realTime.getInfo() );
        mTqTv02.setText( realTime.getDirect() );
        mTqTv03.setText( realTime.getPower() );
        mTqTv04.setText( dateTime);
    }



    String dateTime="";
    public   void initDateTime() {
        final Calendar c = Calendar.getInstance();
        int mYear2 = c.get(Calendar.YEAR);
        int mMonth2 = c.get(Calendar.MONTH)+1;
        int mDay2 = c.get(Calendar.DAY_OF_MONTH);
        Log.i( "mMonth", "" + mMonth2 );
        if (mMonth2 < 10) {
            dateTime = "0" + mMonth2+"月";
        }else{
            dateTime = "" + mMonth2+"月";
        }
        if (mDay2 < 10) {
            dateTime = dateTime + "0" + mDay2+"日";
        }else{
            dateTime = dateTime + mDay2+"日";
        }
    }
}
