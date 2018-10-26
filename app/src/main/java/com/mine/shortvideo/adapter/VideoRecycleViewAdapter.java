package com.mine.shortvideo.adapter;

import android.app.Activity;
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
import com.mine.shortvideo.utils.ToastUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;

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

            String imgUrl = Const.baseUrl + listVideo.get(position).getUser_picture();
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
        holder.tvCollectCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ShareAction((Activity) context).withText("分享").withMedia(shareVideo(listVideo.get(position).getField_media_video_file())).setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE)
                        .setCallback(shareListener).open();
            }
        });
//        holder.imgUserPortrait.setImageResource(imgs[position / 2]);
//        holder.videoView.setVideoURI(Uri.parse("android.resource://"+context.getPackageName()+"/"+ videos[position/2]));
    }

    @Override
    public int getItemCount() {
        return listVideo.size();
    }

    private UMVideo shareVideo(String urlVideo){
//        String videourl = "http://ips.ifeng.com/video19.ifeng.com/video09/2018/07/05/35476841-102-009-091221.mp4?vid=42871dd3-9891-4694-8027-dae611ee9106&uid=1489972570765_iiyhy01818&from=v_Free&pver=vHTML5Player_v2.0.0&sver=&se=%E5%87%A4%E5%87%B0%E7%BD%91%E9%9D%9E%E5%B8%B8%E9%81%93&cat=61-65&ptype=61&platform=pc&sourceType=h5&dt=1530752669000&gid=avx1AWRthOKX&sign=83f904d8762d24e936e9b85b058b8650&tm=1530845782680";
        String videourl = urlVideo;
        UMVideo video;
        video = new UMVideo(videourl);
        video.setTitle("王者五杀视频");//视频的标题
//        video.setH5Url("http://www.51pepe.com/sites/default/files/pictures/2018-09/frame_1s_0.jpg");//视频的缩略图
        video.setThumb(new UMImage(context,"http://www.51pepe.com/sites/default/files/pictures/2018-09/frame_1s_0.jpg"));
        video.setDescription("这是个有趣的游戏视频");//视频的描述
        return video;
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
////            Intent shareIntent = new Intent();
////            shareIntent.setAction(Intent.ACTION_SEND);            //分享视频只能单个分享
////            shareIntent.putExtra(Intent.EXTRA_STREAM,"www.uaes.site:8088/d86//sites//default/files/2018-09/%25E5%258D%25A2%25E5%25B8%2583.mp4");
////            shareIntent.setType("audio/*");
////            context.startActivity(Intent.createChooser(shareIntent, "分享到"));
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
    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            ToastUtils.show("分享成功");
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            ToastUtils.show("分享失败");
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            ToastUtils.show("分享取消");

        }
    };
}
