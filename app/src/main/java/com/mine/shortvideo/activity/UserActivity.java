package com.mine.shortvideo.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mine.shortvideo.R;
import com.mine.shortvideo.adapter.UserGameThumbRecyclerViewAdapter;
import com.mine.shortvideo.adapter.UserVideoListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserActivity extends Activity {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.recycler_list)
    RecyclerView recyclerList;
    private Context context;
    private UserGameThumbRecyclerViewAdapter gameThumbRecyclerViewAdapter;
    private UserVideoListAdapter userVideoListAdapter;
    private LinearLayoutManager layoutManager1;
    private LinearLayoutManager layoutManager2;
    private static final String TAG = "UserActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        context = this;
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        gameThumbRecyclerViewAdapter = new UserGameThumbRecyclerViewAdapter(context);
        layoutManager1 = new LinearLayoutManager(context);
        layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler.setLayoutManager(layoutManager1);
        recycler.setAdapter(gameThumbRecyclerViewAdapter);

        userVideoListAdapter = new UserVideoListAdapter(context);
        layoutManager2 = new LinearLayoutManager(context);
        layoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerList.setLayoutManager(layoutManager2);
        recyclerList.setAdapter(userVideoListAdapter);
        recyclerList.setHasFixedSize(true);
        recyclerList.setNestedScrollingEnabled(false);

    }
}
