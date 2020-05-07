package com.wll.myproject.aggregatedata.adapter;
/*
    Create by WLL on 2020/4/29 DATA: 13:55
*/

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wll.myproject.aggregatedata.R;
import com.wll.myproject.aggregatedata.entity.wannianli.WnlData;

import java.util.ArrayList;
import java.util.List;

public class MyWnlRecyclerAdapter extends RecyclerView.Adapter<MyWnlRecyclerAdapter.MyViewHolder> {
    Context context;
    static List<String> mList = new ArrayList<>();
    WnlData wnlData;
    List<String> mListValue;
    static {
        mList.add("日期:");
        mList.add("生肖:");
        mList.add("事宜:");
        mList.add("假日:");
        mList.add("节日:");
        mList.add("农历:");
        mList.add("农历年:");
        mList.add("告诫:");
        mList.add("星期:");
        mList.add("年月:");
    }

    public MyWnlRecyclerAdapter(Context context,  WnlData wnlData) {
        this.context = context;
        this.wnlData = wnlData;
        mListValue = new ArrayList<>();
        mListValue.add( wnlData.getDate() );
        mListValue.add( wnlData.getAnimalsYear() );
        mListValue.add( wnlData.getAvoid() );
        mListValue.add( wnlData.getDesc() );
        mListValue.add( wnlData.getHoliday() );
        mListValue.add( wnlData.getLunar() );
        mListValue.add( wnlData.getLunarYear() );
        mListValue.add( wnlData.getSuit() );
        mListValue.add( wnlData.getWeekday() );
        mListValue.add( wnlData.getYearMonth() );
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( context ).inflate( R.layout.item_recycler_wnl, parent, false );
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView01.setText( mList.get( position ));
        holder.textView02.setText( mListValue.get( position ));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView01;
        TextView textView02;
        public MyViewHolder(@NonNull View itemView) {
            super( itemView );
            textView01 = itemView.findViewById( R.id.m_wnl_tv01 );
            textView02 = itemView.findViewById( R.id.m_wnl_tv02 );
        }
    }
}
