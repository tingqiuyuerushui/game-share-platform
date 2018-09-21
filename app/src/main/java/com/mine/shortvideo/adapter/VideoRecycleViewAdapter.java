package com.mine.shortvideo.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.google.gson.Gson;
import com.mine.shortvideo.R;
import com.mine.shortvideo.activity.LoginActivity;
import com.mine.shortvideo.activity.VideoCommentActivity;
import com.mine.shortvideo.constant.Const;
import com.mine.shortvideo.entity.LikeCollectEntity;
import com.mine.shortvideo.entity.VideoEntity;
import com.mine.shortvideo.utils.MySharedData;
import com.mine.shortvideo.utils.OkHttpUtils;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Request;
import timber.log.Timber;

public class VideoRecycleViewAdapter extends RecyclerView.Adapter<VideoRecycleViewAdapter.ViewHolder> {
    private int[] imgs = {R.mipmap.timg_0, R.mipmap.timg_2};
    private int[] videos = {R.raw.video_3, R.raw.video_2};
    private Context context;
    private boolean isClicked = false;
    private List<VideoEntity.DataBean> listVideo;
    private int uid = 0;
    private final static String LIKE = "flag";
    private final static String UNLIKE = "unflag";
    private int likeCount = 0;

    public VideoRecycleViewAdapter(Context context, List<VideoEntity.DataBean> listVideo) {
        this.context = context;
        this.listVideo = listVideo;
        uid = MySharedData.sharedata_ReadInt(context,"uid");
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_view_pager, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
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
        likeCount = Integer.parseInt(listVideo.get(position).getLike_count());
        holder.videoView.setVideoPath(videoUrl);
        holder.tvLikeCount.setText(likeCount+"");
        holder.tvCollectCount.setText(listVideo.get(position).getCollect_count());
        holder.tvCommentCount.setText(listVideo.get(position).getComment_count());
        holder.tvNickname.setText(listVideo.get(position).getField_user_nickname());
        holder.tvTitle.setText(listVideo.get(position).getTitle());
        holder.tvLikeCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(uid > 0){
                    LikeCollectEntity likeCollectEntity = new LikeCollectEntity();
                    likeCollectEntity.setEntity_id(Integer.parseInt(listVideo.get(position).getNid()));
                    likeCollectEntity.setEntity_type("node");
                    likeCollectEntity.setFlag_id("like");
                    likeCollectEntity.setMy_uid(uid);
                    if(isClicked){
                        setDrawable(R.mipmap.heart_icon,holder.tvLikeCount);
                        isClicked = false;
                        likeCollectEntity.setFlag_action(UNLIKE);
                        likeCount = likeCount - 1;
                    }else {
                        setDrawable(R.mipmap.icon_red_heart,holder.tvLikeCount);
                        isClicked = true;
                        likeCollectEntity.setFlag_action(LIKE);
                        likeCount = likeCount + 1;
                    }
                    holder.tvLikeCount.setText(likeCount+"");
                    Gson gson = new Gson();
                    String jsonStr = gson.toJson(likeCollectEntity);
                    videoLike(jsonStr);
                }else {
                    Intent intent = new Intent();
                    intent.setClass(context, LoginActivity.class);
                    context.startActivity(intent);
                }
            }
        });
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
        @OnClick(R.id.tv_collect_count)
        public void share(){
//            Intent wechatIntent = new Intent(Intent.ACTION_SEND);
//            wechatIntent.setPackage("com.tencent.mm");
//            wechatIntent.setType("text/plain");
//            wechatIntent.putExtra(Intent.EXTRA_TEXT, "分享到微信的内容");
//            context.startActivity(wechatIntent);
//            Intent shareIntent = new Intent();
//            shareIntent.setAction(Intent.ACTION_SEND);            //分享视频只能单个分享
//            shareIntent.putExtra(Intent.EXTRA_STREAM,"www.uaes.site:8088/d86//sites//default/files/2018-09/%25E5%258D%25A2%25E5%25B8%2583.mp4");
//            shareIntent.setType("audio/*");
//            context.startActivity(Intent.createChooser(shareIntent, "分享到"));
        }
        @OnClick(R.id.tv_comment_count)
        public void comment(){
            Intent intent = new Intent();
            intent.setClass(context, VideoCommentActivity.class);
            context.startActivity(intent);

        }
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    private void setDrawable(int resource,TextView tvLikeCount){
        Drawable drawable = context.getDrawable(resource);
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        tvLikeCount.setCompoundDrawables(null,drawable,null,null);
    }
    private void videoLike(String jsonStr){
        OkHttpUtils.postJsonNoAuthAsync(Const.likeVideo, jsonStr, new OkHttpUtils.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Timber.e("点赞结果-->"+result);

            }
        });
    }
}
