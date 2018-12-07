package com.mine.shortvideo.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mine.shortvideo.R;
import com.mine.shortvideo.constant.Const;
import com.mine.shortvideo.entity.GoodsDetailsEntity;
import com.mine.shortvideo.fragment.CommentFragment;
import com.mine.shortvideo.fragment.GoodsDetailFragment;
import com.mine.shortvideo.fragment.ReleativeFragment;
import com.mine.shortvideo.utils.Code;
import com.mine.shortvideo.utils.CommonDialogUtils;
import com.mine.shortvideo.utils.OkHttpUtils;
import com.mine.shortvideo.utils.ToastUtils;
import com.mine.shortvideo.utils.Utils;

import java.io.IOException;
import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Request;
import timber.log.Timber;

/**
 * 作者：created by lun.zhang on 12/5/2018 14:19
 * 邮箱：zhanglun_study@163.com
 */
public class GoodsDetailActivity extends AppCompatActivity {
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private Context context;
    private MinePagerAdapter minePagerAdapter;
    private String goodsId = "1";
    private CommonDialogUtils dialogUtils;
    private MyHandler handler = null;

    public GoodsDetailsEntity getGoodsDetailsEntity() {
        return goodsDetailsEntity;
    }

    public void setGoodsDetailsEntity(GoodsDetailsEntity goodsDetailsEntity) {
        this.goodsDetailsEntity = goodsDetailsEntity;
    }

    private GoodsDetailsEntity goodsDetailsEntity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_goods_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        context = this;
        dialogUtils = new CommonDialogUtils();
        handler = new MyHandler(this);
        initView();
        initNetworkData();
    }

    private void initView() {
        goodsId = getIntent().getStringExtra("GoodsId");
        Timber.e("goodsId="+ goodsId);
        toolbar.setNavigationIcon(R.mipmap.back_arrow);

    }

    private void setUpPageAdapter() {
        minePagerAdapter = new MinePagerAdapter(getSupportFragmentManager());
        viewpager.setOffscreenPageLimit(3);
        viewpager.setAdapter(minePagerAdapter);
        tabs.setupWithViewPager(viewpager);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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
                goodsDetailsEntity = gson.fromJson(sb.toString(),GoodsDetailsEntity.class);
                setGoodsDetailsEntity(goodsDetailsEntity);
                Utils.sendHandleMsg(1,goodsDetailsEntity,handler);
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
                        setUpPageAdapter();
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
    /**
     * ViewPager的PagerAdapter
     */
    public class MinePagerAdapter extends FragmentPagerAdapter {
        Fragment[] fragments = new Fragment[]{GoodsDetailFragment.newInstance(), CommentFragment.newInstance(), ReleativeFragment.newInstance()};
        String[] titles = new String[]{"商品", "详情", "相关"};

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_share:
                ToastUtils.show("分享");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
