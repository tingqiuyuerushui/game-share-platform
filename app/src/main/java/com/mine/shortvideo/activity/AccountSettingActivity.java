package com.mine.shortvideo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mine.shortvideo.R;
import com.mine.shortvideo.utils.XingZuo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.util.ConvertUtils;
import timber.log.Timber;

/**
 * 作者：created by lun.zhang on 8/27/2018 19:39
 * 邮箱：zhanglun_study@163.com
 */
public class AccountSettingActivity extends Activity {
    @BindView(R.id.img_left)
    ImageView imgLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_change_portrait)
    TextView tvChangePortrait;
    @BindView(R.id.et_nickname)
    EditText etNickname;
    @BindView(R.id.et_gender)
    EditText etGender;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.tv_xingzuo)
    TextView tvXingzuo;
    @BindView(R.id.et_lable)
    EditText etLable;
    @BindView(R.id.et_signature)
    EditText etSignature;

    private Context context;
    private String xingzuo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);
        ButterKnife.bind(this);
        context = this;
        initView();
    }

    private void initView() {
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("提交");
        tvTitle.setText("个人资料");
    }

    public void onYearMonthDayPicker() {
        final DatePicker picker = new DatePicker(this);
        picker.setCanceledOnTouchOutside(true);
        picker.setUseWeight(true);
        picker.setTopPadding(ConvertUtils.toPx(this, 10));
        picker.setRangeEnd(2018, 1, 11);
        picker.setRangeStart(1970, 1, 1);
        picker.setSelectedItem(1999, 10, 14);
        picker.setResetWhileWheel(false);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                Timber.e(year + "-" + month + "-" + day);
                tvBirthday.setText(year + "-" + month + "-" + day);
                xingzuo = XingZuo.getXingZuoName(Integer.parseInt(month),Integer.parseInt(day));
                tvXingzuo.setText(xingzuo);
            }
        });
        picker.setOnWheelListener(new DatePicker.OnWheelListener() {
            @Override
            public void onYearWheeled(int index, String year) {
                picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());
            }

            @Override
            public void onMonthWheeled(int index, String month) {
                picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
            }

            @Override
            public void onDayWheeled(int index, String day) {
                picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
            }
        });
        picker.show();
    }

    @OnClick({R.id.img_left, R.id.tv_right, R.id.tv_change_portrait, R.id.tv_birthday})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_left:
                finish();
                break;
            case R.id.tv_right:
                break;
            case R.id.tv_change_portrait:
                break;
            case R.id.tv_birthday:
                onYearMonthDayPicker();
                break;
        }
    }
}
