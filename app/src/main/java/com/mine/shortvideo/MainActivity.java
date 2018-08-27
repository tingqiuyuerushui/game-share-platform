package com.mine.shortvideo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mine.shortvideo.PopupWindow.CommonPopupWindow;
import com.mine.shortvideo.activity.LoginActivity;
import com.mine.shortvideo.activity.SearchActivity;
import com.mine.shortvideo.application.MyApplication;
import com.mine.shortvideo.constant.Const;
import com.mine.shortvideo.customview.BottomNavigationViewEx;
import com.mine.shortvideo.customview.CommomDialog;
import com.mine.shortvideo.fragment.FragmentTabAdapter;
import com.mine.shortvideo.fragment.HomeFragment;
import com.mine.shortvideo.fragment.MessageFragment;
import com.mine.shortvideo.fragment.MineFragment;
import com.mine.shortvideo.fragment.VideoFragment;
import com.mine.shortvideo.utils.OkHttpUtils;
import com.mine.shortvideo.utils.ToastUtils;
import com.mine.shortvideo.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import okhttp3.Request;
import timber.log.Timber;

public class MainActivity extends FragmentActivity implements CommonPopupWindow.ViewInterface {

    @BindView(R.id.bnve_center_icon_only)
    BottomNavigationViewEx bnveCenterIconOnly;
    @BindView(R.id.fragment_content)
    FrameLayout fragmentContent;
    @BindView(R.id.btn_search)
    ImageView btnSearch;
    @BindView(R.id.btn_pull)
    ImageView btnPull;
    private Context context;
    private static final String TAG = "MainActivity";
    private List<Fragment> fragments = new ArrayList<>();
    private ArrayList<String> FragmentTagList = new ArrayList<>();
    private FragmentTabAdapter tabAdapter;
    public static final int HOMEFRAGMENT = 0;
    public static final int VIDEOFRAGMENT = 1;
    public static final int MESSAGEFRAGMENT = 2;
    public static final int MINEFRAGMENT = 3;
    private static boolean ISUPPULL = false;
    private CommonPopupWindow popupWindow;
    private CommonPopupWindow popupWindow1;
    private static int APPSTATUS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bn);
        context = this;
        ButterKnife.bind(this);
        initView();
        initNetworkData();

