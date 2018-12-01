package com.mine.shortvideo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.mine.shortvideo.R;

/**
 * 作者：created by lun.zhang on 11/27/2018 15:59
 * 邮箱：zhanglun_study@163.com
 */
public class ProductDetailsPagerAdapter extends PagerAdapter {
    private Context context;
    private int[] imgs;

    public ProductDetailsPagerAdapter(Context context, int[] imgs) {
        this.context = context;
        this.imgs = imgs;
    }

    @Override
    public int getCount() {
        return imgs != null ? imgs.length : 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product_details_pager_adapter, null);
        ImageView itemImg = view.findViewById(R.id.item_product_detail_pager);
        itemImg.setImageResource(imgs[position]);
        container.addView(view);
        view.requestLayout();
        return view;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
