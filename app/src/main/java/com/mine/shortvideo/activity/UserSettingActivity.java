package com.mine.shortvideo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mine.shortvideo.R;
import com.mine.shortvideo.utils.MySharedData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：created by lun.zhang on 8/27/2018 19:33
 * 邮箱：zhanglun_study@163.com
 */
public class UserSettingActivity extends Activity {
    @BindView(R.id.img_left)
    ImageView imgLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_account_setting)
    TextView tvAccountSetting;
    @BindView(R.id.ll_account_setting)
    LinearLayout llAccountSetting;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        context = this;
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvTitle.setText("设置");
    }

    @OnClick({R.id.tv_account_setting, R.id.ll_account_setting,R.id.img_left,R.id.ll_user_exit,R.id.tv_user_exit})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tv_account_setting:
            case R.id.ll_account_setting:
                intent.setClass(context,AccountSettingActivity.class);
                startActivity(intent);
                break;
            case R.id.img_left:
                finish();
                break;
            case R.id.ll_user_exit:
            case R.id.tv_user_exit:
                MySharedData.sharedata_WriteString(context,"userId","");
                MySharedData.sharedata_WriteString(context,"password","");
                finish();
                break;

        }
    }
}
