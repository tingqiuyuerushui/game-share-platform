package com.mine.shortvideo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mine.shortvideo.R;
import com.mine.shortvideo.activity.LoginActivity;
import com.mine.shortvideo.constant.Const;
import com.mine.shortvideo.entity.PublishTaskListEntity;
import com.mine.shortvideo.utils.ToastUtils;
import com.mine.shortvideo.utils.Utils;

import java.math.BigDecimal;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import timber.log.Timber;

/**
 * 作者：created by lun.zhang on 8/8/2018 14:42
 * 邮箱：zhanglun_study@163.com
 */
public class CardFragment extends Fragment {
    private static final String INDEX_KEY = "index_key";
    @BindView(R.id.rc_rate)
    RatingBar rcRate;
    @BindView(R.id.tv_match)
    TextView tvMatch;
    @BindView(R.id.card_view)
    CardView cardView;
    Unbinder unbinder;
    @BindView(R.id.img_portrait)
    ImageView imgPortrait;
    @BindView(R.id.lv_level)
    TextView lvLevel;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_lable)
    TextView tvLable;
    @BindView(R.id.tv_distance)
    TextView tvDistance;
    @BindView(R.id.tv_star)
    TextView tvStar;
    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.tv_signature)
    TextView tvSignature;
    @BindView(R.id.tv_gamename)
    TextView tvGamename;
    @BindView(R.id.tv_game_platform)
    TextView tvGamePlatform;
    @BindView(R.id.tv_game_level)
    TextView tvGameLevel;
    @BindView(R.id.tv_booktime)
    TextView tvBooktime;
    @BindView(R.id.img_share)
    ImageView imgShare;
    @BindView(R.id.tv_target_score)
    TextView tvTargetStar;
    @BindView(R.id.tv_reward)
    TextView tvReward;
    @BindView(R.id.ll_target)
    LinearLayout llTargetTeach;
    private static List<PublishTaskListEntity.DataBean> taskListInfo;
    private PublishTaskListEntity.DataBean taskInfo;
    private int index = 0;
    private static Handler handler = null;
    private String nickName;
    private String uid;

    public static CardFragment newInstance(int index, List<PublishTaskListEntity.DataBean> taskListInfo, Handler handler) {
        CardFragment fragment = new CardFragment();
        Bundle bdl = new Bundle();
        bdl.putInt(INDEX_KEY, index);
        fragment.setArguments(bdl);
        fragment.taskListInfo = taskListInfo;
        fragment.handler = handler;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_card, container, false);
        final Bundle bundle = getArguments();
        unbinder = ButterKnife.bind(this, v);
        index = bundle.getInt(INDEX_KEY);
        Timber.e("bundle id" + index);
        taskInfo = taskListInfo.get(index - 1);
        uid = taskInfo.getUid();
        if (bundle != null) {
//            Timber.e("头像URL---->"+Const.baseUrl+taskInfo.getUser_picture());
            Glide.with(getActivity())
                    .load(Const.baseUrl + taskInfo.getUser_picture())
                    .into(imgPortrait);
            if (!TextUtils.isEmpty(taskInfo.getField_user_location())) {
                tvDistance.setText("☞ " + taskInfo.getField_user_location());
            } else {
                tvDistance.setText("未知");
            }
            nickName = taskInfo.getField_user_nickname();
            tvNickname.setText(nickName);
            if (!TextUtils.isEmpty(taskInfo.getField_user_level())) {
                lvLevel.setText("lv" + new BigDecimal(taskInfo.getField_user_level()).stripTrailingZeros());
            } else {
                lvLevel.setText("lv0");
            }
            tvLable.setText("♀" + taskInfo.getField_user_age() + " "+taskInfo.getField_user_tags());
            tvSignature.setText(taskInfo.getField_user_statement());
            tvStar.setText(taskInfo.getField_user_stars());
            tvScore.setText(taskInfo.getField_user_matches());
            tvGameLevel.setText(taskInfo.getField_game_level());
            tvGamename.setText(taskInfo.getField_gamename());
            tvGamePlatform.setText(taskInfo.getField_game_platform());
            tvBooktime.setText("预约" + taskInfo.getField_booktime());
            String taskType = taskInfo.getField_type();
            switch (taskType){
                case Const.TASKFREESTR:
                    if(llTargetTeach.getVisibility() == View.VISIBLE){
                        llTargetTeach.setVisibility(View.INVISIBLE);
                    }
                    break;
                case Const.TASKSCORESTR:
                    if(llTargetTeach.getVisibility() == View.INVISIBLE){
                        llTargetTeach.setVisibility(View.VISIBLE);
                    }
                    tvTargetStar.setText("上分 " + taskInfo.getField_target_division() + taskInfo.getField_stars() + "星");
                    tvReward.setText("奖励"+taskInfo.getField_remuneration()+"佩币");
                    break;
                case Const.TASKGOLDSTR:
                    if(llTargetTeach.getVisibility() == View.INVISIBLE){
                        llTargetTeach.setVisibility(View.VISIBLE);
                    }
                    tvTargetStar.setText("赏金 " + taskInfo.getField_target_division() + taskInfo.getField_stars() + "星");
                    tvReward.setText("奖励"+taskInfo.getField_remuneration()+"佩币");
                    break;
                case Const.TASKTUTORIALSTR:
                    if(llTargetTeach.getVisibility() == View.INVISIBLE){
                        llTargetTeach.setVisibility(View.VISIBLE);
                    }
                    tvTargetStar.setText("教学 " + taskInfo.getField_teach_game_numbers() + "局");
                    tvReward.setText("奖励"+taskInfo.getField_remuneration()+"佩币");
                    break;
            }
        }
        return v;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        Timber.e("fragment" + index + "destory");
    }


    @OnClick(R.id.tv_match)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_match:
                ToastUtils.show("匹配");
                if (Utils.isUserLogin(getActivity())) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("task_info", taskInfo);
                    RongIM.getInstance().startConversation(getActivity(), Conversation.ConversationType.PRIVATE, uid, nickName, bundle);
                } else {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }
}
