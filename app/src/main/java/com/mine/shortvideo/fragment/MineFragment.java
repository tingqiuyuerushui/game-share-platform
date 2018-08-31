package com.mine.shortvideo.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mine.shortvideo.R;
import com.mine.shortvideo.activity.MinePlayVideoActivity;
import com.mine.shortvideo.activity.SelectVideoListActivity;
import com.mine.shortvideo.activity.UserSettingActivity;
import com.mine.shortvideo.adapter.UserGameThumbRecyclerViewAdapter;
import com.mine.shortvideo.adapter.UserVideoListAdapter;
import com.mine.shortvideo.constant.Const;
import com.mine.shortvideo.customview.CommomDialog;
import com.mine.shortvideo.entity.LinkFileRequestEntity;
import com.mine.shortvideo.entity.RequestJsonParameter;
import com.mine.shortvideo.entity.UploadFileResultEntity;
import com.mine.shortvideo.entity.UserInfoEntity;
import com.mine.shortvideo.myInterface.MyItemOnClickListener;
import com.mine.shortvideo.photopicker.PhotoPicker;
import com.mine.shortvideo.utils.Code;
import com.mine.shortvideo.utils.CommonDialogUtils;
import com.mine.shortvideo.utils.GetProgressDialog;
import com.mine.shortvideo.utils.OkHttpUtils;
import com.mine.shortvideo.utils.ToastUtils;
import com.mine.shortvideo.utils.Utils;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.iwf.photopicker.PhotoPreview;
import okhttp3.Request;
import timber.log.Timber;

import static android.app.Activity.RESULT_OK;


