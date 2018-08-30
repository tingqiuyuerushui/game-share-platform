package com.mine.shortvideo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mine.shortvideo.R;
import com.mine.shortvideo.entity.PublishTaskEntity;
import com.mine.shortvideo.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

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
    private static PublishTaskEntity.DataBean taskListInfo;

    public static CardFragment newInstance(int index, PublishTaskEntity.DataBean taskListInfo) {
        CardFragment fragment = new CardFragment();
        Bundle bdl = new Bundle();
        bdl.putInt(INDEX_KEY, index);
        fragment.setArguments(bdl);
        CardFragment.taskListInfo = taskListInfo;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_card, container, false);
        final Bundle bundle = getArguments();
        unbinder = ButterKnife.bind(this, v);
        if (bundle != null) {
            Glide.with(getActivity())
                    .load("http://www.uaes.site:8088"+taskListInfo.getUser_picture())
                    .into(imgPortrait);
            if(!TextUtils.isEmpty(taskListInfo.getField_user_location())){
                tvDistance.setText("☞ "+taskListInfo.getField_user_location());
            }else{
                tvDistance.setText("未知");
            }
            tvNickname.setText(taskListInfo.getField_user_nickname());
            lvLevel.setText(taskListInfo.getField_user_level());
            tvLable.setText("♀" +taskListInfo.getField_user_age() + taskListInfo.getField_user_tags());
            tvSignature.setText(taskListInfo.getField_user_statement());
            tvStar.setText(taskListInfo.getField_user_stars());
            tvScore.setText(taskListInfo.getField_user_matches());
            tvGameLevel.setText(taskListInfo.getField_playtogether_game_level());
            tvGamename.setText(taskListInfo.getField_playtogether_gamename());
            tvGamePlatform.setText(taskListInfo.getField_playtogether_game_platform());
            tvBooktime.setText("预约"+taskListInfo.getField_pplaytogether_booktime());
        }
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.tv_match)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_match:
                ToastUtils.show("匹配");
                RongIM.getInstance().startConversation(getActivity(), Conversation.ConversationType.PRIVATE, "ios", "ios");
                break;
        }
    }
}
