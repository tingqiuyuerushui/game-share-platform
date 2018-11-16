package com.mine.shortvideo.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mine.shortvideo.R;
import com.mine.shortvideo.constant.Const;
import com.mine.shortvideo.entity.PublishTaskListEntity;
import com.mine.shortvideo.entity.RequestJsonParameter;
import com.mine.shortvideo.entity.UserInfoEntity;
import com.mine.shortvideo.utils.CommonDialogUtils;
import com.mine.shortvideo.utils.MySharedData;
import com.mine.shortvideo.utils.OkHttpUtils;
import com.mine.shortvideo.utils.ToastUtils;
import com.mine.shortvideo.utils.Utils;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Request;
import timber.log.Timber;

import static com.umeng.socialize.utils.DeviceConfig.context;

/**
 * 作者：created by lun.zhang on 8/16/2018 14:05
 * 邮箱：zhanglun_study@163.com
 */
public class ConversationActivity extends FragmentActivity {
    @BindView(R.id.img_left)
    ImageView imgLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.img_user_portrait)
    ImageView imgUserPortrait;
    @BindView(R.id.tv_lv)
    TextView tvLv;
    @BindView(R.id.tv_nickname_1)
    TextView tvNickname1;
    @BindView(R.id.tv_user_id)
    TextView tvUserId;
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
    @BindView(R.id.tv_signature)
    TextView tvSignature;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_gamename)
    TextView tvGamename;
    @BindView(R.id.tv_game_platform)
    TextView tvGamePlatform;
    @BindView(R.id.tv_game_level)
    TextView tvGameLevel;
    @BindView(R.id.tv_target_score)
    TextView tvTargetStar;
    @BindView(R.id.tv_reward)
    TextView tvReward;
    @BindView(R.id.tv_booktime)
    TextView tvBooktime;
    @BindView(R.id.ll_top)
    LinearLayout llTop;
    @BindView(R.id.ll_user_info)
    LinearLayout llUserInfo;
    @BindView(R.id.ll_match_info)
    LinearLayout llMatchInfo;
    @BindView(R.id.img_portrait)
    CircleImageView imgPortrait;
    @BindView(R.id.img_my_portrait)
    CircleImageView imgMyPortrait;
    @BindView(R.id.ll_target)
    LinearLayout llTargetTeach;
    @BindView(R.id.ll_both_portrait)
    LinearLayout llBothPortrait;
    private String title;
    private PublishTaskListEntity.DataBean taskInfo;
    private float mPosX, mPosY, mCurPosX, mCurPosY;
    private String nickName;
    private String imgMyPortraitUrl;
    private MyHandler handler;
    private CommonDialogUtils dialogUtils;
    private int userId;
    private Context context;
    private String nid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation);
        context = this;
        ButterKnife.bind(this);
        title = getIntent().getData().getQueryParameter("title");
        Bundle bundle = getIntent().getExtras();
        userId = MySharedData.sharedata_ReadInt(context, "uid");
        dialogUtils = new CommonDialogUtils();
        handler = new MyHandler(ConversationActivity.this);
        if (bundle != null) {
            taskInfo = bundle.getParcelable("task_info");
            initView();
        } else {
            tvTitle.setText(title);
        }

    }

    private void initView() {
        tvTitle.setText(title);
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("匹配");
        llTop.setVisibility(View.VISIBLE);
        llBothPortrait.setVisibility(View.VISIBLE);
        if (taskInfo != null) {
//            Timber.e("头像URL---->"+Const.baseUrl+taskInfo.getUser_picture());
            Glide.with(ConversationActivity.this)
                    .load(Const.baseUrl + taskInfo.getUser_picture())
                    .into(imgUserPortrait);
            Glide.with(ConversationActivity.this)
                    .load(Const.baseUrl + taskInfo.getUser_picture())
                    .into(imgPortrait);
            if (!TextUtils.isEmpty(taskInfo.getField_user_location())) {
                tvDistance.setText("☞ " + taskInfo.getField_user_location());
            } else {
                tvDistance.setText("未知");
            }
            nid = taskInfo.getNid();
            nickName = taskInfo.getField_user_nickname();
            tvNickname.setText(nickName);
            tvNickname1.setText(nickName);
            if (!TextUtils.isEmpty(taskInfo.getField_user_level())) {
                tvLv.setText("lv" + new BigDecimal(taskInfo.getField_user_level()).stripTrailingZeros());
            } else {
                tvLv.setText("lv0");
            }
            tvLable.setText("♀" + taskInfo.getField_user_age() + taskInfo.getField_user_tags());
            tvSignature.setText(taskInfo.getField_user_statement());
            tvStar.setText(taskInfo.getField_user_stars());
            tvScore.setText(taskInfo.getField_user_matches());
            tvGameLevel.setText(taskInfo.getField_game_level());
            tvGamename.setText(taskInfo.getField_gamename());
            tvGamePlatform.setText(taskInfo.getField_game_platform());
            tvBooktime.setText("预约" + taskInfo.getField_booktime());
            String taskType = taskInfo.getField_type();
            switch (taskType) {
                case Const.TASKFREESTR:
                    if (llTargetTeach.getVisibility() == View.VISIBLE) {
                        llTargetTeach.setVisibility(View.INVISIBLE);
                    }
                    break;
                case Const.TASKSCORESTR:
                    if (llTargetTeach.getVisibility() == View.INVISIBLE) {
                        llTargetTeach.setVisibility(View.VISIBLE);
                    }
                    tvTargetStar.setText("上分 " + taskInfo.getField_target_division() + taskInfo.getField_stars() + "星");
                    tvReward.setText("奖励" + taskInfo.getField_remuneration() + "佩币");
                    break;
                case Const.TASKGOLDSTR:
                    if (llTargetTeach.getVisibility() == View.INVISIBLE) {
                        llTargetTeach.setVisibility(View.VISIBLE);
                    }
                    tvTargetStar.setText("赏金 " + taskInfo.getField_target_division() + taskInfo.getField_stars() + "星");
                    tvReward.setText("奖励" + taskInfo.getField_remuneration() + "佩币");
                    break;
                case Const.TASKTUTORIALSTR:
                    if (llTargetTeach.getVisibility() == View.INVISIBLE) {
                        llTargetTeach.setVisibility(View.VISIBLE);
                    }
                    tvTargetStar.setText("教学 " + taskInfo.getField_teach_game_numbers() + "局");
                    tvReward.setText("奖励" + taskInfo.getField_remuneration() + "佩币");
                    break;
            }
        }
        setGestureListener();
    }

    /**
     * 设置上下滑动作监听器
     *
     * @author jczmdeveloper
     */
    @SuppressLint("ClickableViewAccessibility")
    private void setGestureListener() {
        llTop.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        mPosX = event.getX();
                        mPosY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mCurPosX = event.getX();
                        mCurPosY = event.getY();

                        break;
                    case MotionEvent.ACTION_UP:
                        if (mCurPosY - mPosY > 0
                                && (Math.abs(mCurPosY - mPosY) > 25)) {
                            //向下滑動
                            expand();
                        } else if (mCurPosY - mPosY < 0
                                && (Math.abs(mCurPosY - mPosY) > 25)) {
                            //向上滑动
                            collapse();
                        }

                        break;
                }
                return true;
            }

        });
    }
    private void patchTask(){
        String jsonStr = RequestJsonParameter.changeTaskStatus(userId,Const.TASKSTATUSMATCHED);
        OkHttpUtils.patchJsonAsync(Const.changeTaskStatus + nid + "?_format=json", jsonStr, new OkHttpUtils.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                Utils.sendHandleMsg(2,"修改失败",handler);
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Timber.e("修改匹配结果-->" + result);
                if(result.length() > 100){
                    Utils.sendHandleMsg(2,"匹配成功",handler);
                    finish();
                }else {
                    Utils.sendHandleMsg(2,"匹配失败",handler);
                }
            }
        });
    }
    private void collapse() {
        if (llUserInfo.getVisibility() == View.VISIBLE) {
            llUserInfo.setVisibility(View.GONE);
        }
    }

    private void expand() {
        if (llUserInfo.getVisibility() == View.GONE) {
            llUserInfo.setVisibility(View.VISIBLE);
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
                        break;
                    case 2:
                        ToastUtils.show(msg.obj.toString(), Toast.LENGTH_SHORT);
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                }
            }
        }
    }
    private void dismissProgress() {
        if (dialogUtils != null) {
            dialogUtils.dismissProgress();
        }
    }
    @OnClick({R.id.img_left, R.id.tv_title, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_left:
                finish();
                break;
            case R.id.tv_title:
                break;
            case R.id.tv_right:
                patchTask();
                break;
        }
    }
}
