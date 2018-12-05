package com.mine.shortvideo.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mine.shortvideo.R;
import com.mine.shortvideo.fragment.CommentFragment;
import com.mine.shortvideo.fragment.GoodsDetailFragment;
import com.mine.shortvideo.fragment.ReleativeFragment;

/**
 * 作者：created by lun.zhang on 12/5/2018 14:19
 * 邮箱：zhanglun_study@163.com
 */
public class GoodsDetailActivity extends AppCompatActivity {
    private TabLayout tabs;
    private ViewPager viewpager;
    private Toolbar toolbar;
    private MinePagerAdapter minePagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_goods_detail);
        tabs = (TabLayout) findViewById(R.id.tabs);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewpager = (ViewPager) findViewById(R.id.viewpager);

//        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.mipmap.back_arrow);

        minePagerAdapter = new MinePagerAdapter(getSupportFragmentManager());
        viewpager.setOffscreenPageLimit(3);
        viewpager.setAdapter(minePagerAdapter);
        tabs.setupWithViewPager(viewpager);
    }


    /**
     * ViewPager的PagerAdapter
     */
    public class MinePagerAdapter extends FragmentPagerAdapter {
        Fragment[] fragments = new Fragment[]{GoodsDetailFragment.newInstance(), CommentFragment.newInstance(), ReleativeFragment.newInstance()};
        String[] titles = new String[]{"商品", "详情", "评价"};

        public MinePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }
    }

}
