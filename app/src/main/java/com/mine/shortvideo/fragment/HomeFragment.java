package com.mine.shortvideo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.mine.shortvideo.R;
import com.mine.shortvideo.adapter.ContentFragmentAdapter;
import com.mine.shortvideo.constant.Const;
import com.mine.shortvideo.customview.OrientedViewPager;
import com.mine.shortvideo.entity.UserInfoEntity;
import com.mine.shortvideo.transformer.VerticalStackTransformer;
import com.mine.shortvideo.utils.OkHttpUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Request;
import timber.log.Timber;

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
        getUserInfo();
    }
    private void getUserInfo(){
        OkHttpUtils.getAsync(Const.getUserInfoUrl, new OkHttpUtils.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                Timber.e("获取数据失败");
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Timber.e(result);
                StringBuilder sb = new StringBuilder();
                sb.append("{");
                sb.append("\"data\":");
                sb.append(result);
                sb.append("}");
                Gson gson = new Gson();
                UserInfoEntity userInfoEntity = gson.fromJson(sb.toString(),UserInfoEntity.class);
                Timber.e(userInfoEntity.getData().get(0).getField_user_statement().get(0).getValue()+"");
            }
        });
    }

}
