package com.mine.shortvideo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mine.shortvideo.R;

/**
 * 作者：created by lun.zhang on 12/5/2018 14:41
 * 邮箱：zhanglun_study@163.com
 */
public class ReleativeFragment extends Fragment {


    public ReleativeFragment() {
        // Required empty public constructor
    }


    private static ReleativeFragment fragment = null;

    public static ReleativeFragment newInstance() {
        if (fragment == null) {
            fragment = new ReleativeFragment();
        }
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_goods_comment, container, false);
    }

}
