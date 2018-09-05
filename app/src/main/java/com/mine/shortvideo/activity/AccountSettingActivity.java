package com.mine.shortvideo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mine.shortvideo.R;
import com.mine.shortvideo.constant.Const;
import com.mine.shortvideo.entity.RequestJsonParameter;
import com.mine.shortvideo.entity.UploadFileResultEntity;
import com.mine.shortvideo.entity.UserInfoEntity;
import com.mine.shortvideo.fragment.MineFragment;
import com.mine.shortvideo.photopicker.PhotoPicker;
import com.mine.shortvideo.utils.CommonDialogUtils;
import com.mine.shortvideo.utils.MySharedData;
import com.mine.shortvideo.utils.OkHttpUtils;
import com.mine.shortvideo.utils.ToastUtils;
import com.mine.shortvideo.utils.Utils;
import com.mine.shortvideo.utils.XingZuo;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.util.ConvertUtils;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Request;
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
    @BindView(R.id.img_portrait)
    CircleImageView imgPartrait;
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
    private ArrayList<String> photos;
    private MyHandler handler;
    private CommonDialogUtils dialogUtils;
    private UploadFileResultEntity uploadFileResultEntity;
    private static int userId = 0;
    private boolean QUESTAUTH = true;
    private boolean QUESTNOAUTH = false;
    private String userName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);
        ButterKnife.bind(this);
        context = this;
        userId = MySharedData.sharedata_ReadInt(context,"uid");
        userName = MySharedData.sharedata_ReadString(context, "userId");
        dialogUtils = new CommonDialogUtils();
        handler = new MyHandler(AccountSettingActivity.this);
        initView();
        getUserInfo();
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
    private void getUserInfo() {
        dialogUtils.showProgress(context);
        OkHttpUtils.getAsync(Const.getUserInfoUrl + userName + "?_format=json", QUESTAUTH, new OkHttpUtils.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                Timber.e("获取数据失败");
            }

            @Override
            public void requestSuccess(String result) throws Exception {
//                Timber.e(result);
                StringBuilder sb = new StringBuilder();
                sb.append("{");
                sb.append("\"data\":");
                sb.append(result);
                sb.append("}");
                Gson gson = new Gson();
                UserInfoEntity userInfoEntity = gson.fromJson(sb.toString(), UserInfoEntity.class);
                Timber.e(userInfoEntity.getData().get(0).getField_user_nickname().get(0).getValue() + "");
                userId = userInfoEntity.getData().get(0).getUid().get(0).getValue();
                Utils.sendHandleMsg(1, userInfoEntity, handler);
            }
        });
    }
    private void linkFile(int targetId) {
        String jsonStr;
        String url;
        jsonStr = RequestJsonParameter.linkFile(targetId,userName+"@zz.com");
        url = Const.linkFile + userId + "?_format=json";
        OkHttpUtils.patchJsonAsync(url, jsonStr, new OkHttpUtils.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Timber.e("link file result" + result);
                Utils.sendHandleMsg(4, "上传成功", handler);
            }
        });
    }
    @OnClick({R.id.img_left, R.id.tv_right, R.id.tv_change_portrait, R.id.tv_birthday,R.id.img_portrait})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_left:
                finish();
                break;
            case R.id.tv_right:
                break;
            case R.id.tv_change_portrait:
            case R.id.img_portrait:
                updateUserPortrait();
                break;
            case R.id.tv_birthday:
                onYearMonthDayPicker();
                break;
        }
    }
    private void updateUserPortrait(){
        PhotoPicker.builder()
                .setPhotoCount(1)
                .setShowCamera(true)
                .setShowGif(true)
                .setPreviewEnabled(false)
                .start(AccountSettingActivity.this,PhotoPicker.REQUEST_CODE);
    }
    private Map<String, String> addParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("vin", "111111");
        return params;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                dialogUtils.showProgress(context, "正在上传，请稍后");
                OkHttpUtils.postFileAsync(Const.uploadUrl, addParams(), photos.get(0), new OkHttpUtils.DataCallBack() {
                    @Override
                    public void requestFailure(Request request, IOException e) {

                    }

                    @Override
                    public void requestSuccess(String result) throws Exception {
                        Timber.e("result" + result);
                        Gson gson = new Gson();
                        uploadFileResultEntity = gson.fromJson(result, UploadFileResultEntity.class);
                        linkFile(uploadFileResultEntity.getFid().get(0).getValue());

                    }
                });
            }
        }
    }
    public class MyHandler extends Handler {
        private WeakReference<Activity> reference;

        public MyHandler(Activity activity) {
            reference = new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (reference.get() != null) {
                dismissProgress();
                switch (msg.what) {
                    case 1:
                        UserInfoEntity userInfoEntity = (UserInfoEntity) msg.obj;
                        LoadDataToView(userInfoEntity);
                        break;
                    case 2:
                        ToastUtils.show(msg.obj.toString(), Toast.LENGTH_SHORT);
                        break;
                    case 3:
                        ToastUtils.show("", Toast.LENGTH_SHORT);
                        break;
                    case 4:
                        ToastUtils.show(msg.obj.toString(), Toast.LENGTH_SHORT);
                        break;
                }
            }
        }
    }

    private void LoadDataToView(UserInfoEntity userInfoEntity) {
        etNickname.setText(userInfoEntity.getData().get(0).getField_user_nickname().get(0).getValue());
        if(userInfoEntity.getData().get(0).getField_user_gender().size() > 0){
            etGender.setText(userInfoEntity.getData().get(0).getField_user_gender().get(0).getValue());
        }else{
            etGender.setText("未知");
        }if(userInfoEntity.getData().get(0).getField_user_statement().size() > 0){
            etSignature.setText(userInfoEntity.getData().get(0).getField_user_statement().get(0).getValue());
        }

    }

    private void dismissProgress() {
        if (dialogUtils != null) {
            dialogUtils.dismissProgress();
        }
    }
}
