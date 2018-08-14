package com.mine.shortvideo.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.mine.shortvideo.R;
import com.mine.shortvideo.activity.MinePlayVideoActivity;
import com.mine.shortvideo.activity.SelectVideoListActivity;
import com.mine.shortvideo.adapter.UserGameThumbRecyclerViewAdapter;
import com.mine.shortvideo.adapter.UserVideoListAdapter;
import com.mine.shortvideo.constant.Const;
import com.mine.shortvideo.customview.CommomDialog;
import com.mine.shortvideo.myInterface.MyItemOnClickListener;
import com.mine.shortvideo.photopicker.PhotoPicker;
import com.mine.shortvideo.utils.Code;
import com.mine.shortvideo.utils.OkHttpUtils;
import com.mine.shortvideo.utils.ToastUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Request;

import static android.app.Activity.RESULT_OK;


public class MineFragment extends BaseFragment {
    @BindView(R.id.picture_recycler)
    RecyclerView pictureRecycler;
    @BindView(R.id.video_recycler)
    RecyclerView videoRecycler;
    @BindView(R.id.btn_share)
    ImageView btnShare;
    @BindView(R.id.btn_delete)
    ImageView btnDelete;

    private Context context;
    private UserGameThumbRecyclerViewAdapter gameThumbRecyclerViewAdapter;
    private UserVideoListAdapter userVideoListAdapter;
    private LinearLayoutManager layoutManager1;
    private LinearLayoutManager layoutManager2;
    private ArrayList<String> photos;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        context = getActivity();
        gameThumbRecyclerViewAdapter = new UserGameThumbRecyclerViewAdapter(context);
        layoutManager1 = new LinearLayoutManager(context);
        layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        pictureRecycler.setLayoutManager(layoutManager1);
        pictureRecycler.setAdapter(gameThumbRecyclerViewAdapter);
        gameThumbRecyclerViewAdapter.setItemOnClickListener(new MyItemOnClickListener() {
            @Override
            public void onItemOnClick(View view, int postion) {
                ToastUtils.show("点击了" + postion);
                if (postion == 3) {

                    PhotoPicker.builder()
                            .setPhotoCount(9)
                            .setShowCamera(true)
                            .setShowGif(true)
                            .setPreviewEnabled(false)
                            .start(context, MineFragment.this, PhotoPicker.REQUEST_CODE);
                } else {
                    Intent intent = new Intent(getActivity(), SelectVideoListActivity.class);
                    startActivityForResult(intent, 101);
//                    PhotoPreview.builder()
//                            .setPhotos(photos)
//                            .setCurrentItem(0)
//                            .start(getActivity());
                }
            }
        });
        userVideoListAdapter = new UserVideoListAdapter(context);
        layoutManager2 = new LinearLayoutManager(context);
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        videoRecycler.setLayoutManager(layoutManager2);
        videoRecycler.setAdapter(userVideoListAdapter);
        userVideoListAdapter.setItemOnClickListener(new MyItemOnClickListener() {
            @Override
            public void onItemOnClick(View view, int postion) {
                if (postion == 3) {
                    Intent intent = new Intent(getActivity(), SelectVideoListActivity.class);
                    startActivityForResult(intent, Code.LOCAL_VIDEO_REQUEST);
                } else {
                    Intent intent = new Intent();
                    intent.setClass(context, MinePlayVideoActivity.class);
                    intent.putExtra("VideoIndex", postion);
                    startActivity(intent);
                }
            }
        });
    }

    private Map<String, String> addParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("vin", "111111");
        return params;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                OkHttpUtils.postFileAsync(Const.uploadUrl, addParams(), photos.get(0), new OkHttpUtils.DataCallBack() {
                    @Override
                    public void requestFailure(Request request, IOException e) {

                    }

                    @Override
                    public void requestSuccess(String result) throws Exception {

                    }
                });
            }
        } else if (requestCode == Code.LOCAL_VIDEO_REQUEST && resultCode == Code.LOCAL_VIDEO_RESULT) {
            String filPaths = data.getStringExtra("path");
            Log.e("video Path", filPaths);
        }
    }

    @OnClick({R.id.btn_share, R.id.btn_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_share:
                break;
            case R.id.btn_delete:
                new CommomDialog(context, R.style.dialog, "确定删除", new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if(confirm){
                            Toast.makeText(context,"点击确定", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                }).setTitle("删除提示").show();
                break;
        }
    }
}
