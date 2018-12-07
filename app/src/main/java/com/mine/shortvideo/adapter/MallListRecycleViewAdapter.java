package com.mine.shortvideo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mine.shortvideo.R;
import com.mine.shortvideo.constant.Const;
import com.mine.shortvideo.entity.AllGoodsEntity;
import com.mine.shortvideo.myInterface.MyItemOnClickListener;
import com.mine.shortvideo.myInterface.MyItemOnLongClickListener;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：created by lun.zhang on 11/22/2018 11:06
 * 邮箱：zhanglun_study@163.com
 */
public class MallListRecycleViewAdapter extends RecyclerView.Adapter<MallListRecycleViewAdapter.ViewHolder> {
    private Context context;
    private MyItemOnClickListener mMyItemOnClickListener;
    private MyItemOnLongClickListener myItemOnLongClickListener;
    private List<AllGoodsEntity.DataBean> list;

    public MallListRecycleViewAdapter(Context context,List<AllGoodsEntity.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mall_list,parent,false);
        return new MallListRecycleViewAdapter.ViewHolder(view,mMyItemOnClickListener,myItemOnLongClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvProductName.setText(list.get(position).getTitle());
        Glide.with(context)
                .load(Const.baseUrl + list.get(position).getField_c_goods_cover())
                .into(holder.imgThumb);
        holder.tvIntegral.setText(new BigDecimal(list.get(0).getField_c_goods_price()).stripTrailingZeros()+"积分");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        ImageView imgThumb;
        TextView tvProductName;
        TextView tvIntegral;
        MyItemOnClickListener mListener;
        MyItemOnLongClickListener myLongClickListener;
        public ViewHolder(View itemView,MyItemOnClickListener myItemOnClickListener,MyItemOnLongClickListener myItemOnLongClickListener) {
            super(itemView);
            imgThumb = itemView.findViewById(R.id.img_product);
            tvProductName = itemView.findViewById(R.id.tv_product_name);
            tvIntegral = itemView.findViewById(R.id.tv_integral);
            this.mListener = myItemOnClickListener;
            this.myLongClickListener = myItemOnLongClickListener;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mListener!=null){
                mListener.onItemOnClick(view,getLayoutPosition());
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if(myLongClickListener != null){
                myLongClickListener.onItemOnLongClick(view,getLayoutPosition());
            }
            return true;
        }
    }
    public void setItemOnClickListener(MyItemOnClickListener listener){
        this.mMyItemOnClickListener = listener;
    }
    public void setItemOnLongClickListener(MyItemOnLongClickListener listener){
        this.myItemOnLongClickListener = listener;
    }
}
