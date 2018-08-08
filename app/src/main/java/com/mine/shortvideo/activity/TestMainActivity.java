package com.mine.shortvideo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.mine.shortvideo.R;
import com.mine.shortvideo.adapter.VideoRecycleViewAdapter;
import com.mine.shortvideo.viewpager.OnViewPagerListener;
import com.mine.shortvideo.viewpager.ViewPagerLayoutManager;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.shareboard.SnsPlatform;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestMainActivity extends Activity {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.bottom)
    LinearLayout bottom;
    @BindView(R.id.tv_user)
    TextView tvUser;
    @BindView(R.id.ll_user)
    LinearLayout llUser;
    @BindView(R.id.tv_msg)
    TextView tvMsg;
    @BindView(R.id.ll_msg)
    LinearLayout llMsg;
    @BindView(R.id.tv_take_video)
    TextView tvTakeVideo;
    @BindView(R.id.ll_take_video)
    LinearLayout llTakeVideo;
    @BindView(R.id.img_heart)
    ImageView imgHeart;
    @BindView(R.id.img_comment)
    ImageView imgComment;
    @BindView(R.id.img_share)
    ImageView imgShare;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    private VideoRecycleViewAdapter mAdapter;
    private ViewPagerLayoutManager mLayoutManager;
    private Context context;
    private static final String TAG = "MainActivity";
    private static boolean isPlaying = false;
    private static boolean isClickHeart = false;
    private static final int REQUEST_VIDEO=1010;
    public ArrayList<SnsPlatform> platforms = new ArrayList<SnsPlatform>();
    private UMVideo video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        ButterKnife.bind(this);
        initView();
        initListener();
        initPlatforms();
    }

    private void initView() {
        mLayoutManager = new ViewPagerLayoutManager(context, OrientationHelper.VERTICAL);
        mAdapter = new VideoRecycleViewAdapter(context);
        recycler.setLayoutManager(mLayoutManager);
        recycler.setAdapter(mAdapter);
    }

    private void initListener() {
        mLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {

            @Override
            public void onInitComplete() {
                Log.e(TAG, "onInitComplete");
                playVideo(0);
            }

            @Override
            public void onPageRelease(boolean isNext, int position) {
                Log.e(TAG, "释放位置:" + position + " 下一页:" + isNext);
                int index = 0;
                if (isNext) {
                    index = 0;
                } else {
                    index = 1;
                }
                releaseVideo(index);
            }

            @Override
            public void onPageSelected(int position, boolean isBottom) {
                Log.e(TAG, "选中位置:" + position + "  是否是滑动到底部:" + isBottom);
                playVideo(0);
            }


        });
    }

    private void initPlatforms(){
        String videourl = "http://ips.ifeng.com/video19.ifeng.com/video09/2018/07/05/35476841-102-009-091221.mp4?vid=42871dd3-9891-4694-8027-dae611ee9106&uid=1489972570765_iiyhy01818&from=v_Free&pver=vHTML5Player_v2.0.0&sver=&se=%E5%87%A4%E5%87%B0%E7%BD%91%E9%9D%9E%E5%B8%B8%E9%81%93&cat=61-65&ptype=61&platform=pc&sourceType=h5&dt=1530752669000&gid=avx1AWRthOKX&sign=83f904d8762d24e936e9b85b058b8650&tm=1530845782680";
        video = new UMVideo(videourl);
        video.setTitle("王者五杀视频");//视频的标题
        video.setH5Url("http://www.umeng.com/images/pic/social/chart_1.png");//视频的缩略图
        video.setDescription("这是个有趣的游戏视频");//视频的描述
//        platforms.clear();
//        platforms.add(SHARE_MEDIA.WEIXIN.toSnsPlatform());
//        platforms.add(SHARE_MEDIA.WEIXIN_CIRCLE.toSnsPlatform());
//        platforms.add(SHARE_MEDIA.WEIXIN_FAVORITE.toSnsPlatform());
//        platforms.add(SHARE_MEDIA.SINA.toSnsPlatform());
//        platforms.add(SHARE_MEDIA.QQ.toSnsPlatform());
//        platforms.add(SHARE_MEDIA.QZONE.toSnsPlatform());
    }
    private void playVideo(int position) {
        View itemView = recycler.getChildAt(position);
        final VideoView videoView = itemView.findViewById(R.id.video_view);
        final ImageView imgPlay = itemView.findViewById(R.id.img_play);
        final ImageView imgThumb = itemView.findViewById(R.id.img_thumb);
        final RelativeLayout rootView = itemView.findViewById(R.id.root_view);
        final MediaPlayer[] mediaPlayer = new MediaPlayer[1];
        videoView.start();
        isPlaying = true;
        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                mediaPlayer[0] = mp;
                mp.setLooping(true);
                imgThumb.animate().alpha(0).setDuration(200).start();
                return false;
            }
        });
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

            }
        });


        imgPlay.setOnClickListener(new View.OnClickListener() {
            boolean isPlaying = true;

            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()) {
                    imgPlay.animate().alpha(1f).start();
                    videoView.pause();
                    isPlaying = false;
                } else {
                    imgPlay.animate().alpha(0f).start();
                    videoView.start();
                    isPlaying = true;
                }
            }
        });
    }

    private void releaseVideo(int index) {
        View itemView = recycler.getChildAt(index);
        final VideoView videoView = itemView.findViewById(R.id.video_view);
        final ImageView imgThumb = itemView.findViewById(R.id.img_thumb);
        final ImageView imgPlay = itemView.findViewById(R.id.img_play);
        videoView.stopPlayback();
        isPlaying = false;
        imgThumb.animate().alpha(1).start();
        imgPlay.animate().alpha(0f).start();
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
            Toast.makeText(TestMainActivity.this,"成功了",Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(TestMainActivity.this,"失败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(TestMainActivity.this,"取消了",Toast.LENGTH_LONG).show();

        }
    };
    @OnClick({R.id.tv_user, R.id.ll_user, R.id.tv_msg, R.id.ll_msg, R.id.tv_take_video, R.id.ll_take_video, R.id.img_heart, R.id.img_comment, R.id.img_share, R.id.tv_search, R.id.ll_search})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tv_user:
            case R.id.ll_user:
                intent.setClass(context, UserActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_msg:
            case R.id.ll_msg:

                break;
            case R.id.tv_take_video:
            case R.id.ll_take_video:
                Intent intent1=new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                intent1.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                Uri uri = FileProvider.getUriForFile(context,"org.diql.fileprovider", settingVideoPath());
                intent1.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(intent1,REQUEST_VIDEO);
                break;
            case R.id.img_heart:
                if(isClickHeart){
                    imgHeart.setImageResource(R.mipmap.icon_blank_heart);
                    isClickHeart = false;
                }else{
                    imgHeart.setImageResource(R.mipmap.icon_red_heart);
                    isClickHeart = true;
                }
                break;
            case R.id.img_comment:

                break;
            case R.id.img_share:
                new ShareAction(TestMainActivity.this).withText("hello").withMedia(video).setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE)
                        .setCallback(shareListener).open();
                break;
            case R.id.tv_search:
            case R.id.ll_search:
                intent.setClass(context, SearchActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isPlaying){
            playVideo(0);
        }
    }
    private File settingVideoPath(){
        String pathString = Environment.getExternalStorageDirectory().getAbsolutePath()+"/TestVideo";
        File dir = new File(pathString);
        if(!dir.exists()){
            dir.mkdirs();
        }
        String name=pathString+"/"+System.currentTimeMillis()+".mp4";
        File file = new File(name);
        return file;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_VIDEO && resultCode == Activity.RESULT_OK) {
            Toast.makeText(TestMainActivity.this, "拍摄成功", Toast.LENGTH_SHORT).show();
            Log.e(" 视频保存的路径",data.getData().toString());
        }else{
            UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        }
    }
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                isPlaying = false;
//                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
