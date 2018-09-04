package com.mine.shortvideo.fragment;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.gson.Gson;
import com.mine.shortvideo.R;
import com.mine.shortvideo.adapter.VideoRecycleViewAdapter;
import com.mine.shortvideo.constant.Const;
import com.mine.shortvideo.entity.VideoEntity;
import com.mine.shortvideo.utils.CommonDialogUtils;
import com.mine.shortvideo.utils.NetworkUtil;
import com.mine.shortvideo.utils.OkHttpUtils;
import com.mine.shortvideo.utils.ToastUtils;
import com.mine.shortvideo.utils.Utils;
import com.mine.shortvideo.viewpager.OnViewPagerListener;
import com.mine.shortvideo.viewpager.ViewPagerLayoutManager;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Request;
import timber.log.Timber;

public class VideoFragment extends BaseFragment {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private VideoRecycleViewAdapter mAdapter;
    private ViewPagerLayoutManager mLayoutManager;
    private Context context;
    private static final String TAG = "VideoFragment";
    private static boolean isPlaying = false;
    private VideoView videoView = null;
    private VideoEntity videoEntity;
    private CommonDialogUtils dialogUtils;
    private MyHandler handler = null;
    private int page = 0;
    private List<VideoEntity.DataBean> listVideo;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        context = getActivity();
        handler = new MyHandler(getActivity());
        dialogUtils = new CommonDialogUtils();

        initNetworkData();
    }

    private void initNetworkData() {
        if (NetworkUtil.isNetworkEnabled(context) == -1 || !NetworkUtil.isNetworkAvailable(context)) {
            handler.sendEmptyMessage(3);
            return;
        }
        dialogUtils.showProgress(context);
        OkHttpUtils.getAsync(Const.getVideoList + "&page=" + page, false, new OkHttpUtils.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Timber.e("videolist:" + result);

                StringBuilder sb = new StringBuilder();
                sb.append("{");
                sb.append("\"data\":");
                sb.append(result);
                sb.append("}");
                Gson gson = new Gson();
                videoEntity = gson.fromJson(sb.toString(),VideoEntity.class);
                if(listVideo == null){
                    listVideo = new ArrayList<>();
                }
                if(result.length() < 50){
                    Utils.sendHandleMsg(2, "没有数据了", handler);
                }else {
                    for (int i = 0; i <videoEntity.getData().size() ; i++) {
                        listVideo.add(videoEntity.getData().get(i));
                    }
                    Utils.sendHandleMsg(4, "数据获取成功", handler);
                }
                page++;
            }
        });
//        OkHttpUtils.getAsync(Const.getVideoComment, false, new OkHttpUtils.DataCallBack() {
//            @Override
//            public void requestFailure(Request request, IOException e) {
//
//            }
//
//            @Override
//            public void requestSuccess(String result) throws Exception {
////                Timber.e("video comment:" + result);
//            }
//        });
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
                if(isBottom){
                    initNetworkData();
                }
            }


        });
    }

    private void playVideo(int position) {
        View itemView = recycler.getChildAt(position);
        videoView = itemView.findViewById(R.id.video_view);
        final ImageView imgPlay = itemView.findViewById(R.id.img_play);
        final ImageView imgThumb = itemView.findViewById(R.id.img_thumb);
        final RelativeLayout rootView = itemView.findViewById(R.id.root_view);
        final MediaPlayer[] mediaPlayer = new MediaPlayer[1];
        dialogUtils.showProgress(context,"视频缓冲中……");
        videoView.start();
//        videoView.seekTo(1);
        isPlaying = true;
        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                mediaPlayer[0] = mp;
                mp.setLooping(true);
//                mp.seekTo(1);
                imgThumb.animate().alpha(0).setDuration(200).start();
                return false;
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
    //使用FragmentManager的show hide方法来显示和隐藏fragment的时候使用
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){
            if(videoView != null)
                videoView.pause();
            //TODO now visible to user
        } else {
            //TODO now invisible to user
//            if(videoView != null)
////                videoView.start();
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
                        ToastUtils.show(msg.obj.toString(),Toast.LENGTH_SHORT);
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
        if(mAdapter == null && mLayoutManager == null){
            mLayoutManager = new ViewPagerLayoutManager(context, OrientationHelper.VERTICAL);
            mAdapter = new VideoRecycleViewAdapter(context,listVideo);
            recycler.setLayoutManager(mLayoutManager);
            recycler.setAdapter(mAdapter);
        }else{

            mAdapter.notifyDataSetChanged();
        }
        initListener();
    }

    private void dismissProgress(){
        if(dialogUtils!=null){
            dialogUtils.dismissProgress();
        }
    }
}
