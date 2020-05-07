package com.wll.myproject.aggregatedata.adapter;
/*
    Create by WLL on 2020/4/27 DATA: 15:20
*/

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * 新华字典的viewpager适配器。
 */

public class MyXwzdViewPagerAdapter extends FragmentStatePagerAdapter {
    List<Fragment> fragmentList;
    List<String> mtitls;

    public MyXwzdViewPagerAdapter(@NonNull FragmentManager fm, int behavior, List<Fragment> fragmentList) {
        super( fm, behavior );
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get( position );
    }
    @Override
    public int getCount() {
        return fragmentList.size();
    }

    //
    public void setTitles(List<String> mtitls) {
        this.mtitls = mtitls;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mtitls == null ? "" : mtitls.get( position );
    }
}
