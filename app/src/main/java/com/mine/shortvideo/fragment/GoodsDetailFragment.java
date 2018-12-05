package com.mine.shortvideo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.mine.shortvideo.R;
import com.mine.shortvideo.page.Page;
import com.mine.shortvideo.page.PageBehavior;
import com.mine.shortvideo.page.PageContainer;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：created by lun.zhang on 12/5/2018 14:25
 * 邮箱：zhanglun_study@163.com
 */
public class GoodsDetailFragment extends Fragment implements PageBehavior.OnPageChanged {


    @BindView(R.id.pageOne)
    Page pageOne;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.container)
    PageContainer container;
    Unbinder unbinder;
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

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_goods_detail_with_webview, container, false);
        View view = inflater.inflate(R.layout.fragment_goods_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
}