public class MineFragment extends BaseFragment {
    @BindView(R.id.picture_recycler)
    RecyclerView pictureRecycler;
    @BindView(R.id.video_recycler)
    RecyclerView videoRecycler;
    @BindView(R.id.btn_share)
    ImageView btnShare;
    @BindView(R.id.btn_delete)
    ImageView btnDelete;
    @BindView(R.id.rc_rate)
    RatingBar rcRate;
    @BindView(R.id.btn_private_msg)
    ImageView btnPrivateMsg;
    @BindView(R.id.btn_attention)
    ImageView btnAttention;
    @BindView(R.id.btn_more)
    ImageView btnMore;
    private Context context;
    private UserGameThumbRecyclerViewAdapter gameThumbRecyclerViewAdapter;
    private UserVideoListAdapter userVideoListAdapter;
    private LinearLayoutManager layoutManager1;
    private LinearLayoutManager layoutManager2;
    private ArrayList<String> photos;
    private MyHandler handler;
    private CommonDialogUtils dialogUtils;
    private UploadFileResultEntity uploadFileResultEntity;
    private boolean QUESTAUTH = true;
    private boolean QUESTNOAUTH = false;
    private static int userId = 0;
    private static int VIDEOTYPE = 1;
    private static int IMGTYPE = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        context = getActivity();
        unbinder = ButterKnife.bind(this, rootView);
        dialogUtils = new CommonDialogUtils();
        handler = new MyHandler(getActivity());
        gameThumbRecyclerViewAdapter = new UserGameThumbRecyclerViewAdapter(context);
        layoutManager1 = new LinearLayoutManager(context);
        layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        pictureRecycler.setLayoutManager(layoutManager1);
        pictureRecycler.setAdapter(gameThumbRecyclerViewAdapter);
        gameThumbRecyclerViewAdapter.setItemOnClickListener(new MyItemOnClickListener() {
            @Override
            public void onItemOnClick(View view, int postion) {
                ToastUtils.show("点击了" + postion);
                if (postion == 3) {
                    PhotoPicker.builder()
                            .setPhotoCount(9)
                            .setShowCamera(true)
                            .setShowGif(true)
                            .setPreviewEnabled(false)
                            .start(context, MineFragment.this, PhotoPicker.REQUEST_CODE);
                } else if (photos != null && photos.size() > 0) {
                    PhotoPreview.builder()
                            .setPhotos(photos)
                            .setCurrentItem(0)
                            .start(getActivity());
                }
            }
        });
        userVideoListAdapter = new UserVideoListAdapter(context);
        layoutManager2 = new LinearLayoutManager(context);
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        videoRecycler.setLayoutManager(layoutManager2);
        videoRecycler.setAdapter(userVideoListAdapter);
        userVideoListAdapter.setItemOnClickListener(new MyItemOnClickListener() {
            @Override
            public void onItemOnClick(View view, int postion) {
                if (postion == 3) {
                    Intent intent = new Intent(getActivity(), SelectVideoListActivity.class);
                    startActivityForResult(intent, Code.LOCAL_VIDEO_REQUEST);
                } else {
                    Intent intent = new Intent();
                    intent.setClass(context, MinePlayVideoActivity.class);
                    intent.putExtra("VideoIndex", postion);
                    startActivity(intent);
                }
            }
        });
        getUserInfo();
        getUserVideoList();
    }

    private void getUserInfo(){
        OkHttpUtils.getAsync(Const.getUserInfoUrl+"17839997702"+"?_format=json", QUESTAUTH,new OkHttpUtils.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                Timber.e("获取数据失败");
            }

            @Override
            public void requestSuccess(String result) throws Exception {
//                Timber.e(result);
                StringBuilder sb = new StringBuilder();
                sb.append("{");
                sb.append("\"data\":");
                sb.append(result);
                sb.append("}");
                Gson gson = new Gson();
                UserInfoEntity userInfoEntity = gson.fromJson(sb.toString(),UserInfoEntity.class);
                Timber.e(userInfoEntity.getData().get(0).getField_user_nickname().get(0).getValue()+"");
                userId = userInfoEntity.getData().get(0).getUid().get(0).getValue();
            }
        });
    }
    private void getUserVideoList(){
        OkHttpUtils.getAsync(Const.getUserVideoList+"17839997702"+"?_format=json", QUESTAUTH,new OkHttpUtils.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                Timber.e("获取数据失败");
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Timber.e(result);
//                StringBuilder sb = new StringBuilder();
//                sb.append("{");
//                sb.append("\"data\":");
//                sb.append(result);
//                sb.append("}");
//                Gson gson = new Gson();
//                UserInfoEntity userInfoEntity = gson.fromJson(sb.toString(),UserInfoEntity.class);
//                Timber.e(userInfoEntity.getData().get(0).getField_user_nickname().get(0).getValue()+"");
//                userId = userInfoEntity.getData().get(0).getUid().get(0).getValue();
            }
        });
    }

    private Map<String, String> addParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("vin", "111111");
        return params;
    }
    private void linkFile(int targetId,int fileType){
        String jsonStr;
        if(fileType == VIDEOTYPE){
            jsonStr = RequestJsonParameter.linkVideoFile(targetId);
        }else {
            jsonStr = RequestJsonParameter.linkFile(targetId);
        }
        OkHttpUtils.patchJsonAsync(Const.linkFile + userId + "?_format=json", jsonStr, new OkHttpUtils.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Timber.e("link file result" + result);
                Utils.sendHandleMsg(4,"上传成功",handler);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                dialogUtils.showProgress(context,"正在上传，请稍后");
                OkHttpUtils.postFileAsync(Const.uploadUrl, addParams(), photos.get(0), new OkHttpUtils.DataCallBack() {
                    @Override
                    public void requestFailure(Request request, IOException e) {

                    }

                    @Override
                    public void requestSuccess(String result) throws Exception {
                        Timber.e("result" + result);
                        Gson gson = new Gson();
                        uploadFileResultEntity = gson.fromJson(result,UploadFileResultEntity.class);
                        linkFile(uploadFileResultEntity.getFid().get(0).getValue(),IMGTYPE);
                        Utils.sendHandleMsg(4,"上传成功",handler);
                    }
                });
            }
        } else if (requestCode == Code.LOCAL_VIDEO_REQUEST && resultCode == Code.LOCAL_VIDEO_RESULT) {
            String filPaths = data.getStringExtra("path");
            Log.e("video Path", filPaths);
            dialogUtils.showProgress(context,"正在上传，请稍后");
            OkHttpUtils.postFileAsyncNoParameter(Const.uploadVideoUrl, filPaths, new OkHttpUtils.ProgressListener() {
                @Override
                public void onProgress(long totalSize, long currSize, boolean done, int id) {

                    Timber.e("当前上传"+currSize + "----总大小"+totalSize);
                    if(done){
//                        Utils.sendHandleMsg(4,"上传成功",handler);
                    }
                }
            }, new OkHttpUtils.DataCallBack() {
                @Override
                public void requestFailure(Request request, IOException e) {

                }

                @Override
                public void requestSuccess(String result) throws Exception {

                    Timber.e("上传视频成功返回："+result);
                    Gson gson = new Gson();
                    uploadFileResultEntity = gson.fromJson(result,UploadFileResultEntity.class);
                    linkFile(uploadFileResultEntity.getFid().get(0).getValue(),VIDEOTYPE);
                }
            });
        }
    }

    @OnClick({R.id.btn_share, R.id.btn_delete,R.id.btn_private_msg, R.id.btn_attention, R.id.btn_more})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_share:
                break;
            case R.id.btn_delete:
                new CommomDialog(context, R.style.dialog, "确定删除", new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm) {
                            Toast.makeText(context, "点击确定", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                }).setTitle("删除提示").show();
                break;
            case R.id.btn_private_msg:
                break;
            case R.id.btn_attention:
                break;
            case R.id.btn_more:
                intent.setClass(context, UserSettingActivity.class);
                startActivity(intent);
                break;
        }
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
                        ToastUtils.show(msg.obj.toString(),Toast.LENGTH_SHORT);
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

}
