package com.mine.shortvideo.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mine.shortvideo.R;
import com.mine.shortvideo.adapter.ContentFragmentAdapter;
import com.mine.shortvideo.application.MyApplication;
import com.mine.shortvideo.constant.Const;
import com.mine.shortvideo.customview.OrientedViewPager;
import com.mine.shortvideo.entity.PublishTaskEntity;
import com.mine.shortvideo.transformer.VerticalStackTransformer;
import com.mine.shortvideo.utils.CommonDialogUtils;
import com.mine.shortvideo.utils.MySharedData;
import com.mine.shortvideo.utils.OkHttpUtils;
import com.mine.shortvideo.utils.ToastUtils;
import com.mine.shortvideo.utils.Utils;

import java.io.IOException;
import java.lang.ref.WeakReference;
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
    private boolean QUESTAUTH = true;
    private boolean QUESTNOAUTH = false;
    private Context context;
    private int page = 1;
    private PublishTaskEntity publishTaskEntity;
    private List<PublishTaskEntity.DataBean> listTaskList;
    private CommonDialogUtils dialogUtils;
    private MyHandler handler = null;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        context = getActivity();
        handler = new MyHandler(getActivity());
        dialogUtils = new CommonDialogUtils();

        initNetWorkData();

    }
    private void initNetWorkData(){
        getToken();
        getPublishTaskList();
    }
    private void getToken() {
        OkHttpUtils.getAsync(Const.getTokenUrl,QUESTNOAUTH, new OkHttpUtils.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                MySharedData.sharedata_WriteString(MyApplication.getAppContext(),"token",result);
                Timber.e("token" + result);
            }
        });
    }
    private void getPublishTaskList() {
        dialogUtils.showProgress(context);
        OkHttpUtils.getAsync(Const.getTaskList+"&page="+page,QUESTNOAUTH, new OkHttpUtils.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                Timber.e("请求失败：" + e);
            }
            @Override
            public void requestSuccess(String result) throws Exception {
//                Timber.e("publish task=" + result);
                StringBuilder sb = new StringBuilder();
                sb.append("{");
                sb.append("\"data\":");
                sb.append(result);
                sb.append("}");
                Gson gson = new Gson();
                publishTaskEntity = gson.fromJson(sb.toString(),PublishTaskEntity.class);
                if(listTaskList == null){
                    listTaskList = new ArrayList<>();
                }
                for (int i = 0; i < publishTaskEntity.getData().size(); i++) {
                    listTaskList.add(publishTaskEntity.getData().get(i));
                }
//                listTaskList.addAll(publishTaskEntity.getData());

                Utils.sendHandleMsg(4,"获取数据成功",handler);
//                Timber.e("publishTaskEntity==" + publishTaskEntity.getData().get(0).getUser_picture());
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
                        break;
                    case 2:
                        ToastUtils.show(msg.obj.toString(), Toast.LENGTH_SHORT);
                        break;
                    case 3:
                        ToastUtils.show("",Toast.LENGTH_SHORT);
                        break;
                    case 4:
                        ToastUtils.show(msg.obj.toString(),Toast.LENGTH_SHORT);
                        LoadDataToView();
                        break;
                }
            }
        }
    }

    private void LoadDataToView() {
        for (int i = 0; i < listTaskList.size(); i++) {
            mFragments.add(CardFragment.newInstance(i + 1,listTaskList.get(i)));
        }

        mContentFragmentAdapter = new ContentFragmentAdapter(getActivity().getSupportFragmentManager(), mFragments);
        //设置viewpager的方向为竖直
        viewPager.setOrientation(OrientedViewPager.Orientation.VERTICAL);
        //设置limit
        viewPager.setOffscreenPageLimit(3);
        //设置transformer
        viewPager.setPageTransformer(true, new VerticalStackTransformer(getActivity()));
        viewPager.setAdapter(mContentFragmentAdapter);
}

    private void dismissProgress(){
        if(dialogUtils!=null){
            dialogUtils.dismissProgress();
        }
    }
}
