package com.wll.myproject.aggregatedata.adapter;
/*
    Create by WLL on 2020/5/6 DATA: 17:15
*/

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wll.myproject.aggregatedata.R;

import java.util.List;

public class MyCycdRecyclerAdapter extends RecyclerView.Adapter<MyCycdRecyclerAdapter.MyViewHolder> {
    Context context;
    List<String> mTitles;
    List<String> mList;

    public MyCycdRecyclerAdapter(Context context, List<String> mTitles, List<String> mList) {
        this.context = context;
        this.mTitles = mTitles;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( context ).inflate( R.layout.item_recycler_cycd, parent, false );

        return new MyViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView1.setText( mTitles.get( position ) +":");
        holder.textView2.setText( mList.get( position ) );
    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;
        TextView textView2;
        public MyViewHolder(@NonNull View itemView) {
            super( itemView );
            textView1 = itemView.findViewById( R.id.m_cycd_tv1 );
            textView2 = itemView.findViewById( R.id.m_cycd_tv2 );
        }
    }

}
