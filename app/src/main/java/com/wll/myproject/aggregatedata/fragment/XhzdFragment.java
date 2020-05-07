package com.wll.myproject.aggregatedata.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.wll.myproject.aggregatedata.R;
import com.wll.myproject.aggregatedata.adapter.MyXwzdViewPagerAdapter;
import com.wll.myproject.aggregatedata.entity.xhzd.Myxhzd;
import com.wll.myproject.aggregatedata.entity.xhzd.XHzidian;
import com.wll.myproject.aggregatedata.fragment.xhzdfragment.JianMingFragment;
import com.wll.myproject.aggregatedata.fragment.xhzdfragment.JieShiFragment;
import com.wll.myproject.aggregatedata.fragment.xhzdfragment.XiangXiJieShiFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class XhzdFragment extends Fragment {
    Myxhzd myxhzd;
    @BindView(R.id.m_xhzd_viewpager)
    ViewPager mXhzdViewpager;
    MyXwzdViewPagerAdapter myViewPagerAdapter;
    List<Fragment> fragmentList;
    @BindView(R.id.m_xhzd_tablayout)
    TabLayout mXhzdTablayout;

    public XhzdFragment() {
        // Required empty public constructor
    }

    public XhzdFragment(Myxhzd myxhzd) {
        this.myxhzd = myxhzd;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_xhzd, container, false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        ButterKnife.bind( this, view );
        initDate();
        initFragment();
        initViewPager();
        initTab();

    }

    private void initTab() {
        mXhzdTablayout.setupWithViewPager( mXhzdViewpager );
    }

    List<String> mTitles;
    List<String> oneList;
    List<String> twoList;
    List<String> threeList;
    XHzidian xHzidian;

    private void initDate() {
        xHzidian = myxhzd.getResult();
//        oneList = new ArrayList<>();
//        twoList = new ArrayList<>();
//        threeList = new ArrayList<>();
        String[] str = new String[]{xHzidian.getZi(), xHzidian.getPy(), xHzidian.getWubi(), xHzidian.getBushou(), xHzidian.getBihua()};
        oneList = Arrays.asList( str );
        twoList = xHzidian.getJijie();
        threeList = xHzidian.getXiangjie();
        String[] titles = new String[]{"简明", "解析", "详细解析"};
        mTitles = Arrays.asList( titles );

    }

    private void initFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add( new JianMingFragment( oneList ) );
        fragmentList.add( new JieShiFragment( twoList ) );
        fragmentList.add( new XiangXiJieShiFragment( threeList ) );

    }

    FragmentManager fragmentManager;

    private void initViewPager() {
        fragmentManager = getChildFragmentManager();
        myViewPagerAdapter = new MyXwzdViewPagerAdapter( fragmentManager, 0, fragmentList );
        myViewPagerAdapter.setTitles( mTitles );
        mXhzdViewpager.setAdapter( myViewPagerAdapter );

    }
}
