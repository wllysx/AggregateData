package com.wll.myproject.aggregatedata;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.dsw.calendar.component.MonthView;
import com.dsw.calendar.views.CircleCalendarView;
import com.wll.myproject.aggregatedata.adapter.MyWnlRecyclerAdapter;
import com.wll.myproject.aggregatedata.address.GetRequestAddress;
import com.wll.myproject.aggregatedata.entity.wannianli.WnlData;
import com.wll.myproject.aggregatedata.entity.wannianli.WnlReason;
import com.wll.myproject.aggregatedata.util.AppUtil;
import com.wll.myproject.aggregatedata.util.HttpApi;
import com.wll.myproject.aggregatedata.util.StatusBarUtil;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WNLActivity extends BaseActivity {

    @BindView(R.id.m_toobar_wnl)
    Toolbar mToobarWnl;
    @BindView(R.id.circleMonthView)
    CircleCalendarView circleMonthView;

    @BindView(R.id.m_recycler_wnl)
    RecyclerView mRecyclerWnl;
    WnlReason wnlReason;
    WnlData wnlData;
    MyWnlRecyclerAdapter myWnlRecyclerAdapter;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage( msg );
            switch (msg.what) {
                case 0:
                    wnlData = wnlReason.getResult().getData();
                    initRecyclerView();
                    break;
            }

        }
    };

    private void initRecyclerView() {
        myWnlRecyclerAdapter = new MyWnlRecyclerAdapter( this, wnlData );
        mRecyclerWnl.setAdapter( myWnlRecyclerAdapter );
        mRecyclerWnl.setLayoutManager( new LinearLayoutManager( this, LinearLayoutManager.VERTICAL, false ) );
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_wnl );
        AppUtil.fullScreen( this );
        StatusBarUtil.setDarkMode( this );
        ButterKnife.bind( this );
        initToolbar();
        initMonthView();
    }

    //发现新大陆。
    TextView textViewYear;
    TextView textViewMonth;
    MonthView.IDateClick iDateClick;

    private void initMonthView() {
        Calendar calendar = Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH)+1;
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        textViewYear = circleMonthView.findViewById( R.id.year );
        textViewMonth = circleMonthView.findViewById( R.id.month );
        textViewYear.setText( Calendar.getInstance().get( Calendar.YEAR ) + "年" );
        textViewMonth.setText( Calendar.getInstance().get( Calendar.MONTH ) + 1 + "月" );
        requestDate( year + "-" + month + "-" + day );
        textViewYear.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击弹出对话框。
                showDatePickDlg();

            }
        } );
        textViewMonth.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击弹出对话框。
                showDatePickDlg();

            }
        } );
        iDateClick = new MonthView.IDateClick() {
            @Override
            public void onClickOnDate(int year, int month, int day) {
                Toast.makeText( WNLActivity.this, "点击了" + year + "-" + month + "-" + day, Toast.LENGTH_SHORT ).show();
                requestDate( year + "-" + month + "-" + day );
            }
        };
        circleMonthView.setDateClick( iDateClick );

    }

    private void initToolbar() {

        mToobarWnl.setNavigationIcon( R.mipmap.backs );
        mToobarWnl.setNavigationOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
    }

    //调用系统默认日历。
    protected void showDatePickDlg() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog( this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Toast.makeText( activity, year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日", Toast.LENGTH_SHORT ).show();
                textViewYear.setText( year + "年" );
                textViewMonth.setText( monthOfYear + 1 + "月" );
                requestDate( year + "-" + (monthOfYear+1) + "-" + dayOfMonth );


            }
        }, calendar.get( Calendar.YEAR ), calendar.get( Calendar.MONTH ), calendar.get( Calendar.DAY_OF_MONTH ) );
        datePickerDialog.show();


    }

    //进行网络访问请求。
    private void requestDate(String dateTime) {
        initArrays();
        String url = GetRequestAddress.getDateTime( dateTime );
        HttpApi.generalRequest( url, new HttpApi.HttpRequestListener<String>() {
            @Override
            public void requestListener(String result) {
                try {
                    Log.i( "wll11", "why:" + result );
                    wnlReason = JSON.parseObject( result, WnlReason.class );
                    if (wnlReason.getReason().equals( "Success" )) {
                        Message message = Message.obtain();
                        message.what = 0;
                        handler.sendMessage( message );
                    }else{
                        Toast.makeText( activity, "获取失败", Toast.LENGTH_SHORT ).show();
                    }

                } catch (JSONException e) {
                    if (e.toString().contains( "网络异常" )) {
                        Toast.makeText( WNLActivity.this, "网络异常，请检查网络。", Toast.LENGTH_SHORT ).show();
                    } else {
                        Toast.makeText( WNLActivity.this, "系统繁忙，请稍后重试。", Toast.LENGTH_SHORT ).show();
                    }
                    finish();
                }

            }
        }, param, value, false, "get", "" );


    }


}
