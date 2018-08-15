package com.mine.shortvideo;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mine.shortvideo.PopupWindow.CommonPopupWindow;
import com.mine.shortvideo.activity.SearchActivity;
import com.mine.shortvideo.customview.BottomNavigationViewEx;
import com.mine.shortvideo.fragment.FragmentTabAdapter;
import com.mine.shortvideo.fragment.HomeFragment;
import com.mine.shortvideo.fragment.MessageFragment;
import com.mine.shortvideo.fragment.MineFragment;
import com.mine.shortvideo.fragment.VideoFragment;
import com.mine.shortvideo.utils.ToastUtils;
import com.mine.shortvideo.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity implements CommonPopupWindow.ViewInterface {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bn);
        context = this;
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
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
                switch (item.getItemId()) {
                    case R.id.i_home:
                        tabAdapter.getRadioGroup(HOMEFRAGMENT);
                        return true;
                    case R.id.i_video:
                        tabAdapter.getRadioGroup(VIDEOFRAGMENT);
                        return true;
                    case R.id.menu_add:
                        showAll();
//                        new CommomDialog(context, R.style.dialog, "确定删除", new CommomDialog.OnCloseListener() {
//                            @Override
//                            public void onClick(Dialog dialog, boolean confirm) {
//                                if(confirm){
//                                    Toast.makeText(context,"点击确定", Toast.LENGTH_SHORT).show();
//                                    dialog.dismiss();
//                                }
//                            }
//                        }).setTitle("删除提示").show();
//                        Intent intent = new Intent();
//                        intent.setClass(context, LoginActivity.class);
//                        startActivity(intent);
//                            tabAdapter.getRadioGroup(USERFRAGMENT);
                        return true;
                    case R.id.i_message:
                        tabAdapter.getRadioGroup(MESSAGEFRAGMENT);
                        return true;
                    case R.id.i_mine:
                        tabAdapter.getRadioGroup(MINEFRAGMENT);
                        return true;
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
        View upView = LayoutInflater.from(this).inflate(R.layout.add_task_type, null);
        //测量View的宽高
        Utils.measureWidthAndHeight(upView);
        popupWindow = new CommonPopupWindow.Builder(this)
                .setView(R.layout.add_task_type)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, upView.getMeasuredHeight())
                .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗
                .setAnimationStyle(R.style.AnimUp)
                .setViewOnclickListener(this)
                .create();
        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.BOTTOM, 0, 0);
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
