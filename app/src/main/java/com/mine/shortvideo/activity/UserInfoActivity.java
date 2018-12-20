package com.mine.shortvideo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.mine.shortvideo.R;
import com.mine.shortvideo.adapter.UserGameThumbRecyclerViewAdapter;
import com.mine.shortvideo.adapter.UserVideoListAdapter;
import com.mine.shortvideo.constant.Const;
import com.mine.shortvideo.entity.MyVideoEntity;
import com.mine.shortvideo.entity.UploadFileResultEntity;
import com.mine.shortvideo.entity.UploadUserPicEntity;
import com.mine.shortvideo.entity.UserInfoEntity;
import com.mine.shortvideo.myInterface.MyItemOnClickListener;
import com.mine.shortvideo.utils.Code;
import com.mine.shortvideo.utils.CommonDialogUtils;
import com.mine.shortvideo.utils.OkHttpUtils;
import com.mine.shortvideo.utils.ToastUtils;
import com.mine.shortvideo.utils.Utils;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.iwf.photopicker.PhotoPreview;
import okhttp3.Request;
import timber.log.Timber;

/**
 * 作者：created by lun.zhang on 12/19/2018 10:04
 * 邮箱：zhanglun_study@163.com
 */
public class UserInfoActivity extends Activity {
    @BindView(R.id.btn_login)
    ImageView btnLogin;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.ll_login)
    LinearLayout llLogin;
    @BindView(R.id.img_user_portrait)
    ImageView imgUserPortrait;
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
    @BindView(R.id.rc_rate)
    RatingBar rcRate;
    @BindView(R.id.tv_star)
    TextView tvStar;
    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.btn_private_msg)
    ImageView btnPrivateMsg;
    @BindView(R.id.btn_attention)
    ImageView btnAttention;
    @BindView(R.id.btn_mall)
    ImageView btnMall;
    @BindView(R.id.btn_more)
    ImageView btnMore;
    @BindView(R.id.tv_like_count)
    TextView tvLikeCount;
    @BindView(R.id.tv_attention_count)
    TextView tvAttentionCount;
    @BindView(R.id.tv_fans_count)
    TextView tvFansCount;
    @BindView(R.id.picture_recycler)
    RecyclerView pictureRecycler;
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
    @BindView(R.id.btn_share)
    ImageView btnShare;
    @BindView(R.id.btn_delete)
    ImageView btnDelete;
    @BindView(R.id.video_recycler)
    RecyclerView videoRecycler;
    @BindView(R.id.ll_layout)
    LinearLayout llLayout;
    @BindView(R.id.img_left)
    ImageView imgLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    private Context context;
    private UserGameThumbRecyclerViewAdapter gameThumbRecyclerViewAdapter;
    private UserVideoListAdapter userVideoListAdapter;
    private LinearLayoutManager layoutManager1;
    private LinearLayoutManager layoutManager2;
    private ArrayList<String> photos;
    private MyHandler handler;
    private CommonDialogUtils dialogUtils;
    private UploadFileResultEntity uploadFileResultEntity;
    private static int userId = 0;
    private String userName;
    private String nickName;
    private List<UserInfoEntity.DataBean.FieldPersonalpicshowBean> personalpicshowBeanList;
    private UserInfoEntity userInfoEntity;
    private List<MyVideoEntity.DataBean> myVideoList;
    private ArrayList<String> listShowPicUrl;
    private List<UploadUserPicEntity.UidBean> uidBeanList;
    private List<UploadUserPicEntity.FieldPersonalpicshowBean> fieldPersonalpicshowBeanList;
    private UploadUserPicEntity uploadUserPicEntity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        context = this;
        dialogUtils = new CommonDialogUtils();
        handler = new MyHandler(UserInfoActivity.this);
        userName = getIntent().getStringExtra("userName");
        nickName = getIntent().getStringExtra("nickName");
        if (uploadUserPicEntity == null) {
            uploadUserPicEntity = new UploadUserPicEntity();
        }
        initView();
        getUserInfo();
        getUserVideoList();
    }

    private void initView() {
        tvTitle.setText(nickName);
    }

    private void getUserInfo() {
        dialogUtils.showProgress(context);
        OkHttpUtils.getAsync(Const.getUserInfoUrl + userName + "?_format=json", Code.QUESTAUTH, new OkHttpUtils.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                Timber.e("获取数据失败");
                Utils.sendHandleMsg(4, "数据获取失败", handler);
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
                userInfoEntity = gson.fromJson(sb.toString(), UserInfoEntity.class);
                Timber.e(userInfoEntity.getData().get(0).getField_user_nickname().get(0).getValue() + "");
                userId = userInfoEntity.getData().get(0).getUid().get(0).getValue();
                if (uidBeanList == null) {
                    uidBeanList = new ArrayList<>();
                } else {
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
        OkHttpUtils.getAsync(Const.getUserVideoList + userName + "?_format=json", Code.QUESTNOAUTH, new OkHttpUtils.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                Timber.e("获取数据失败");
                Utils.sendHandleMsg(4, "数据获取失败", handler);
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
                        if (gameThumbRecyclerViewAdapter != null) {
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
            userVideoListAdapter = new UserVideoListAdapter(context, true, myVideoList);
            layoutManager2 = new LinearLayoutManager(context);
            layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
            videoRecycler.setLayoutManager(layoutManager2);
            videoRecycler.setAdapter(userVideoListAdapter);
            userVideoListAdapter.setItemOnClickListener(new MyItemOnClickListener() {
                @Override
                public void onItemOnClick(View view, int postion) {
                        Intent intent = new Intent();
                        intent.setClass(context, MinePlayVideoActivity.class);
                        intent.putExtra("VideoIndex", postion);
                        intent.putExtra("myVideoEntity", myVideoEntity);
                        startActivity(intent);
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

            if (fieldPersonalpicshowBeanList == null) {
                fieldPersonalpicshowBeanList = new ArrayList<>();
            } else {
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
                gameThumbRecyclerViewAdapter = new UserGameThumbRecyclerViewAdapter(context, false, personalpicshowBeanList);
                layoutManager1 = new LinearLayoutManager(context);
                layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
                pictureRecycler.setLayoutManager(layoutManager1);
                pictureRecycler.setAdapter(gameThumbRecyclerViewAdapter);
                gameThumbRecyclerViewAdapter.setItemOnClickListener(new MyItemOnClickListener() {
                    @Override
                    public void onItemOnClick(View view, int position) {
                        ToastUtils.show("点击了" + position);
                        if (listShowPicUrl != null && listShowPicUrl.size() > 0) {
                            PhotoPreview.builder()
                                    .setPhotos(listShowPicUrl)
                                    .setCurrentItem(position)
                                    .start(UserInfoActivity.this);
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
