package com.mine.shortvideo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mine.shortvideo.R;
import com.mine.shortvideo.adapter.MallListRecycleViewAdapter;
import com.mine.shortvideo.constant.Const;
import com.mine.shortvideo.entity.AllGoodsEntity;
import com.mine.shortvideo.myInterface.MyItemOnClickListener;
import com.mine.shortvideo.special.SpacesItemDecoration;
import com.mine.shortvideo.utils.Code;
import com.mine.shortvideo.utils.CommonDialogUtils;
import com.mine.shortvideo.utils.OkHttpUtils;
import com.mine.shortvideo.utils.ToastUtils;
import com.mine.shortvideo.utils.Utils;

import java.io.IOException;
import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;
import timber.log.Timber;

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
    private CommonDialogUtils dialogUtils;
    private MyHandler handler = null;
    private Context context;
    private MallListRecycleViewAdapter mallListRecycleViewAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_list);
        ButterKnife.bind(this);
        context = this;
        handler = new MyHandler(this);
        dialogUtils = new CommonDialogUtils();
        initView();
        initNetworkData();
    }
    private void initView() {
        tvTitle.setText("积分商城");
        mallRecycler.setLayoutManager(new GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false));
//        SpacesItemDecoration decoration = new SpacesItemDecoration(10);
////        mallRecycler.addItemDecoration(decoration);

    }
    private void initNetworkData() {
        dialogUtils.showProgress(context);
        OkHttpUtils.getAsync(Const.getAllGoods, Code.QUESTNOAUTH, new OkHttpUtils.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                Timber.e("获取数据失败");
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
                AllGoodsEntity allGoodsEntity = gson.fromJson(sb.toString(),AllGoodsEntity.class);
                Utils.sendHandleMsg(1,allGoodsEntity,handler);

            }
        });

    }
    private void loadDataToView(final AllGoodsEntity allGoodsEntity) {
        mallListRecycleViewAdapter = new MallListRecycleViewAdapter(context,allGoodsEntity.getData());
        mallRecycler.setAdapter(mallListRecycleViewAdapter);
        mallListRecycleViewAdapter.setItemOnClickListener(new MyItemOnClickListener() {
            @Override
            public void onItemOnClick(View view, int position) {
//                ToastUtils.show("点击了" + position);
                Intent intent = new Intent();
                intent.putExtra("GoodsId",allGoodsEntity.getData().get(position).getField_c_goods_code());
                intent.setClass(context,GoodsDetailActivity.class);
                startActivity(intent);
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
                        loadDataToView((AllGoodsEntity) msg.obj);
                        break;
                    case 2:
                        ToastUtils.show(msg.obj.toString(), Toast.LENGTH_SHORT);
                        break;
                    case 3:
                        ToastUtils.show("",Toast.LENGTH_SHORT);
                        break;
                    case 4:
                        ToastUtils.show(msg.obj.toString(),Toast.LENGTH_SHORT);
//                        LoadDataToView();
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
