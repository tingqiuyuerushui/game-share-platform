package com.mine.shortvideo.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.List;

/**
 * 作者：created by lun.zhang on 8/8/2018 14:57
 * 邮箱：zhanglun_study@163.com
 */
public class ContentFragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragments;
    private int itemPosition= PagerAdapter.POSITION_UNCHANGED;

    public ContentFragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }

    @Override
    public int getItemPosition(Object object) {
        return  getItemPosition();
    }

    public int getItemPosition() {
        return itemPosition;
    }
    public void setItemPosition(int itemPosition) {
        this.itemPosition = itemPosition;
    }
}
