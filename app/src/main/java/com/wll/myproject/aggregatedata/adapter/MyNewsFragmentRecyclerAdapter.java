package com.wll.myproject.aggregatedata.adapter;
/*
    Create by WLL on 2020/4/27 DATA: 15:39
*/

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wll.myproject.aggregatedata.MyWebViewActivity;
import com.wll.myproject.aggregatedata.R;
import com.wll.myproject.aggregatedata.entity.newsproduct.Product;

import java.util.List;

public class MyNewsFragmentRecyclerAdapter extends RecyclerView.Adapter<MyNewsFragmentRecyclerAdapter.MyViewHolder> {
    Context context;
    List<Product> mList;


    public MyNewsFragmentRecyclerAdapter(Context context, List<Product> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( context ).inflate( R.layout.item_recycler_fragment_news, parent, false );
        MyViewHolder myViewHolder = new MyViewHolder( view );
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product product = mList.get( position );
        holder.mNewTitle.setText( product.getTitle() );
        holder.mNewsDate.setText( product.getDate() );
        holder.mNewsSource.setText( product.getAuthorName() );
        RequestOptions options = new RequestOptions().placeholder( R.mipmap.loading ).error( R.mipmap.error );
        Glide.with( context ).load( product.getThumbnailPicS() ).apply( options ).into( holder.mImage01 );
        Glide.with( context ).load( product.getThumbnailPicS02()).apply( options ).into( holder.mImage02 );
        Glide.with( context ).load( product.getGetThumbnailPicS023() ).apply( options ).into( holder.mImage03 );
        holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (product.getUrl() != null) {
                    Intent intent = new Intent( context, MyWebViewActivity.class );
                    intent.putExtra( "weburl", product.getUrl() );
                    context.startActivity( intent );

                }else{
                    Intent intent = new Intent( context, MyWebViewActivity.class );
                    intent.putExtra( "weburl", product.getUrl() );
                    context.startActivity( intent );
                }

            }
        } );

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setMListProducts(List<Product> mListProducts) {
        mList.clear();
        mList.addAll( mListProducts );
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mImage01;
        ImageView mImage02;
        ImageView mImage03;
        TextView mNewTitle;
        TextView mNewsSource;
        TextView mNewsDate;

        public MyViewHolder(@NonNull View itemView) {
            super( itemView );
            mImage01 = itemView.findViewById( R.id.m_image_01 );
            mImage02 = itemView.findViewById( R.id.m_image_02 );
            mImage03 = itemView.findViewById( R.id.m_image_03 );
            mNewTitle = itemView.findViewById( R.id.m_new_title );
            mNewsSource = itemView.findViewById( R.id.m_news_source );
            mNewsDate = itemView.findViewById( R.id.m_news_date );
        }

    }

}
