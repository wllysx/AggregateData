package com.wll.myproject.aggregatedata.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wll.myproject.aggregatedata.R;
import com.wll.myproject.aggregatedata.adapter.MyNewsFragmentRecyclerAdapter;
import com.wll.myproject.aggregatedata.entity.newsproduct.Product;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment {


    @BindView(R.id.m_productfragment_recycler)
    RecyclerView mRecycler;
    MyNewsFragmentRecyclerAdapter myNewsFragmentRecyclerAdapter;

    int position;
    List<Product> mListProducts;


    public ProductFragment() {
        // Required empty public constructor
    }

    //普通方法。
    public void setMListProducts(List<Product> mListProducts) {
        this.mListProducts.clear();
        this.mListProducts.addAll( mListProducts );
        //调用recyclerview将新数据赋值过去并刷新。会出现空指针，必须将适配器放在构造方法中进行提前初始化。
        if (myNewsFragmentRecyclerAdapter != null) {
            myNewsFragmentRecyclerAdapter.setMListProducts(mListProducts);
        }else{
            Toast.makeText( getContext(), "WHATFUCK!!", Toast.LENGTH_SHORT ).show();
        }
    }

    public ProductFragment(List<Product> mListProducts,int position) {
        this.position = position;
        this.mListProducts = mListProducts;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_product, container, false );
        return view ;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        ButterKnife.bind( this, view );
        myNewsFragmentRecyclerAdapter = new MyNewsFragmentRecyclerAdapter(getContext(),mListProducts);
        if (mListProducts != null) {
            mRecycler.setAdapter( myNewsFragmentRecyclerAdapter );
            mRecycler.setLayoutManager( new LinearLayoutManager( getContext(),LinearLayoutManager.VERTICAL,false  ));
        }
    }



}
