package com.mine.shortvideo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mine.shortvideo.R;
import com.mine.shortvideo.adapter.MallListRecycleViewAdapter;
import com.mine.shortvideo.myInterface.MyItemOnClickListener;
import com.mine.shortvideo.special.SpacesItemDecoration;
import com.mine.shortvideo.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：created by lun.zhang on 11/22/2018 09:42
 * 邮箱：zhanglun_study@163.com
 */
public class MallListActivity extends Activity {
    @BindView(R.id.img_left)
    ImageView imgLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.mall_recycler)
    RecyclerView mallRecycler;

    private Context context;
    private MallListRecycleViewAdapter mallListRecycleViewAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_list);
        ButterKnife.bind(this);
        context = this;
        initView();
    }

    private void initView() {
        tvTitle.setText("积分商城");
        mallRecycler.setLayoutManager(new GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false));
//        SpacesItemDecoration decoration = new SpacesItemDecoration(10);
////        mallRecycler.addItemDecoration(decoration);
        mallListRecycleViewAdapter = new MallListRecycleViewAdapter(context);
        mallRecycler.setAdapter(mallListRecycleViewAdapter);
        mallListRecycleViewAdapter.setItemOnClickListener(new MyItemOnClickListener() {
            @Override
            public void onItemOnClick(View view, int position) {
                ToastUtils.show("点击了" + position);
                Intent intent = new Intent();
                intent.setClass(context,GoodsDetailActivity.class);
                startActivity(intent);
            }
        });
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
