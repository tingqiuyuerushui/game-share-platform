package com.mine.shortvideo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.mine.shortvideo.MainActivity;
import com.mine.shortvideo.R;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppStartActivity extends Activity {
    @BindView(R.id.img_gif)
    ImageView imgGif;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
//        RequestOptions options = new RequestOptions()
//                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(this).load("file:///android_asset/gif_start.gif").into(imgGif);
//        Glide.with(this).load(R.mipmap.gif_start).apply(options).into(imgGif);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(AppStartActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);//延时1s执行
    }
}
