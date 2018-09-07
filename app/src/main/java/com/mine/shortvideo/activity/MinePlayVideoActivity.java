package com.mine.shortvideo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.mine.shortvideo.R;
import com.mine.shortvideo.adapter.MyVideoRecycleViewAdapter;
import com.mine.shortvideo.entity.MyVideoEntity;
import com.mine.shortvideo.fragment.VideoFragment;
import com.mine.shortvideo.utils.CommonDialogUtils;
import com.mine.shortvideo.utils.ToastUtils;
import com.mine.shortvideo.viewpager.OnViewPagerListener;
import com.mine.shortvideo.viewpager.ViewPagerLayoutManager;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class MinePlayVideoActivity extends Activity {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.btn_back)
    Button btnBack;
    private ViewPagerLayoutManager mLayoutManager;
    private MyVideoRecycleViewAdapter myVideoRecycleViewAdapter;
    private Context context;
    private static final String TAG = "MinePlayVideoActivity";
    private static boolean isPlaying = false;
    private int index = 0;
    private MyVideoEntity myVideoEntity;
    private List<MyVideoEntity.DataBean> listVideo;
    private CommonDialogUtils dialogUtils;
    private MyHandler handler = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_play_video);
        ButterKnife.bind(this);
        handler = new MyHandler(this);
        dialogUtils = new CommonDialogUtils();
        init();
    }

    private void init() {
        Intent intent = getIntent();
        index = intent.getIntExtra("VideoIndex", 0);
        myVideoEntity = intent.getParcelableExtra("myVideoEntity");
        Timber.e(index + "");
        Timber.e("获得MyVideoEntity parcelable-->" + myVideoEntity.getData().get(0).getTitle());
        if(listVideo == null){
            listVideo = new ArrayList<>();
        }
        listVideo.addAll(myVideoEntity.getData());
        mLayoutManager = new ViewPagerLayoutManager(context, OrientationHelper.VERTICAL);
        myVideoRecycleViewAdapter = new MyVideoRecycleViewAdapter(context,listVideo);
        recycler.setLayoutManager(mLayoutManager);
        recycler.setAdapter(myVideoRecycleViewAdapter);
        initListener();
    }

    private void initListener() {
        mLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {

            @Override
            public void onInitComplete() {
                Timber.e("onInitComplete");
                playVideo(0);
            }

            @Override
            public void onPageRelease(boolean isNext, int position) {
//                Log.e(TAG, "释放位置:" + position + " 下一页:" + isNext);
                Timber.e("释放位置:" + position + " 下一页:" + isNext);
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
                Timber.e("选中位置:" + position + "  是否是滑动到底部:" + isBottom);
                playVideo(0);
            }


        });
    }

    private void playVideo(int position) {
        View itemView = recycler.getChildAt(position);
        final VideoView videoView = itemView.findViewById(R.id.video_view);
        final ImageView imgPlay = itemView.findViewById(R.id.img_play);
        final ImageView imgThumb = itemView.findViewById(R.id.img_thumb);
        final RelativeLayout rootView = itemView.findViewById(R.id.root_view);
        final MediaPlayer[] mediaPlayer = new MediaPlayer[1];
        videoView.start();
        dialogUtils.showProgress(context,"视频缓冲中……");
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
        videoView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ToastUtils.show("长按视频");
                return true;
            }
        });
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                    @Override
                    public void onBufferingUpdate(MediaPlayer mediaPlayer, int percent) {
                        Timber.e("缓冲进度-->" + percent);
                        if (percent > 0){
                            dismissProgress();
                        }
                    }
                });
            }
        });


        imgPlay.setOnClickListener(new View.OnClickListener() {

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

    @OnClick(R.id.btn_back)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
            default:
                break;
        }
    }
    public class MyHandler extends Handler {
        private WeakReference<Activity> reference;

        public MyHandler(Activity activity) {
            reference = new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if(reference.get() != null) {
                dismissProgress();
                switch (msg.what) {
                    case 1:
                        break;
                    case 2:
                        ToastUtils.show(msg.obj.toString(), Toast.LENGTH_SHORT);
                        break;
                    case 3:
                        ToastUtils.show("",Toast.LENGTH_SHORT);
                        break;
                    case 4:
                        ToastUtils.show(msg.obj.toString(),Toast.LENGTH_SHORT);
                        LoadDataToView();
                        break;
                }
            }
        }
    }

    private void LoadDataToView() {
    }

    private void dismissProgress(){
        if(dialogUtils!=null){
            dialogUtils.dismissProgress();
        }
    }
}
