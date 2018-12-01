package com.mine.shortvideo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mine.shortvideo.R;

/**
 * 作者：created by lun.zhang on 12/1/2018 16:15
 * 邮箱：zhanglun_study@163.com
 */
public class ProductDetailsRecycleViewAdapter extends RecyclerView.Adapter<ProductDetailsRecycleViewAdapter.ViewHolder> {
    private Context context;

    public ProductDetailsRecycleViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_product_details,parent,false);
        return new ProductDetailsRecycleViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.imgThumb.setImageResource();

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgThumb;
        public ViewHolder(View itemView) {
            super(itemView);
            imgThumb = itemView.findViewById(R.id.img_product_details);
        }
    }
}
