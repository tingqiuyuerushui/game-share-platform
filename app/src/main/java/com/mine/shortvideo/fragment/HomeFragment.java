package com.mine.shortvideo.fragment;

import android.app.Fragment;
import android.os.Bundle;

import com.mine.shortvideo.R;
import com.mine.shortvideo.adapter.ContentFragmentAdapter;
import com.mine.shortvideo.customview.OrientedViewPager;
import com.mine.shortvideo.transformer.VerticalStackTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment {
    @BindView(R.id.view_pager)
    OrientedViewPager viewPager;
    private ContentFragmentAdapter mContentFragmentAdapter;
    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        for (int i = 0; i < 10; i++) {
            mFragments.add(CardFragment.newInstance(i + 1));
        }

        mContentFragmentAdapter = new ContentFragmentAdapter(getActivity().getFragmentManager(), mFragments);
        //设置viewpager的方向为竖直
        viewPager.setOrientation(OrientedViewPager.Orientation.VERTICAL);
        //设置limit
        viewPager.setOffscreenPageLimit(3);
        //设置transformer
        viewPager.setPageTransformer(true, new VerticalStackTransformer(getActivity()));
        viewPager.setAdapter(mContentFragmentAdapter);
    }

}
