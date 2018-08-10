package com.mine.shortvideo;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.mine.shortvideo.activity.LoginActivity;
import com.mine.shortvideo.activity.SearchActivity;
import com.mine.shortvideo.customview.BottomNavigationViewEx;
import com.mine.shortvideo.fragment.FragmentTabAdapter;
import com.mine.shortvideo.fragment.HomeFragment;
import com.mine.shortvideo.fragment.MessageFragment;
import com.mine.shortvideo.fragment.MineFragment;
import com.mine.shortvideo.fragment.VideoFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

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
                        Intent intent = new Intent();
                        intent.setClass(context, LoginActivity.class);
                        startActivity(intent);
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
                if (ISUPPULL){
                    btnPull.setImageResource(R.mipmap.icon_down_pull);
                    ISUPPULL = false;
                }else {
                    btnPull.setImageResource(R.mipmap.icon_up_pull);
                    ISUPPULL = true;
                }
                break;
        }
    }
}
