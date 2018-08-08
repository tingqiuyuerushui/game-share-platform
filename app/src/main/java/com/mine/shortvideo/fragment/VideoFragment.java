package com.mine.shortvideo.fragment;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.mine.shortvideo.R;
import com.mine.shortvideo.adapter.VideoRecycleViewAdapter;
import com.mine.shortvideo.viewpager.OnViewPagerListener;
import com.mine.shortvideo.viewpager.ViewPagerLayoutManager;

import butterknife.BindView;

public class VideoFragment extends BaseFragment {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private VideoRecycleViewAdapter mAdapter;
    private ViewPagerLayoutManager mLayoutManager;
    private Context context;
    private static final String TAG = "VideoFragment";
    private static boolean isPlaying = false;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        context = getActivity();
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

}
