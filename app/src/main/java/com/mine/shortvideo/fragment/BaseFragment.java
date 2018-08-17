package com.mine.shortvideo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mine.shortvideo.utils.ToastUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lun.zhang on 12/14/2017.
 */

public abstract class BaseFragment extends Fragment {

    public View rootView;
    public LayoutInflater inflater;
    public Unbinder unbinder;

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        this.inflater = inflater;
        if (rootView == null) {
            rootView = inflater.inflate(this.getLayoutId(), container, false);
            unbinder = ButterKnife.bind(this,rootView);
            init(savedInstanceState);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    protected abstract int getLayoutId();

    protected abstract void init(Bundle savedInstanceState);

    protected void showToast(String msg) {
        ToastUtils.show(msg);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
