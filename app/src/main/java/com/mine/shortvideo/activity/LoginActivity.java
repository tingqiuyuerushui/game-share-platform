package com.mine.shortvideo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mine.shortvideo.R;
import com.mine.shortvideo.constant.Const;
import com.mine.shortvideo.entity.RegistResultEntity;
import com.mine.shortvideo.entity.RequestJsonParameter;
import com.mine.shortvideo.utils.Code;
import com.mine.shortvideo.utils.MySharedData;
import com.mine.shortvideo.utils.OkHttpUtils;
import com.mine.shortvideo.utils.ToastUtils;
import com.mine.shortvideo.utils.Utils;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.io.IOException;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;
import timber.log.Timber;

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
    @BindView(R.id.btn_commit)
    TextView btnCommit;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.img_wechat)
    ImageView imgWechat;
    @BindView(R.id.img_qq)
    ImageView imgQq;
    @BindView(R.id.img_weibo)
    ImageView imgWeibo;
    private Context context;
    private String nickName = "hello12";
    private String numPhone = "17839997735";
    private String password = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        context = this;
    }

    @OnClick({R.id.et_password, R.id.img_close, R.id.btn_commit,R.id.img_wechat, R.id.img_qq, R.id.img_weibo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_password:
                break;
            case R.id.img_close:
                finish();
                break;
            case R.id.btn_commit:
                numPhone = etNumber.getText().toString();
                password = etPassword.getText().toString();
                nickName = Utils.getRandomJianHan(4);
                if (!TextUtils.isEmpty(numPhone) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(nickName)) {
                    creatUser(RequestJsonParameter.loadCreateUserJsonParameter(numPhone, password, nickName));
                } else {
                    ToastUtils.show("手机号或密码不能为空");
                }
                Intent intent = getIntent();
                intent.putExtra("userName", numPhone);
                setResult(Code.LOGININ_RESULT, intent);
                break;
            case R.id.img_wechat:
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.WEIXIN, umAuthListener);
                break;
            case R.id.img_qq:
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ, umAuthListener);
                break;
            case R.id.img_weibo:
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.SINA, umAuthListener);
                break;
        }
    }

    private void creatUser(String postJsonData) {
        OkHttpUtils.postJsonAsync(Const.createUser, postJsonData, new OkHttpUtils.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Timber.e("create user" + result.length());
                if (result.length() > 400) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("{");
                    sb.append("\"data\":");
                    sb.append(result);
                    sb.append("}");
                    Timber.e(sb.toString());
                    Gson gson = new Gson();
                    RegistResultEntity registResultEntity = gson.fromJson(sb.toString(), RegistResultEntity.class);
                    Timber.e(registResultEntity.getData().getField_user_mobile().get(0).getValue() + "");
                    MySharedData.sharedata_WriteString(context, "userId", registResultEntity.getData().getField_user_mobile().get(0).getValue());
                    MySharedData.sharedata_WriteInt(context, "uid", registResultEntity.getData().getUid().get(0).getValue());
                    MySharedData.sharedata_WriteString(context, "password", password);
                } else {
                    Timber.e("create result" + result);
                    if (result.contains("is already taken")) {
                        MySharedData.sharedata_WriteString(context, "userId", numPhone);
                        MySharedData.sharedata_WriteString(context, "password", password);
                    }
                }
                finish();

            }
        });
    }
    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调
        }
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            ToastUtils.show("授权成功");
           for (String key:data.keySet()){
               String value = data.get(key);
               Timber.e("第三方登录数据-->"+ key + ":" + value);
           }

        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            ToastUtils.show("授权出错");
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            ToastUtils.show("授权取消");
        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }
}