//        if(RongIM.getInstance() != null)
//            RongIM.getInstance().startConversationList(context);
    }

    private void initNetworkData() {
        OkHttpUtils.getAsync(Const.getStatus, true, new OkHttpUtils.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Timber.e(result);
                JSONObject jsonObject = JSON.parseObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("field_value");
                JSONObject jsonValue = JSONObject.parseObject(jsonArray.getString(0));
                APPSTATUS = jsonValue.getInteger("value");
//                Timber.e(jsonValue.getString("value"));
            }
        });
    }

    private void initView() {
        connectRongIM(Const.tokenRongIM);
        disableAllAnimation(bnveCenterIconOnly);
        int centerPosition = 2;
        bnveCenterIconOnly.setIconVisibility(false);
        bnveCenterIconOnly.setIconSizeAt(centerPosition, 40, 40);
        bnveCenterIconOnly.setTextSize(16);
        bnveCenterIconOnly.setIconTintList(centerPosition,
                getResources().getColorStateList(R.color.selector_item_gray_color));
        bnveCenterIconOnly.setIconMarginTop(centerPosition, BottomNavigationViewEx.dp2px(this, 4));
        // you could set a listener for bnve. and return false when click the center item so that it won't be checked.
        bnveCenterIconOnly.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(APPSTATUS == 2) {
                    switch (item.getItemId()) {
                        case R.id.i_home:
                            tabAdapter.getRadioGroup(HOMEFRAGMENT);
                            return true;
                        case R.id.i_video:
                            tabAdapter.getRadioGroup(VIDEOFRAGMENT);
                            return true;
                        case R.id.menu_add:
                            if(Utils.isUserLogin(context)){
                                showAll();
                            }else{
                                Intent intent = new Intent();
                                intent.setClass(context, LoginActivity.class);
                                startActivity(intent);
                            }
                            return true;
                        case R.id.i_message:
                            tabAdapter.getRadioGroup(MESSAGEFRAGMENT);
                            return true;
                        case R.id.i_mine:
                            tabAdapter.getRadioGroup(MINEFRAGMENT);
                            return true;
                    }
                }
                return false;
            }
        });
        FragmentTagList.add("HomeFragment");
        FragmentTagList.add("VideoFragment");
        FragmentTagList.add("MessageFragment");
        FragmentTagList.add("MineFragment");
        fragments.add(new HomeFragment());
        fragments.add(new VideoFragment());
        fragments.add(new MessageFragment());
        fragments.add(new MineFragment());
        tabAdapter = new FragmentTabAdapter(this, fragments, R.id.fragment_content, FragmentTagList);
    }

    private void disableAllAnimation(BottomNavigationViewEx bnve) {
//        bnve.enableAnimation(false);
        bnve.enableShiftingMode(false);
        bnve.enableItemShiftingMode(false);
    }

    //全屏弹出
    public void showAll() {
        if (popupWindow != null && popupWindow.isShowing()) return;
        View upView = LayoutInflater.from(context).inflate(R.layout.add_task_type, null);
        //测量View的宽高
        Utils.measureWidthAndHeight(upView);
//        darkenBackground(0.5f);
        popupWindow = new CommonPopupWindow.Builder(context)
                .setView(R.layout.add_task_type)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, upView.getMeasuredHeight())
                .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗
                .setAnimationStyle(R.style.AnimUp)
                .setViewOnclickListener(this)
                .create();
        popupWindow.showAtLocation(findViewById(R.id.main_activity), Gravity.BOTTOM, 0, 0);
    }
    private void darkenBackground(Float bgcolor){
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgcolor;
        if(bgcolor == 1.0f){
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }else{
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }
        getWindow().setAttributes(lp);

    }
    //全屏弹出
    public void showPublishTask() {
        if (popupWindow1 != null && popupWindow1.isShowing()) return;
        View upView = LayoutInflater.from(this).inflate(R.layout.publish_task, null);
        //测量View的宽高
        Utils.measureWidthAndHeight(upView);
        popupWindow1 = new CommonPopupWindow.Builder(this)
                .setView(R.layout.publish_task)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, upView.getMeasuredHeight())
                .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗
                .setAnimationStyle(R.style.AnimUp)
                .setViewOnclickListener(this)
                .create();
        popupWindow1.showAtLocation(findViewById(android.R.id.content), Gravity.BOTTOM, 0, 0);
    }
    private void connectRongIM(String token) {
        if (getApplicationInfo().packageName.equals(MyApplication.getCurProcessName(getApplicationContext()))) {
            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {
                    Timber.e("Token错误");
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token 对应的用户 id
                 */
                @Override
                public void onSuccess(String userid) {
                    Timber.e("userid" + userid);
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Timber.e("连接融云失败");
                }
            });
        }
    }
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
//                System.exit(0);

            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.btn_search, R.id.btn_pull})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_search:
                Intent intent = new Intent();
                intent.setClass(context, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_pull:
                if (ISUPPULL) {
                    btnPull.setImageResource(R.mipmap.icon_down_pull);
                    ISUPPULL = false;
                } else {
                    btnPull.setImageResource(R.mipmap.icon_up_pull);
                    ISUPPULL = true;
                }
                break;

        }
    }

    @Override
    public void getChildView(View view, int layoutResId) {
        switch (layoutResId) {
            case R.layout.add_task_type:
                ViewHolder viewHolder = new ViewHolder(view);
                view.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (popupWindow != null) {
                            popupWindow.dismiss();
//                            darkenBackground(1f);
                        }
                        return true;
                    }
                });
                break;
            case R.layout.publish_task:
                ViewHolder1 viewHolder1 = new ViewHolder1(view);
                break;
        }
    }

    class ViewHolder {
        @OnClick(R.id.ll_score)
        public void showToast() {
            ToastUtils.show("急速上分");
            if (popupWindow != null) {
                popupWindow.dismiss();
            }
        }

        @OnClick(R.id.ll_gold)
        public void showToast1() {
            ToastUtils.show("任务赏金");
            if (popupWindow != null) {
                popupWindow.dismiss();
            }
        }

        @OnClick(R.id.ll_tutorial)
        public void showToast2() {
            ToastUtils.show("大神教程");
            if (popupWindow != null) {
                popupWindow.dismiss();
            }
        }

        @OnClick(R.id.ll_free)
        public void showToast3() {
            showPublishTask();
            ToastUtils.show("完美脱单");
            if (popupWindow != null) {
                popupWindow.dismiss();
            }
        }

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    class ViewHolder1 {
        @OnClick(R.id.btn_publish)
        public void publishTask() {
            ToastUtils.show("发布任务");
            if (popupWindow1 != null) {
                popupWindow1.dismiss();
            }
        }
        @OnClick(R.id.img_close)
        public void closePop() {
            if (popupWindow1 != null) {
                popupWindow1.dismiss();
            }
        }

        ViewHolder1(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
