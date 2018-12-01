package com.mine.shortvideo.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mine.shortvideo.R;
import com.mine.shortvideo.adapter.ProductDetailsPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：created by lun.zhang on 11/27/2018 15:19
 * 邮箱：zhanglun_study@163.com
 */
public class ProductDetailsActivity extends Activity implements ViewPager.OnPageChangeListener {
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.img_left)
    ImageView imgLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_count)
    TextView tvCount;
    private int[] imgs = {R.mipmap.demo_3, R.mipmap.demo_2, R.mipmap.demo_1};
    private Context context;
    private int mIndex = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(this);
        context = this;
        initView();
        setupViewPager();
    }

    private void initView() {

    }

    private void setupViewPager() {
        viewPager.setAdapter(new ProductDetailsPagerAdapter(this, imgs));
        viewPager.setCurrentItem(mIndex);
        tvCount.setText(mIndex + 1 + "/" + imgs.length);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tvCount.setText(position + 1 + "/" + imgs.length);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @OnClick({R.id.img_left, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_left:
                finish();
                break;
            case R.id.tv_right:
                break;
        }
    }
}
