package com.mine.shortvideo.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mine.shortvideo.R;
import com.mine.shortvideo.adapter.HomeRecycleViewAdapter;
import com.mine.shortvideo.application.MyApplication;
import com.mine.shortvideo.constant.Const;
import com.mine.shortvideo.customview.carousellayoutmanager.CarouselLayoutManager;
import com.mine.shortvideo.customview.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.mine.shortvideo.customview.carousellayoutmanager.CenterScrollListener;
import com.mine.shortvideo.entity.PublishTaskListEntity;
import com.mine.shortvideo.utils.Code;
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

/**
 * 作者：created by lun.zhang on 12/18/2018 09:08
 * 邮箱：zhanglun_study@163.com
 */
public class HomeV2Fragment extends BaseFragment {
    @BindView(R.id.list_vertical)
    RecyclerView listVertical;
    private Context context;
    private PublishTaskListEntity publishTaskEntity;
    private List<PublishTaskListEntity.DataBean> listTaskList;
    private int page = 0;
    private MyHandler handler = null;
    private HomeRecycleViewAdapter homeRecycleViewAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_v2_home;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        context = getActivity();

//        dialogUtils = new CommonDialogUtils();
        handler = new MyHandler(getActivity());

        initNetWorkData();

    }

    private void initNetWorkData() {
        getToken();
        getPublishTaskList();
    }

    private void getToken() {
        OkHttpUtils.getAsync(Const.getTokenUrl, Code.QUESTNOAUTH, new OkHttpUtils.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                MySharedData.sharedata_WriteString(MyApplication.getAppContext(), "token", result);
                Timber.e("token" + result);
            }
        });
    }

    private void getPublishTaskList() {
//        dialogUtils.showProgress(context);
        OkHttpUtils.getAsync(Const.getTaskListV2 + "&page=" + page, Code.QUESTNOAUTH, new OkHttpUtils.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                Timber.e("请求失败：" + e);
                Utils.sendHandleMsg(2, "数据获取失败", handler);
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Timber.e("publish task=" + result);
                if (result.length() < 5) {
                    Utils.sendHandleMsg(2, "没有数据了", handler);
                    return;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("{");
                sb.append("\"data\":");
                sb.append(result);
                sb.append("}");
                Gson gson = new Gson();
                publishTaskEntity = gson.fromJson(sb.toString(), PublishTaskListEntity.class);
                if (listTaskList == null) {
                    listTaskList = new ArrayList<>();
                }
                for (int i = 0; i < publishTaskEntity.getData().size(); i++) {
                    listTaskList.add(publishTaskEntity.getData().get(i));
                }
                page++;
                Utils.sendHandleMsg(4, "获取数据成功", handler);
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
            if (reference.get() != null) {
                switch (msg.what) {
                    case 1:
                        getPublishTaskList();
                        break;
                    case 2:
                        ToastUtils.show(msg.obj.toString(), Toast.LENGTH_SHORT);
                        break;
                    case 3:
                        ToastUtils.show("", Toast.LENGTH_SHORT);
                        break;
                    case 4:
                        ToastUtils.show(msg.obj.toString(), Toast.LENGTH_SHORT);
                        LoadDataToView();
                        break;
                }
            }
        }
    }

    private void LoadDataToView() {
        if (homeRecycleViewAdapter == null){
            homeRecycleViewAdapter = new HomeRecycleViewAdapter(context,listTaskList);
            listVertical.setAdapter(homeRecycleViewAdapter);
            CarouselLayoutManager carouselLayoutManager = new CarouselLayoutManager(CarouselLayoutManager.VERTICAL, false);
            carouselLayoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
            carouselLayoutManager.setMaxVisibleItems(2);
            listVertical.setLayoutManager(carouselLayoutManager);
            listVertical.setHasFixedSize(true);
            listVertical.addOnScrollListener(new CenterScrollListener());

        }else {
            homeRecycleViewAdapter.notifyDataSetChanged();
        }


    }
}
