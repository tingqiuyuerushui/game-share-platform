package com.mine.shortvideo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
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
import com.mine.shortvideo.constant.Const;
import com.mine.shortvideo.entity.PublishTaskListEntity;

import java.math.BigDecimal;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：created by lun.zhang on 12/18/2018 09:29
 * 邮箱：zhanglun_study@163.com
 */
public class HomeRecycleViewAdapter extends RecyclerView.Adapter<HomeRecycleViewAdapter.ViewHolder> {
    private Context context;
    private List<PublishTaskListEntity.DataBean> taskListInfo;
    private PublishTaskListEntity.DataBean taskInfo;
    private String uid;
    private String nickName;

    public HomeRecycleViewAdapter(Context context, List<PublishTaskListEntity.DataBean> taskListInfo) {
        this.context = context;
        this.taskListInfo = taskListInfo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        taskInfo = taskListInfo.get(position);
        uid = taskInfo.getUid();
        Glide.with(context)
                .load(Const.baseUrl + taskInfo.getUser_picture())
                .into(holder.imgPortrait);
        if (!TextUtils.isEmpty(taskInfo.getField_user_location())) {
            holder.tvDistance.setText("☞ " + taskInfo.getField_user_location());
        } else {
            holder.tvDistance.setText("未知");
        }
        nickName = taskInfo.getField_user_nickname();
        holder.tvNickname.setText(nickName);
        if (!TextUtils.isEmpty(taskInfo.getField_user_level())) {
            holder.lvLevel.setText("lv" + new BigDecimal(taskInfo.getField_user_level()).stripTrailingZeros());
        } else {
            holder.lvLevel.setText("lv0");
        }
        holder.tvLable.setText("♀" + taskInfo.getField_user_age() + " "+taskInfo.getField_user_tags());
        holder.tvSignature.setText(taskInfo.getField_user_statement());
        holder.tvStar.setText(taskInfo.getField_user_stars());
        holder.tvScore.setText(taskInfo.getField_user_matches());
        holder.tvGameLevel.setText(taskInfo.getField_game_level());
        holder.tvGamename.setText(taskInfo.getField_gamename());
        holder.tvGamePlatform.setText(taskInfo.getField_game_platform());
        holder.tvBooktime.setText("预约" + taskInfo.getField_booktime());
        String taskType = taskInfo.getField_type();
        switch (taskType){
            case Const.TASKFREESTR:
                if(holder.llTarget.getVisibility() == View.VISIBLE){
                    holder.llTarget.setVisibility(View.INVISIBLE);
                }
                break;
            case Const.TASKSCORESTR:
                if(holder.llTarget.getVisibility() == View.INVISIBLE){
                    holder.llTarget.setVisibility(View.VISIBLE);
                }
                holder.tvTargetScore.setText("上分 " + taskInfo.getField_target_division() + taskInfo.getField_stars() + "星");
                holder.tvReward.setText("奖励"+taskInfo.getField_remuneration()+"佩币");
                break;
            case Const.TASKGOLDSTR:
                if(holder.llTarget.getVisibility() == View.INVISIBLE){
                    holder.llTarget.setVisibility(View.VISIBLE);
                }
                holder.tvTargetScore.setText("赏金 " + taskInfo.getField_target_division() + taskInfo.getField_stars() + "星");
                holder.tvReward.setText("奖励"+taskInfo.getField_remuneration()+"佩币");
                break;
            case Const.TASKTUTORIALSTR:
                if(holder.llTarget.getVisibility() == View.INVISIBLE){
                    holder.llTarget.setVisibility(View.VISIBLE);
                }
                holder.tvTargetScore.setText("教学 " + taskInfo.getField_teach_game_numbers() + "局");
                holder.tvReward.setText("奖励"+taskInfo.getField_remuneration()+"佩币");
                break;
        }

    }

    @Override
    public int getItemCount() {
        return taskListInfo.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
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
        @BindView(R.id.rc_rate)
        RatingBar rcRate;
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
        @BindView(R.id.tv_target_score)
        TextView tvTargetScore;
        @BindView(R.id.tv_reward)
        TextView tvReward;
        @BindView(R.id.ll_target)
        LinearLayout llTarget;
        @BindView(R.id.tv_booktime)
        TextView tvBooktime;
        @BindView(R.id.tv_match)
        TextView tvMatch;
        @BindView(R.id.img_share)
        ImageView imgShare;
        @BindView(R.id.ll_cardview)
        LinearLayout llCardview;
        @BindView(R.id.card_view)
        CardView cardView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
