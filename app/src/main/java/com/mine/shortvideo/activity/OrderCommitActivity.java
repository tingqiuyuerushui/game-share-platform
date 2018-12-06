package com.mine.shortvideo.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mine.shortvideo.R;
import com.mine.shortvideo.adapter.ProductDetailsPagerAdapter;
import com.mine.shortvideo.adapter.ProductDetailsRecycleViewAdapter;
import com.mine.shortvideo.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：created by lun.zhang on 12/6/2018 13:05
 * 邮箱：zhanglun_study@163.com
 */
public class OrderCommitActivity extends Activity implements ViewPager.OnPageChangeListener{
    @BindView(R.id.img_left)
    ImageView imgLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.tv_product_name)
    TextView tvProductName;
    @BindView(R.id.btn_exchange_now)
    TextView btnExchangeNow;
    @BindView(R.id.tv_integral)
    TextView tvIntegral;
    @BindView(R.id.tv_exchange_time)
    TextView tvExchangeTime;
    @BindView(R.id.tv_like_count)
    TextView tvLikeCount;
    private int[] imgs = {R.mipmap.demo_3, R.mipmap.demo_2, R.mipmap.demo_1};
    private Context context;
    private int mIndex = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_commit);
        ButterKnife.bind(this);
        context = this;
        initView();
        setupViewPager();
    }

    private void initView() {
        tvTitle.setText("确认订单");
    }


    private void setupViewPager() {
        viewPager.setAdapter(new ProductDetailsPagerAdapter(context, imgs));
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

    @OnClick({R.id.img_left, R.id.btn_exchange_now})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_left:
                finish();
                break;
            case R.id.btn_exchange_now:
                ToastUtils.show("下单成功");
                finish();
                break;
        }
    }
}
