package com.wll.myproject.aggregatedata.adapter;
/*
    Create by WLL on 2020/4/28 DATA: 13:48
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

public class MyXhzdRecyclerAdapter extends RecyclerView.Adapter<MyXhzdRecyclerAdapter.MyViewHolder> {
    Context context;
    List<String> keys;
    List<String> values;

    public MyXhzdRecyclerAdapter(Context context,List<String> keys,List<String> values) {
        this.context = context;
        this.keys = keys;
        this.values = values;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( context ).inflate( R.layout.item_recycler_xhzd_fragment, parent, false );
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView1.setText( keys.get( position )+":" );
        holder.textView2.setText( values.get( position ) );
    }

    @Override
    public int getItemCount() {
        return keys.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;
        TextView textView2;
        public MyViewHolder(@NonNull View itemView) {
            super( itemView );
            textView1 = itemView.findViewById( R.id.m_jianming_tv1 );
            textView2 = itemView.findViewById( R.id.m_jianming_tv2 );
        }
    }

}
