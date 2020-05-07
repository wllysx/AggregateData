package com.wll.myproject.aggregatedata.adapter;
/*
    Create by WLL on 2020/4/27 DATA: 14:13
*/

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wll.myproject.aggregatedata.R;
import com.wll.myproject.aggregatedata.entity.Model;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    Context context;
    List<Model> modelList;
    OnWllClickListener onWllClickListener;
    public MyRecyclerViewAdapter(Context context, List<Model> modelList,OnWllClickListener onWllClickListener) {
        this.context = context;
        this.modelList = modelList;
        this.onWllClickListener = onWllClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( context ).inflate( R.layout.item_recycler_main, parent, false );
        MyViewHolder myViewHolder = new MyViewHolder( view );
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText( modelList.get( position ).getTitle() );
        holder.imageView.setImageResource( modelList.get( position ).getViewId() );
        holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onWllClickListener.onClickWLlListener(holder.itemView,position);
            }
        } );


    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        LinearLayout mLL;
        public MyViewHolder(@NonNull View itemView) {
            super( itemView );
            imageView = itemView.findViewById( R.id.m_recycler_main_iv );
            textView = itemView.findViewById( R.id.m_recycler_main_tv );
            mLL = itemView.findViewById( R.id.m_recycler_main_ll );
        }
    }

    public interface OnWllClickListener {
        void onClickWLlListener(View itemView,int position);
    }

}
