package com.mine.shortvideo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.mine.shortvideo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：created by lun.zhang on 8/10/2018 16:34
 * 邮箱：zhanglun_study@163.com
 */
public class LoginActivity extends Activity {
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.img_close)
    ImageView imgClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.et_password, R.id.img_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_password:
                break;
            case R.id.img_close:
                finish();
                break;
        }
    }
}
