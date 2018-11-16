package com.mine.shortvideo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mine.shortvideo.R;
import com.mine.shortvideo.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：created by lun.zhang on 11/16/2018 15:02
 * 邮箱：zhanglun_study@163.com
 */
public class NotifyActivity extends Activity {
    @BindView(R.id.img_left)
    ImageView imgLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_match_id)
    TextView tvMatchId;
    @BindView(R.id.tv_match_target)
    TextView tvMatchTarget;
    @BindView(R.id.tv_match_reward)
    TextView tvMatchReward;
    @BindView(R.id.tv_match_finish)
    TextView tvMatchFinish;
    @BindView(R.id.tv_confirm_score)
    TextView tvConfirmScore;
    @BindView(R.id.ll_score)
    LinearLayout llScore;
    @BindView(R.id.rb_game_ability)
    RatingBar rbGameAbility;
    @BindView(R.id.tv_game_ability)
    TextView tvGameAbility;
    @BindView(R.id.rb_talk_ability)
    RatingBar rbTalkAbility;
    @BindView(R.id.tv_talk_ability)
    TextView tvTalkAbility;
    @BindView(R.id.rb_reputation)
    RatingBar rbReputation;
    @BindView(R.id.tv_reputation)
    TextView tvReputation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvTitle.setText("通知");
        rbGameAbility.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float value, boolean b) {
                tvGameAbility.setText(value+"");
            }
        });
        rbReputation.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float value, boolean b) {
                tvReputation.setText(value+"");
            }
        });
        rbTalkAbility.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float value, boolean b) {
                tvTalkAbility.setText(value+"");
            }
        });
    }

    @OnClick({R.id.img_left, R.id.tv_match_finish, R.id.tv_confirm_score})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_left:
                finish();
                break;
            case R.id.tv_match_finish:
                if (llScore.getVisibility() == View.GONE) {
                    llScore.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.tv_confirm_score:
                ToastUtils.show("订单完成");
                break;
        }
    }
}
