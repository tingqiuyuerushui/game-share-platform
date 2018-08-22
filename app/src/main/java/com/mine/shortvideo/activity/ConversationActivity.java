package com.mine.shortvideo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mine.shortvideo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：created by lun.zhang on 8/16/2018 14:05
 * 邮箱：zhanglun_study@163.com
 */
public class ConversationActivity extends FragmentActivity {
    @BindView(R.id.img_left)
    ImageView imgLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    private String title;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation);
        ButterKnife.bind(this);
        title = getIntent().getData().getQueryParameter("title");
        tvTitle.setText(title);
    }

    @OnClick({R.id.img_left, R.id.tv_title, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_left:
                finish();
                break;
            case R.id.tv_title:
                break;
            case R.id.tv_right:
                break;
        }
    }
}
