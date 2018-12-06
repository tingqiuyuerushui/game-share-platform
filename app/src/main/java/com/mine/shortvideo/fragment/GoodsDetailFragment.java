package com.mine.shortvideo.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mine.shortvideo.R;
import com.mine.shortvideo.activity.OrderCommitActivity;
import com.mine.shortvideo.adapter.ProductDetailsPagerAdapter;
import com.mine.shortvideo.adapter.ProductDetailsRecycleViewAdapter;
import com.mine.shortvideo.page.Page;
import com.mine.shortvideo.page.PageBehavior;
import com.mine.shortvideo.page.PageContainer;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 作者：created by lun.zhang on 12/5/2018 14:25
 * 邮箱：zhanglun_study@163.com
 */
public class GoodsDetailFragment extends Fragment implements PageBehavior.OnPageChanged, ViewPager.OnPageChangeListener {


    @BindView(R.id.pageOne)
    Page pageOne;
    @BindView(R.id.container)
    PageContainer container;
    Unbinder unbinder;
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
    @BindView(R.id.rv_product_details)
    RecyclerView rvProductDetails;
    private int[] imgs = {R.mipmap.demo_3, R.mipmap.demo_2, R.mipmap.demo_1};
    private Context context;
    private int mIndex = 0;
    private ProductDetailsRecycleViewAdapter imgDetailsRecycleViewAdapter;
    private ArrayList<String> list = new ArrayList<>();
    private Page.OnScrollListener onScrollListener;

    public GoodsDetailFragment() {
        // Required empty public constructor
        //
    }

    private static GoodsDetailFragment fragment = null;

    public static GoodsDetailFragment newInstance() {
        if (fragment == null) {
            fragment = new GoodsDetailFragment();
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        context = getActivity();
        return view;

    }

    private void initView() {
        rvProductDetails.setFocusable(false);
    }

    private void setupDetailImg() {
        imgDetailsRecycleViewAdapter = new ProductDetailsRecycleViewAdapter(context);
        rvProductDetails.setLayoutManager(new LinearLayoutManager(context));
        rvProductDetails.setAdapter(imgDetailsRecycleViewAdapter);


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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        setupViewPager();
        setupDetailImg();
        container.setOnPageChanged(new PageBehavior.OnPageChanged() {

            @Override
            public void toTop() {
                //位于第一页
            }

            @Override
            public void toBottom() {
                //位于第二页
            }
        });
    }


    boolean isTop = true;

    public void toggle() {
        if (isTop) {
            container.scrollToBottom();
        } else {
            isTop = true;
            container.backToTop();
        }
    }

    @Override
    public void toTop() {
        isTop = true;
    }

    @Override
    public void toBottom() {
        isTop = false;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_product_name, R.id.btn_exchange_now})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_product_name:

                break;
            case R.id.btn_exchange_now:
                Intent intent = new Intent();
                intent.setClass(context, OrderCommitActivity.class);
                startActivity(intent);
                break;
        }
    }
}

