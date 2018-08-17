package com.mine.shortvideo.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lun.zhang on 12/13/2017.
 */

public class FragmentTabAdapter {
    private List<Fragment> fragments;
    private FragmentActivity fragmentActivity;
    private int fragmentContentId; // Activity中所要被替换的区域的id
    private int currentTab = 0; // 当前Tab页面索引
    private ArrayList<String> FragmentTagList;//给fragment设置标签方便Activity找到
    public FragmentTabAdapter(FragmentActivity fragmentActivity, List<Fragment> fragments, int fragmentContentId, ArrayList<String> FragmentTagList) {
        this.fragments = fragments;
        this.fragmentActivity = fragmentActivity;
        this.fragmentContentId = fragmentContentId;
        this.FragmentTagList = FragmentTagList;
        // 默认显示第一个fragment
        fragmentActivity.getSupportFragmentManager().beginTransaction().add(fragmentContentId,fragments.get(0)).commit();
//        FragmentTransaction ft = fragmentActivity.getFragmentManager().beginTransaction();
//        ft.add(fragmentContentId, fragments.get(0),FragmentTagList.get(0));
//        ft.commit();
    }
    public void getRadioGroup(int i) {
        Fragment fragment = fragments.get(i);
        FragmentTransaction ft = obtainFragmentTransaction(i);
        getCurrentFragment().onPause(); // 暂停当前tab
        //                getCurrentFragment().onStop(); // 暂停当前tab
        if(fragment.isAdded()){
            //                    fragment.onStart(); // 启动目标tab的onStart()
            fragment.onResume(); // 启动目标tab的onResume()
        }else{
            ft.add(fragmentContentId, fragment,FragmentTagList.get(i));
        }
        showTab(i); // 显示目标tab
        ft.commitAllowingStateLoss();
    }
    /**
     * 切换tab
     * @param idx
     */
    private void showTab(int idx){
        for(int i = 0; i < fragments.size(); i++){
            Fragment fragment = fragments.get(i);
            FragmentTransaction ft = obtainFragmentTransaction(idx);
            if(idx == i){
                ft.show(fragment);
            }else{
                ft.hide(fragment);
            }
            ft.commit();
        }
        currentTab = idx; // 更新目标tab为当前tab
    }

    /**
     * 获取�?个带动画的FragmentTransaction
     * @param index
     * @return
     */
    @SuppressLint("ResourceType")
    private FragmentTransaction obtainFragmentTransaction(int index){
        FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
//        // 设置切换动画
//        if(index > currentTab){
//            ft.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out);
//        }else{
//            ft.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_out);
//        }
        return ft;
    }

    public int getCurrentTab() {
        return currentTab;
    }

    public Fragment getCurrentFragment(){
        return fragments.get(currentTab);
    }

}
