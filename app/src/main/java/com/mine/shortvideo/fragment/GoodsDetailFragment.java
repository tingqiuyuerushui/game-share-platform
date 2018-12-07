package com.mine.shortvideo.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mine.shortvideo.R;
import com.mine.shortvideo.activity.GoodsDetailActivity;
import com.mine.shortvideo.activity.OrderCommitActivity;
import com.mine.shortvideo.adapter.ProductDetailsPagerAdapter;
import com.mine.shortvideo.adapter.ProductDetailsRecycleViewAdapter;
import com.mine.shortvideo.constant.Const;
import com.mine.shortvideo.entity.GoodsDetailsEntity;
import com.mine.shortvideo.page.Page;
import com.mine.shortvideo.page.PageBehavior;
import com.mine.shortvideo.page.PageContainer;
import com.mine.shortvideo.utils.Code;
import com.mine.shortvideo.utils.CommonDialogUtils;
import com.mine.shortvideo.utils.OkHttpUtils;
import com.mine.shortvideo.utils.ToastUtils;
import com.mine.shortvideo.utils.Utils;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Request;

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
    private MyHandler handler = null;
    private String goodsId = "1";
    private CommonDialogUtils dialogUtils;
    private int homeSize;
    private String[] listHomePic;
    private String[] listDetailsPic;
    private String productName;
    private String productValue;
    private  GoodsDetailActivity parentActivity;
    private GoodsDetailsEntity goodsDetailsEntity;

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
//        handler = new MyHandler(getActivity());
//        dialogUtils = new CommonDialogUtils();
        return view;

    }

    private void initView() {
        rvProductDetails.setFocusable(false);
        parentActivity = (GoodsDetailActivity ) getActivity();
        goodsDetailsEntity = parentActivity.getGoodsDetailsEntity();
        loadDataToView(goodsDetailsEntity);
    }

    private void initNetworkData(){
        dialogUtils.showProgress(context);
        OkHttpUtils.getAsync(Const.getGoodsDetails + goodsId + "?_format=json", Code.QUESTNOAUTH, new OkHttpUtils.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                Utils.sendHandleMsg(2, "数据获取失败", handler);
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                StringBuilder sb = new StringBuilder();
                sb.append("{");
                sb.append("\"data\":");
                sb.append(result);
                sb.append("}");
                Gson gson = new Gson();
                GoodsDetailsEntity goodsDetailsEntity = gson.fromJson(sb.toString(),GoodsDetailsEntity.class);
                Utils.sendHandleMsg(1,goodsDetailsEntity,handler);
            }
        });


    }
    private void setupDetailImg(String[] listDetailsPic) {
        imgDetailsRecycleViewAdapter = new ProductDetailsRecycleViewAdapter(context,listDetailsPic);
        rvProductDetails.setLayoutManager(new LinearLayoutManager(context));
        rvProductDetails.setAdapter(imgDetailsRecycleViewAdapter);


    }

    private void setupViewPager(String [] listHomePic) {
        viewPager.setAdapter(new ProductDetailsPagerAdapter(context, listHomePic));
        viewPager.setCurrentItem(mIndex);
        tvCount.setText(mIndex + 1 + "/" + listHomePic.length);
        viewPager.addOnPageChangeListener(this);
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
//        initNetworkData();
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




    public class MyHandler extends Handler {
        private WeakReference<Activity> reference;

        public MyHandler(Activity activity) {
            reference = new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if(reference.get() != null) {
                dismissProgress();
                switch (msg.what) {
                    case 1:
                        loadDataToView((GoodsDetailsEntity) msg.obj);
                        break;
                    case 2:
                        ToastUtils.show(msg.obj.toString(), Toast.LENGTH_SHORT);
                        break;
                    case 3:
                        ToastUtils.show("",Toast.LENGTH_SHORT);
                        break;
                    case 4:
                        ToastUtils.show(msg.obj.toString(),Toast.LENGTH_SHORT);
                        break;
                }
            }
        }
    }
    private void dismissProgress(){
        if(dialogUtils!=null){
            dialogUtils.dismissProgress();
        }
    }
    private void loadDataToView(GoodsDetailsEntity goodsDetailsEntity) {
        productName = goodsDetailsEntity.getData().get(0).getTitle();
        productValue = new BigDecimal(goodsDetailsEntity.getData().get(0).getField_c_goods_price()).stripTrailingZeros()+"积分";
        tvProductName.setText(productName);
        tvIntegral.setText(productValue);
        String strDetailsPic = goodsDetailsEntity.getData().get(0).getField_c_goods_detail_pics().replaceAll(" ", "");
        String strHomePic = goodsDetailsEntity.getData().get(0).getField_c_good_main_pics().replaceAll(" ", "");
        listDetailsPic = strDetailsPic.split(",");
        listHomePic = strHomePic.split(",");
        setupViewPager(listHomePic);
        homeSize = listHomePic.length;
        setupDetailImg(listDetailsPic);
    }

    @OnClick({R.id.tv_product_name, R.id.btn_exchange_now})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_product_name:

                break;
            case R.id.btn_exchange_now:
                Intent intent = new Intent();
                intent.putExtra("homePic",listHomePic);
                intent.putExtra("productName",productName);
                intent.putExtra("productValue",productValue);
                intent.setClass(context, OrderCommitActivity.class);
                startActivity(intent);
                break;
        }
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
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tvCount.setText(position + 1 + "/" + homeSize);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

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

