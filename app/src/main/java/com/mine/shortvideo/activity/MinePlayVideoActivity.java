package com.mine.shortvideo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.mine.shortvideo.R;
import com.mine.shortvideo.adapter.VideoRecycleViewAdapter;
import com.mine.shortvideo.viewpager.OnViewPagerListener;
import com.mine.shortvideo.viewpager.ViewPagerLayoutManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class MinePlayVideoActivity extends Activity {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.btn_back)
    Button btnBack;
    private VideoRecycleViewAdapter mAdapter;
    private ViewPagerLayoutManager mLayoutManager;
    private Context context;
    private static final String TAG = "MinePlayVideoActivity";
    private static boolean isPlaying = false;
    private int index = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_play_video);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        Intent intent = getIntent();
        index = intent.getIntExtra("VideoIndex", 0);
        Timber.e(index + "");
        mLayoutManager = new ViewPagerLayoutManager(context, OrientationHelper.VERTICAL);
        mAdapter = new VideoRecycleViewAdapter(context);
        recycler.setLayoutManager(mLayoutManager);
        recycler.setAdapter(mAdapter);
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
}
