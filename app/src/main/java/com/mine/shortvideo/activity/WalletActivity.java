package com.mine.shortvideo.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mine.shortvideo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：created by lun.zhang on 11/14/2018 14:13
 * 邮箱：zhanglun_study@163.com
 */
public class WalletActivity extends Activity {
    @BindView(R.id.img_left)
    ImageView imgLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_integral)
    TextView tvIntegral;
    @BindView(R.id.tv_recharge)
    TextView tvRecharge;
    @BindView(R.id.tv_withdraw)
    TextView tvWithdraw;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        context = this;
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvTitle.setText("钱包");
    }

    @OnClick({R.id.img_left, R.id.tv_recharge, R.id.tv_withdraw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_left:
                finish();
                break;
            case R.id.tv_recharge:
                break;
            case R.id.tv_withdraw:
                break;
        }
    }
}
