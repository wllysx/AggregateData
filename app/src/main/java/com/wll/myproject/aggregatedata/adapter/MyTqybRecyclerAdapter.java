package com.wll.myproject.aggregatedata.adapter;
/*
    Create by WLL on 2020/4/28 DATA: 17:55
*/

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wll.myproject.aggregatedata.R;
import com.wll.myproject.aggregatedata.entity.tianqiyubao.FutureTQ;

import java.util.List;

public class MyTqybRecyclerAdapter extends RecyclerView.Adapter<MyTqybRecyclerAdapter.MyViewHolder> {
    Context context;
    List<FutureTQ> mList;
    FutureTQ futureTQ;
    public MyTqybRecyclerAdapter(Context context, List<FutureTQ> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( context ).inflate( R.layout.item_recycler_fragment_tqyb, parent, false );
        MyViewHolder myViewHolder = new MyViewHolder( view );
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        futureTQ = mList.get( position );
        holder.textView01.setText( futureTQ.getDate().substring( 5 ) );
        holder.textView02.setText( futureTQ.getTemperature() );
        holder.textView03.setText( futureTQ.getWeather() );
        holder.textView04.setText( futureTQ.getDirect() );
        String weather=futureTQ.getWeather();
        if (weather.equals("多云转晴")) {
            holder.imageView01.setImageResource(R.mipmap.duoyunzhuanqing);
        } else if (weather.equals( "多云" )) {
            holder.imageView01.setImageResource(R.mipmap.duoyun);
        }else if (weather.equals( "晴" )) {
            holder.imageView01.setImageResource(R.mipmap.qing);
        }else if (weather.equals( "阴" )) {
            holder.imageView01.setImageResource(R.mipmap.yin);
        }else if (weather.equals( "中雨转小雨" )) {
            holder.imageView01.setImageResource(R.mipmap.yu);
        }else {
            holder.imageView01.setImageResource(R.mipmap.yu);
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView01;
        TextView textView02;
        TextView textView03;
        TextView textView04;
        ImageView imageView01;

        public MyViewHolder(@NonNull View itemView) {
            super( itemView );
            textView01 = itemView.findViewById( R.id.m_recycler_tv01 );
            textView02 = itemView.findViewById( R.id.m_recycler_tv02 );
            textView03 = itemView.findViewById( R.id.m_recycler_tv03);
            textView04 = itemView.findViewById( R.id.m_recycler_tv04 );
            imageView01 = itemView.findViewById( R.id.m_recycler_iv01 );
        }


    }



}
