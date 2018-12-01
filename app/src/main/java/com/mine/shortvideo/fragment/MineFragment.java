package com.mine.shortvideo.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.mine.shortvideo.R;
import com.mine.shortvideo.activity.LoginActivity;
import com.mine.shortvideo.activity.MallListActivity;
import com.mine.shortvideo.activity.MinePlayVideoActivity;
import com.mine.shortvideo.activity.SelectVideoListActivity;
import com.mine.shortvideo.activity.UserSettingActivity;
import com.mine.shortvideo.adapter.UserGameThumbRecyclerViewAdapter;
import com.mine.shortvideo.adapter.UserVideoListAdapter;
import com.mine.shortvideo.constant.Const;
import com.mine.shortvideo.customview.CommomDialog;
import com.mine.shortvideo.entity.MyVideoEntity;
import com.mine.shortvideo.entity.RequestJsonParameter;
import com.mine.shortvideo.entity.UploadFileResultEntity;
import com.mine.shortvideo.entity.UploadUserPicEntity;
import com.mine.shortvideo.entity.UserInfoEntity;
import com.mine.shortvideo.myInterface.MyItemOnClickListener;
import com.mine.shortvideo.myInterface.MyItemOnLongClickListener;
import com.mine.shortvideo.photopicker.PhotoPicker;
import com.mine.shortvideo.utils.Code;
import com.mine.shortvideo.utils.CommonDialogUtils;
import com.mine.shortvideo.utils.MySharedData;
import com.mine.shortvideo.utils.OkHttpUtils;
import com.mine.shortvideo.utils.ToastUtils;
import com.mine.shortvideo.utils.Utils;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
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
    @BindView(R.id.tv_lv)
    TextView tvLv;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_user_id)
    TextView tvUserId;
    @BindView(R.id.tv_signature)
    TextView tvSignature;
    @BindView(R.id.tv_lable)
    TextView tvLable;
    @BindView(R.id.tv_distance)
    TextView tvDistance;
    @BindView(R.id.tv_star)
    TextView tvStar;
    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.tv_like_count)
    TextView tvLikeCount;
    @BindView(R.id.tv_attention_count)
    TextView tvAttentionCount;
    @BindView(R.id.tv_fans_count)
    TextView tvFansCount;
    @BindView(R.id.tv_gamename)
    TextView tvGamename;
    @BindView(R.id.tv_game_platform)
    TextView tvGamePlatform;
    @BindView(R.id.tv_game_level)
    TextView tvGameLevel;
    @BindView(R.id.tv_target_score)
    TextView tvTargetScore;
    @BindView(R.id.tv_reward)
    TextView tvReward;
    @BindView(R.id.tv_booktime)
    TextView tvBooktime;
    @BindView(R.id.img_user_portrait)
    ImageView imgUserPortrait;
    @BindView(R.id.btn_login)
    ImageView btnLogin;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.ll_login)
    LinearLayout llLogin;
    @BindView(R.id.ll_layout)
    LinearLayout llLayout;
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
    private static int DELETE = 1;
    private static int ADD = 0;
    private String userName;
    private List<UserInfoEntity.DataBean.FieldPersonalpicshowBean> personalpicshowBeanList;
    private UserInfoEntity userInfoEntity;
    private List<MyVideoEntity.DataBean> myVideoList;
    private ArrayList<String> listShowPicUrl;
    private List<UploadUserPicEntity.UidBean> uidBeanList;
    private List<UploadUserPicEntity.FieldPersonalpicshowBean> fieldPersonalpicshowBeanList;
    private UploadUserPicEntity uploadUserPicEntity;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        context = getActivity();
        userName = MySharedData.sharedata_ReadString(context, "userId");
        unbinder = ButterKnife.bind(this, rootView);
        dialogUtils = new CommonDialogUtils();
        handler = new MyHandler(getActivity());
        if(uploadUserPicEntity == null){
            uploadUserPicEntity = new UploadUserPicEntity();
        }
        getUserInfo();
        getUserVideoList();
    }

    private void getUserInfo() {
        dialogUtils.showProgress(context);
        OkHttpUtils.getAsync(Const.getUserInfoUrl + userName + "?_format=json", QUESTAUTH, new OkHttpUtils.DataCallBack() {
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
                userInfoEntity = gson.fromJson(sb.toString(), UserInfoEntity.class);
                Timber.e(userInfoEntity.getData().get(0).getField_user_nickname().get(0).getValue() + "");
                userId = userInfoEntity.getData().get(0).getUid().get(0).getValue();
                if(uidBeanList == null){
                    uidBeanList = new ArrayList<>();
                }else {
                    uidBeanList.clear();
                }
                UploadUserPicEntity.UidBean uidBean = new UploadUserPicEntity.UidBean();
                uidBean.setValue(userId);
                uidBeanList.add(uidBean);
                uploadUserPicEntity.setUid(uidBeanList);
                Utils.sendHandleMsg(1, userInfoEntity, handler);
            }
        });
    }

    private void getUserVideoList() {
        OkHttpUtils.getAsync(Const.getUserVideoList + userName + "?_format=json", QUESTNOAUTH, new OkHttpUtils.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                Timber.e("获取数据失败");
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Timber.e("获取用户视频" + result);
                StringBuilder sb = new StringBuilder();
                sb.append("{");
                sb.append("\"data\":");
                sb.append(result);
                sb.append("}");
                Gson gson = new Gson();
                MyVideoEntity myVideoEntity = gson.fromJson(sb.toString(), MyVideoEntity.class);
                Utils.sendHandleMsg(2, myVideoEntity, handler);
            }
        });
    }
    private void deleteUserVideo(String nid){
        dialogUtils.showProgress(context);
        String jsonStr = RequestJsonParameter.deleteUserVideoJsonStr();
        OkHttpUtils.patchJsonAsync(Const.deleteUserVideo + nid +"?_format=json",jsonStr, new OkHttpUtils.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                Utils.sendHandleMsg(4,"删除失败",handler);
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                if(result.length() > 100){
                    Utils.sendHandleMsg(4,"删除成功",handler);
                    getUserVideoList();
                }else {
                    Utils.sendHandleMsg(4,"删除失败",handler);
                }

            }
        });
    }
    private Map<String, String> addParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("vin", "111111");
        return params;
    }

    private void linkFile(int targetId, final int operationType, int removeIndex) {
        String jsonStr;
        String url;
        UploadUserPicEntity.FieldPersonalpicshowBean fieldPersonalpicshowBean =
                new UploadUserPicEntity.FieldPersonalpicshowBean();
        if(fieldPersonalpicshowBean != null && operationType == ADD){
            fieldPersonalpicshowBean.setTarget_id(targetId);
            fieldPersonalpicshowBean.setAlt("alternatice text1");
            fieldPersonalpicshowBeanList.add(fieldPersonalpicshowBean);
            uploadUserPicEntity.setField_personalpicshow(fieldPersonalpicshowBeanList);
        }else if(operationType == DELETE){
            fieldPersonalpicshowBeanList.remove(removeIndex);
            uploadUserPicEntity.setField_personalpicshow(fieldPersonalpicshowBeanList);
        }
        final Gson gson = new Gson();
        jsonStr = gson.toJson(uploadUserPicEntity);
        Timber.e("UploadUserPic  jsonstr -->" + jsonStr);
//        jsonStr = RequestJsonParameter.linkUserShowPic(userId,targetId);
        url = Const.linkFile + userId + "?_format=json";
        OkHttpUtils.patchJsonAsync(url, jsonStr, new OkHttpUtils.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                if(operationType == ADD){
                    Utils.sendHandleMsg(4, "上传失败", handler);
                }else {
                    Utils.sendHandleMsg(4, "删除失败", handler);
                }
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Timber.e("link file result" + result);
                if(result.length() > 100){
                    //获得上传图片成功返回的的图片信息装到用户展示图片的list中
                    UserInfoEntity.DataBean.FieldPersonalpicshowBean personalpicshowBean;
                    JSONObject jsonObject = JSON.parseObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("field_personalpicshow");
                    personalpicshowBeanList.clear();
                    for (int i = 0; i < jsonArray.size() ; i++) {
                        personalpicshowBean = gson.fromJson(jsonArray.getString(i),UserInfoEntity.DataBean.FieldPersonalpicshowBean.class);
                        personalpicshowBeanList.add(personalpicshowBean);
                    }
                    if(operationType == ADD){
                        Utils.sendHandleMsg(5, "上传成功", handler);
                    }else {
                        Utils.sendHandleMsg(5, "删除成功", handler);
                    }
                }else{
                    if(operationType == ADD){
                        Utils.sendHandleMsg(4, "上传失败", handler);
                    }else {
                        Utils.sendHandleMsg(4, "删除失败", handler);
                    }

                }
            }
        });
    }

    private void linkVideoFile(final int targetId) {
        String jsonStr;
        String url;
        jsonStr = RequestJsonParameter.CreateMediaJsonStr(userId, targetId);
        url = Const.CreateMediaVideoUrl;
        OkHttpUtils.postJsonAsync(url, jsonStr, new OkHttpUtils.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Timber.e("上传视频第二步成功返回" + result);
                JSONObject jsonObject = JSON.parseObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("mid");
                JSONObject jsonValue = JSONObject.parseObject(jsonArray.getString(0));
                int mid = jsonValue.getInteger("value");
                Timber.e("link file result mid" + mid);
                OkHttpUtils.postJsonAsync(Const.CreateUserVideoUrl, RequestJsonParameter.CreateUserVideoJsonStr(mid), new OkHttpUtils.DataCallBack() {
                    @Override
                    public void requestFailure(Request request, IOException e) {

                    }

                    @Override
                    public void requestSuccess(String result) throws Exception {
                        if (result.length() > 100) {
                            Utils.sendHandleMsg(4, "上传成功", handler);
                            getUserVideoList();
                        }else {

                            Utils.sendHandleMsg(4, "失败", handler);
                        }
                        Timber.e("上传视频第三步成功返回：" + result);
                    }
                });

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if(Const.isRefreshUserInfo){
            getUserInfo();
            Const.isRefreshUserInfo = false;
        }
        Timber.e("我是onResume----->");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            //上传个人展示图片
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                dialogUtils.showProgress(context, "正在上传，请稍后");
                OkHttpUtils.postFileAsync(Const.upUserShowPicUrl, addParams(), photos.get(0), new OkHttpUtils.DataCallBack() {
                    @Override
                    public void requestFailure(Request request, IOException e) {

                    }

                    @Override
                    public void requestSuccess(String result) throws Exception {
                        Timber.e("result" + result);
                        Gson gson = new Gson();
                        uploadFileResultEntity = gson.fromJson(result, UploadFileResultEntity.class);
                        linkFile(uploadFileResultEntity.getFid().get(0).getValue(),ADD,0);
                    }
                });
            }
        } else if (requestCode == Code.LOCAL_VIDEO_REQUEST && resultCode == Code.LOCAL_VIDEO_RESULT) {
            //上传视频
            String filPaths = data.getStringExtra("path");
            Log.e("video Path", filPaths);
            dialogUtils.showProgress(context, "正在上传，请稍后");
            OkHttpUtils.postFileAsyncNoParameter(Const.uploadVideoUrl, filPaths, new OkHttpUtils.ProgressListener() {
                @Override
                public void onProgress(long totalSize, long currSize, boolean done, int id) {

                    Timber.e("当前上传" + currSize + "----总大小" + totalSize);
                    if (done) {
//                        Utils.sendHandleMsg(4,"上传成功",handler);
                    }
                }
            }, new OkHttpUtils.DataCallBack() {
                @Override
                public void requestFailure(Request request, IOException e) {

                }

                @Override
                public void requestSuccess(String result) throws Exception {

                    Timber.e("上传视频第一步成功返回：" + result);
                    Gson gson = new Gson();
                    uploadFileResultEntity = gson.fromJson(result, UploadFileResultEntity.class);
                    linkVideoFile(uploadFileResultEntity.getFid().get(0).getValue());
                }
            });
        }else if(requestCode == Code.LOGININ_REQUEST && resultCode == Code.LOGININ_RESULT){
            //重新登录
            userName = data.getStringExtra("userName");
            gameThumbRecyclerViewAdapter = null;
            layoutManager1 = null;
            getUserInfo();
            getUserVideoList();
            llLogin.setVisibility(View.GONE);
            llLayout.setVisibility(View.VISIBLE);

        }else if(requestCode == Code.SETTING_REQUEST && resultCode == Code.SETTING_RESULT){
            llLogin.setVisibility(View.VISIBLE);
            llLayout.setVisibility(View.GONE);

        }
    }

    @OnClick({R.id.btn_share, R.id.btn_delete, R.id.btn_private_msg, R.id.btn_attention,
            R.id.btn_more,R.id.btn_login,R.id.tv_login,R.id.btn_mall})
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
                startActivityForResult(intent, Code.SETTING_REQUEST);
                break;
            case R.id.btn_login:
            case R.id.tv_login:
                if (!Utils.isUserLogin(context)) {
                    intent.setClass(context, LoginActivity.class);
                    startActivityForResult(intent, Code.LOGININ_REQUEST);
                }
                break;
            case R.id.btn_mall:
                intent.setClass(context, MallListActivity.class);
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
            if (reference.get() != null) {
                dismissProgress();
                switch (msg.what) {
                    case 1:
                        UserInfoEntity userInfoEntity = (UserInfoEntity) msg.obj;
                        LoadDataToView(userInfoEntity);
                        break;
                    case 2:
                        MyVideoEntity myVideoEntity = (MyVideoEntity) msg.obj;
                        loadVideoView(myVideoEntity);
//                        ToastUtils.show(msg.obj.toString(), Toast.LENGTH_SHORT);
                        break;
                    case 3:
                        ToastUtils.show("", Toast.LENGTH_SHORT);
                        break;
                    case 4:
                        ToastUtils.show(msg.obj.toString(), Toast.LENGTH_SHORT);
                        break;
                    case 5:
                        if(gameThumbRecyclerViewAdapter != null){
                            gameThumbRecyclerViewAdapter.notifyDataSetChanged();
                        }
                        ToastUtils.show(msg.obj.toString(), Toast.LENGTH_SHORT);
                        break;
                }
            }
        }
    }

    private void loadVideoView(final MyVideoEntity myVideoEntity) {
        if (myVideoList == null) {
            myVideoList = new ArrayList<>();
        } else {
            myVideoList.clear();
        }
        myVideoList.addAll(myVideoEntity.getData());
        if (userVideoListAdapter == null && layoutManager2 == null) {
            userVideoListAdapter = new UserVideoListAdapter(context, myVideoList);
            layoutManager2 = new LinearLayoutManager(context);
            layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
            videoRecycler.setLayoutManager(layoutManager2);
            videoRecycler.setAdapter(userVideoListAdapter);
            userVideoListAdapter.setItemOnClickListener(new MyItemOnClickListener() {
                @Override
                public void onItemOnClick(View view, int postion) {
                    if (postion == 0 || myVideoList.size() == 0) {
                        Intent intent = new Intent(getActivity(), SelectVideoListActivity.class);
                        startActivityForResult(intent, Code.LOCAL_VIDEO_REQUEST);
                    } else {
                        Intent intent = new Intent();
                        intent.setClass(context, MinePlayVideoActivity.class);
                        intent.putExtra("VideoIndex", postion);
                        intent.putExtra("myVideoEntity", myVideoEntity);
                        startActivity(intent);
                    }
                }
            });
            userVideoListAdapter.setItemOnLongClickListener(new MyItemOnLongClickListener() {
                @Override
                public void onItemOnLongClick(View view, final int position) {
                    if (position != 0){

                        new CommomDialog(context, R.style.dialog, "确定删除", new CommomDialog.OnCloseListener() {
                            @Override
                            public void onClick(Dialog dialog, boolean confirm) {
                                if (confirm && position != 0) {
                                    deleteUserVideo(myVideoList.get(0).getNid());
                                    dialog.dismiss();
                                }
                            }
                        }).setTitle("删除提示").show();
                    }
                }
            });
        } else {
            userVideoListAdapter.notifyDataSetChanged();
        }

    }

    private void LoadDataToView(UserInfoEntity userInfoEntity) {
        tvNickname.setText(userInfoEntity.getData().get(0).getField_user_nickname().get(0).getValue());
        if (userInfoEntity.getData().get(0).getUser_picture().size() == 0) {
            imgUserPortrait.setImageResource(R.mipmap.img_user_example);
        } else {
            Glide.with(context)
                    .load(userInfoEntity.getData().get(0).getUser_picture().get(0).getUrl())
                    .into(imgUserPortrait);
        }
        tvDistance.setText("未知");
        if (userInfoEntity.getData().get(0).getField_user_level().size() > 0 && !TextUtils.isEmpty(userInfoEntity.getData().get(0).getField_user_level().get(0).getValue())) {
            tvLv.setText("lv" + new BigDecimal(userInfoEntity.getData().get(0).getField_user_level().get(0).getValue()).stripTrailingZeros());
        } else {
            tvLv.setText("lv0");
        }
//        tvLable.setText("♀" +userInfoEntity.getData().get(0).getField_user_age().get(0).getValue() +
//                userInfoEntity.getData().get(0).getField_user_tags().get(0));
        if (userInfoEntity.getData().get(0).getField_user_statement().size() > 0) {
            tvSignature.setText(userInfoEntity.getData().get(0).getField_user_statement().get(0).getValue());
        } else {
            tvSignature.setText("本宝宝太懒，没有留下任何签名");
        }
        if (userInfoEntity.getData().get(0).getField_user_stars().size() > 0) {
            tvStar.setText(userInfoEntity.getData().get(0).getField_user_stars().get(0).getValue() + "");
            rcRate.setRating(userInfoEntity.getData().get(0).getField_user_stars().get(0).getValue());
        } else {
            tvStar.setText("5.0");
            rcRate.setRating(5.0f);
        }
        if (userInfoEntity.getData().get(0).getField_user_point().size() > 0) {
            tvScore.setText(userInfoEntity.getData().get(0).getField_user_point().get(0).getValue() + "");
        } else {
            tvScore.setText("100");
        }
        if (userInfoEntity.getData().get(0).getField_user_game_level().size() > 0) {
            tvGameLevel.setText(userInfoEntity.getData().get(0).getField_user_game_level().get(0).getValue());
        } else {
            tvGameLevel.setText("王者1星");
        }
        if (userInfoEntity.getData().get(0).getField_user_gamename().size() > 0) {
            tvGamename.setText(userInfoEntity.getData().get(0).getField_user_gamename().get(0).getValue());
        }
        if (userInfoEntity.getData().get(0).getField_user_platform().size() > 0) {
            tvGamePlatform.setText(userInfoEntity.getData().get(0).getField_user_platform().get(0).getValue());
        }
        if (userInfoEntity.getData().get(0).getField_personalpicshow().size() >= 0) {
            if (listShowPicUrl == null) {
                listShowPicUrl = new ArrayList<>();
            } else {
                listShowPicUrl.clear();
            }

            if(fieldPersonalpicshowBeanList == null){
                fieldPersonalpicshowBeanList = new ArrayList<>();
            }else {
                fieldPersonalpicshowBeanList.clear();
            }
            if (gameThumbRecyclerViewAdapter == null && layoutManager1 == null) {

                if (personalpicshowBeanList == null) {
                    personalpicshowBeanList = new ArrayList<>();
                } else {
                    personalpicshowBeanList.clear();
                }
                personalpicshowBeanList.addAll(userInfoEntity.getData().get(0).getField_personalpicshow());
                for (int i = 0; i < personalpicshowBeanList.size(); i++) {

                    listShowPicUrl.add(personalpicshowBeanList.get(i).getUrl());
                }
                gameThumbRecyclerViewAdapter = new UserGameThumbRecyclerViewAdapter(context, personalpicshowBeanList);
                layoutManager1 = new LinearLayoutManager(context);
                layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
                pictureRecycler.setLayoutManager(layoutManager1);
                pictureRecycler.setAdapter(gameThumbRecyclerViewAdapter);
                gameThumbRecyclerViewAdapter.setItemOnClickListener(new MyItemOnClickListener() {
                    @Override
                    public void onItemOnClick(View view, int position) {
                        ToastUtils.show("点击了" + position);
                        if (position == personalpicshowBeanList.size() || personalpicshowBeanList.size() == 0) {
                            PhotoPicker.builder()
                                    .setPhotoCount(1)
                                    .setShowCamera(true)
                                    .setShowGif(true)
                                    .setPreviewEnabled(false)
                                    .start(context, MineFragment.this, PhotoPicker.REQUEST_CODE);
                        } else if (listShowPicUrl != null && listShowPicUrl.size() > 0) {
                            PhotoPreview.builder()
                                    .setPhotos(listShowPicUrl)
                                    .setCurrentItem(0)
                                    .start(getActivity());
                        }
                    }
                });
                gameThumbRecyclerViewAdapter.setItemOnLongClickListener(new MyItemOnLongClickListener() {
                    @Override
                    public void onItemOnLongClick(View view, final int position) {
                        if (position != personalpicshowBeanList.size() && personalpicshowBeanList.size() != 0) {

                            new CommomDialog(context, R.style.dialog, "确定删除", new CommomDialog.OnCloseListener() {
                                @Override
                                public void onClick(Dialog dialog, boolean confirm) {
                                    if (confirm) {
                                        linkFile(0,DELETE,position);
                                        dialog.dismiss();
                                    }
                                }
                            }).setTitle("删除提示").show();
                        }
                    }
                });
            } else {
                gameThumbRecyclerViewAdapter.notifyDataSetChanged();
            }
            for (int i = 0; i < personalpicshowBeanList.size(); i++) {
                UploadUserPicEntity.FieldPersonalpicshowBean fieldPersonalpicshowBean =
                        new UploadUserPicEntity.FieldPersonalpicshowBean();
                fieldPersonalpicshowBean.setAlt("alternatice text");
                fieldPersonalpicshowBean.setTarget_id(personalpicshowBeanList.get(i).getTarget_id());
                fieldPersonalpicshowBean.setUrl(personalpicshowBeanList.get(0).getUrl());
                fieldPersonalpicshowBeanList.add(fieldPersonalpicshowBean);
            }
        }

    }

    private void dismissProgress() {
        if (dialogUtils != null) {
            dialogUtils.dismissProgress();
        }
    }

}
