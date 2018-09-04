package com.mine.shortvideo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.mine.shortvideo.R;
import com.mine.shortvideo.entity.MyVideoEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：created by lun.zhang on 9/4/2018 10:02
 * 邮箱：zhanglun_study@163.com
 */
public class MyVideoRecycleViewAdapter extends RecyclerView.Adapter<MyVideoRecycleViewAdapter.ViewHolder> {
    private int[] imgs = {R.mipmap.img_list_example_thumb_1,R.mipmap.img_list_example_thumb_0,R.mipmap.timg_0,R.mipmap.timg_1};
    private Context context;
    private List<MyVideoEntity.DataBean> listMyvideo;

    public MyVideoRecycleViewAdapter(Context context, List<MyVideoEntity.DataBean> listMyvideo) {
        this.context = context;
        this.listMyvideo = listMyvideo;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_view_pager, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String videoUrl = listMyvideo.get(position).getField_media_video_file();
        holder.videoView.setVideoPath(videoUrl);
        holder.imgThumb.setImageResource(imgs[position % 4]);
        holder.imgUserPortrait.setVisibility(View.GONE);
        holder.tvCommentCount.setVisibility(View.GONE);
        holder.tvCollectCount.setVisibility(View.GONE);
        holder.tvNickname.setVisibility(View.GONE);
        holder.tvTitle.setVisibility(View.GONE);
        holder.tvLikeCount.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return listMyvideo.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.video_view)
        VideoView videoView;
        @BindView(R.id.img_thumb)
        ImageView imgThumb;
        @BindView(R.id.img_play)
        ImageView imgPlay;
        @BindView(R.id.tv_like_count)
        TextView tvLikeCount;
        @BindView(R.id.tv_comment_count)
        TextView tvCommentCount;
        @BindView(R.id.tv_collect_count)
        TextView tvCollectCount;
        @BindView(R.id.img_user_portrait)
        CircleImageView imgUserPortrait;
        @BindView(R.id.tv_nickname)
        TextView tvNickname;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.root_view)
        RelativeLayout rootView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
