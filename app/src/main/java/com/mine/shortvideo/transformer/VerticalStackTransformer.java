package com.mine.shortvideo.transformer;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.mine.shortvideo.R;
import com.mine.shortvideo.utils.ScreenUtils;

import timber.log.Timber;


/**
 * Created by Nate on 2016/7/22.
 */
public class VerticalStackTransformer extends VerticalBaseTransformer {

    private Context context;
    private int spaceBetweenFirAndSecWith = 15 * 2;//第一张卡片和第二张卡片宽度差  dp单位
    private int spaceBetweenFirAndSecHeight = 20;//第一张卡片和第二张卡片高度差   dp单位
    public static final float MAX_SCALE = 1.0f;
    public static final float MIN_SCALE = 0.5f;
    public VerticalStackTransformer(Context context) {
        this.context = context;
    }

    public VerticalStackTransformer(Context context, int spaceBetweenFirAndSecWith, int spaceBetweenFirAndSecHeight) {
        this.context = context;
        this.spaceBetweenFirAndSecWith = spaceBetweenFirAndSecWith;
        this.spaceBetweenFirAndSecHeight = spaceBetweenFirAndSecHeight;
    }

    @Override
    protected void onTransform(View page, float position) {
        LinearLayout llCardView = page.findViewById(R.id.ll_cardview);
        if (position <= 0.0f) {
            llCardView.setVisibility(View.VISIBLE);
            page.setAlpha(1.0f + position);
            Timber.e("position--<= 0.0f >>"+position);
//            Log.e("onTransform", "position <= 0.0f ==>" + position);
            page.setTranslationY(0f);
            page.setScaleX(1+position);
            page.setScaleY(1+position);
            page.setTranslationX(-page.getWidth() / 2 * position);
            //控制停止滑动切换的时候，只有最上面的一张卡片可以点击
            page.setClickable(true);
        } else if (position <= 2.0f) {
            llCardView.setVisibility(View.INVISIBLE);
//            Log.e("onTransform", "position <= 3.0f ==>" + position);
            float scale = (float) (page.getWidth() - ScreenUtils.dp2px(context, spaceBetweenFirAndSecWith * position)) / (float) (page.getWidth());
            //控制下面卡片的可见度
            page.setAlpha(0.4f);
            //控制停止滑动切换的时候，只有最上面的一张卡片可以点击
            page.setClickable(false);
            page.setPivotX(page.getWidth() / 2f);
            page.setPivotY(page.getHeight() / 2f);
//            Timber.e("Y-->>"+(float) Math.pow(1.2f,position));
////            Timber.e("X-->>"+scale);
            Timber.e("position--<= 2.0f  >>"+position);
//            page.setScaleY(Math.abs(position-1));
            if (position == 1.0f || position == 2.0f){
                page.setScaleY((float) Math.pow(1.15f,position));
                page.setScaleX(scale);
            }else if (position < 1.0f){
                page.setScaleY(Math.max(MIN_SCALE, position));
                page.setScaleX(Math.max(MIN_SCALE,position-0.2f));
            }else {
                page.setScaleY(Math.max(MIN_SCALE+0.1f,position-1 + 0.1f));
                page.setScaleX(Math.max(MIN_SCALE+0.1f,position-1.2f + 0.1f));
            }
//            page.setTranslationY(-page.getHeight() * position + (page.getHeight() * 0.5f) * (1 - scale));
//            page.setScaleY(scale);
//            page.setTranslationY(-page.getHeight() * position + (page.getHeight() * 0.5f) * (1 - scale) + ScreenUtils.dp2px(context, spaceBetweenFirAndSecHeight) * position);
        }
    }
}
