package com.mine.shortvideo.transformer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.mine.shortvideo.utils.ScreenUtils;


/**
 * 作者：created by lun.zhang on 12/12/2018 11:06
 * 邮箱：zhanglun_study@163.com
 */
public class DepthPageTransformer extends VerticalBaseTransformer {
    private Context context;
    private static final float MIN_SCALE = 0.75F; // 最小缩放比例
    private int spaceBetweenFirAndSecWith = 15 * 2;//第一张卡片和第二张卡片宽度差  dp单位

    public DepthPageTransformer(Context context) {
        this.context = context;
    }

    @Override
    protected void onTransform(View page, float position) {
        if (position <= 0.0f) {
            page.setAlpha(1.0f + position);
            page.setTranslationY(0f);
            //控制停止滑动切换的时候，只有最上面的一张卡片可以点击
            page.setClickable(true);
        } else if (position <= 2.0f) {
            float scale = (float) (page.getWidth() - ScreenUtils.dp2px(context, spaceBetweenFirAndSecWith * position)) / (float) (page.getWidth());
            //控制下面卡片的可见度
            page.setAlpha(0.4f);
            page.setClickable(false);
            page.setPivotX(page.getWidth() / 2f);
            page.setPivotY(page.getHeight() / 2f);
            page.setScaleY((float) Math.pow(1.15f, position));
            page.setScaleX(scale);
        }
    }

}