package com.wll.myproject.aggregatedata.fragment.xhzdfragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wll.myproject.aggregatedata.R;
import com.wll.myproject.aggregatedata.adapter.MyXhzdRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class XiangXiJieShiFragment extends Fragment {

    List<String> mList;
    @BindView(R.id.m_xiangxijieshi_recycler)
    RecyclerView mXiangxijieshiRecycler;
    MyXhzdRecyclerAdapter myXhzdRecyclerAdapter;

    public XiangXiJieShiFragment() {
        // Required empty public constructor
    }

    public XiangXiJieShiFragment(List<String> threeList) {
        this.mList = threeList;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_xiang_xi_jie_shi, container, false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        ButterKnife.bind( this, view );
        initDate();
        initRecycler();
    }

    private void initRecycler() {
        myXhzdRecyclerAdapter = new MyXhzdRecyclerAdapter( getContext(), mListTitles, mList );
        mXiangxijieshiRecycler.setAdapter( myXhzdRecyclerAdapter );
        mXiangxijieshiRecycler.setLayoutManager( new LinearLayoutManager( getContext(), LinearLayoutManager.VERTICAL, false ) );
    }

    List<String> mListTitles;

    private void initDate() {
        mListTitles = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            mListTitles.add( "eg" );
        }
    }
}
