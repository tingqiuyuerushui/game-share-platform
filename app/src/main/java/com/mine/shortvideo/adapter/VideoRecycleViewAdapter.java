package com.mine.shortvideo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.mine.shortvideo.R;
import com.mine.shortvideo.entity.VideoEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import timber.log.Timber;

public class VideoRecycleViewAdapter extends RecyclerView.Adapter<VideoRecycleViewAdapter.ViewHolder> {
    private int[] imgs = {R.mipmap.timg_0, R.mipmap.timg_2};
    private int[] videos = {R.raw.video_3, R.raw.video_2};
    private Context context;
    private List<VideoEntity.DataBean> listVideo;

    public VideoRecycleViewAdapter(Context context, List<VideoEntity.DataBean> listVideo) {
        this.context = context;
        this.listVideo = listVideo;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_view_pager, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String imgUrl = "http://www.uaes.site:8088" + listVideo.get(position).getUser_picture();
        Timber.e(imgUrl);
        Timber.e("listvideo size "+listVideo.size());
        String videoUrl = listVideo.get(position).getField_media_video_file();
        Glide.with(context)
                .load(imgUrl)
                .into(holder.imgUserPortrait);
        holder.videoView.setVideoPath(videoUrl);
        holder.tvLikeCount.setText(listVideo.get(position).getLike_count());
        holder.tvCollectCount.setText(listVideo.get(position).getCollect_count());
        holder.tvCommentCount.setText(listVideo.get(position).getComment_count());
        holder.tvNickname.setText(listVideo.get(position).getField_user_nickname());
        holder.tvTitle.setText(listVideo.get(position).getTitle());
//        holder.imgUserPortrait.setImageResource(imgs[position / 2]);
//        holder.videoView.setVideoURI(Uri.parse("android.resource://"+context.getPackageName()+"/"+ videos[position/2]));
    }

    @Override
    public int getItemCount() {
        return listVideo.size();
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
