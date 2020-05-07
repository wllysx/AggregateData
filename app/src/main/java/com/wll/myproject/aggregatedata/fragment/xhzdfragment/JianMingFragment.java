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

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class JianMingFragment extends Fragment {
    List<String> mlist;
    @BindView(R.id.m_jiaming_recycler)
    RecyclerView mJiamingRecycler;
    MyXhzdRecyclerAdapter myXhzdRecyclerAdapter;

    public JianMingFragment(List<String> mlist) {
        this.mlist = mlist;
    }

    public JianMingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_jian_ming, container, false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        ButterKnife.bind( this, view );
        initDate();
        initRecyclerView();

    }

    private void initRecyclerView() {
        myXhzdRecyclerAdapter = new MyXhzdRecyclerAdapter( getContext(), mListTags, mlist );
        mJiamingRecycler.setAdapter( myXhzdRecyclerAdapter );
        mJiamingRecycler.setLayoutManager( new LinearLayoutManager( getContext(),LinearLayoutManager.VERTICAL,false ) );
    }

    List<String> mListTags;
    private void initDate() {
        String[] tags = new String[]{"字","拼音","五笔","部首","笔画"};
        mListTags = Arrays.asList( tags );

    }
}
