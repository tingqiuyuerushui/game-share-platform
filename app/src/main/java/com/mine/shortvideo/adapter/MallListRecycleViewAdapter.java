package com.mine.shortvideo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mine.shortvideo.R;
import com.mine.shortvideo.myInterface.MyItemOnClickListener;
import com.mine.shortvideo.myInterface.MyItemOnLongClickListener;

/**
 * 作者：created by lun.zhang on 11/22/2018 11:06
 * 邮箱：zhanglun_study@163.com
 */
public class MallListRecycleViewAdapter extends RecyclerView.Adapter<MallListRecycleViewAdapter.ViewHolder> {
    private Context context;
    private MyItemOnClickListener mMyItemOnClickListener;
    private MyItemOnLongClickListener myItemOnLongClickListener;

    public MallListRecycleViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mall_list,parent,false);
        return new MallListRecycleViewAdapter.ViewHolder(view,mMyItemOnClickListener,myItemOnLongClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        ImageView imgThumb;
        MyItemOnClickListener mListener;
        MyItemOnLongClickListener myLongClickListener;
        public ViewHolder(View itemView,MyItemOnClickListener myItemOnClickListener,MyItemOnLongClickListener myItemOnLongClickListener) {
            super(itemView);
            imgThumb = itemView.findViewById(R.id.img_product);
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
