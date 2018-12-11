package com.mine.shortvideo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mine.shortvideo.R;
import com.mine.shortvideo.activity.GoodsDetailActivity;
import com.mine.shortvideo.adapter.ProductDetailsRecycleViewAdapter;
import com.mine.shortvideo.entity.GoodsDetailsEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：created by lun.zhang on 12/5/2018 14:41
 * 邮箱：zhanglun_study@163.com
 */
public class CommentFragment extends Fragment {


    @BindView(R.id.rv_comment)
    RecyclerView rvComment;
    Unbinder unbinder;
    private ProductDetailsRecycleViewAdapter imgDetailsRecycleViewAdapter;
    private Context context;
    private String[] listDetailsPic;
    private GoodsDetailActivity parentActivity;
    private GoodsDetailsEntity goodsDetailsEntity;
    public CommentFragment() {
        // Required empty public constructor
    }


    private static CommentFragment fragment = null;

    public static CommentFragment newInstance() {
        if (fragment == null) {
            fragment = new CommentFragment();
        }
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods_comment, container, false);
        unbinder = ButterKnife.bind(this, view);
        context = getActivity();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setupDetailImg();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupDetailImg();
    }

    private void setupDetailImg() {
        parentActivity = (GoodsDetailActivity ) getActivity();
        goodsDetailsEntity = parentActivity.getGoodsDetailsEntity();
        String strDetailsPic = goodsDetailsEntity.getData().get(0).getField_c_goods_detail_pics().replaceAll(" ", "");
        listDetailsPic = strDetailsPic.split(",");
        imgDetailsRecycleViewAdapter = new ProductDetailsRecycleViewAdapter(context,listDetailsPic);
        rvComment.setLayoutManager(new LinearLayoutManager(context));
        rvComment.setAdapter(imgDetailsRecycleViewAdapter);


    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
