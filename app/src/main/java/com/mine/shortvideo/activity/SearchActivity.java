package com.mine.shortvideo.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.mine.shortvideo.R;
import com.mine.shortvideo.adapter.UserVideoListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends Activity {
    @BindView(R.id.edit_search)
    EditText editSearch;
    @BindView(R.id.recycler_list)
    RecyclerView recyclerList;
    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.btn_pull)
    ImageView btnPull;
    private Context context;
    private UserVideoListAdapter userVideoListAdapter;
    private LinearLayoutManager layoutManager;
    private static final String TAG = "SearchActivity";
    private static boolean ISUPPULL = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        context = this;
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
//        userVideoListAdapter = new UserVideoListAdapter(context);
//        layoutManager = new LinearLayoutManager(context);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerList.setLayoutManager(layoutManager);
//        recyclerList.setAdapter(userVideoListAdapter);
//        recyclerList.setHasFixedSize(true);
//        recyclerList.setNestedScrollingEnabled(false);
    }

    @OnClick({R.id.btn_back, R.id.btn_pull})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
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
