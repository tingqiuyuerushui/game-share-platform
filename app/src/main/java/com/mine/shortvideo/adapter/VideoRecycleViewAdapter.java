package com.mine.shortvideo.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
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
import butterknife.OnClick;
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
        if(listVideo.get(position).getUser_picture().equals("url is null")){
            holder.imgUserPortrait.setImageResource(R.mipmap.img_user_example);
        }else{

            String imgUrl = "http://www.uaes.site:8088" + listVideo.get(position).getUser_picture();
            Glide.with(context)
                    .load(imgUrl)
                    .into(holder.imgUserPortrait);
            Timber.e(imgUrl);
        }
        String videoUrl = listVideo.get(position).getField_media_video_file();
        Timber.e("listvideo size " + listVideo.size());
        Timber.e("listvideo url " + videoUrl);

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
        private boolean isClicked = false;
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
        @OnClick(R.id.tv_like_count)
        public void click(){
            if(isClicked){
                setDrawable(R.mipmap.heart_icon);
                isClicked = false;
            }else {
                setDrawable(R.mipmap.icon_red_heart);
                isClicked = true;
            }
        }
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
        private void setDrawable(int resource){
            Drawable drawable = context.getDrawable(resource);
            drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
            tvLikeCount.setCompoundDrawables(null,drawable,null,null);
        }
    }
}
