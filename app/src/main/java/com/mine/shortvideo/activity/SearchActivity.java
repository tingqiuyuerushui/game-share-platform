package com.mine.shortvideo.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.mine.shortvideo.R;
import com.mine.shortvideo.adapter.UserVideoListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends Activity {
    @BindView(R.id.edit_search)
    EditText editSearch;
    @BindView(R.id.recycler_list)
    RecyclerView recyclerList;
    private Context context;
    private UserVideoListAdapter userVideoListAdapter;
    private LinearLayoutManager layoutManager;
    private static final String TAG = "SearchActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        context = this;
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        userVideoListAdapter = new UserVideoListAdapter(context);
        layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerList.setLayoutManager(layoutManager);
        recyclerList.setAdapter(userVideoListAdapter);
        recyclerList.setHasFixedSize(true);
        recyclerList.setNestedScrollingEnabled(false);
    }
}
