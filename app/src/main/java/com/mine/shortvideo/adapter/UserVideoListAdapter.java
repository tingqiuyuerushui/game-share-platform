package com.mine.shortvideo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mine.shortvideo.R;
import com.mine.shortvideo.entity.MyVideoEntity;
import com.mine.shortvideo.myInterface.MyItemOnClickListener;
import com.mine.shortvideo.myInterface.MyItemOnLongClickListener;

import java.util.List;

public class UserVideoListAdapter extends RecyclerView.Adapter<UserVideoListAdapter.ViewHolder>{
    private Context context;
    private MyItemOnClickListener mMyItemOnClickListener;
    private MyItemOnLongClickListener myItemOnLongClickListener;
    private int[] imgs = {R.mipmap.img_list_example_thumb_1,R.mipmap.img_list_example_thumb_0,R.mipmap.timg_0,R.mipmap.timg_1};
    private List<MyVideoEntity.DataBean> myVideoList;
    public UserVideoListAdapter(Context context,List<MyVideoEntity.DataBean> myVideoList) {
        this.context = context;
        this.myVideoList = myVideoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_list_video,parent,false);
        return new ViewHolder(view,mMyItemOnClickListener,myItemOnLongClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(position == 0){
            holder.imgAdd.setVisibility(View.VISIBLE);
            holder.imgUserVideoListItemThumb.setVisibility(View.GONE);
            holder.imgAdd.setImageResource(R.mipmap.icon_add);
        }else{
            holder.imgAdd.setVisibility(View.GONE);
            holder.imgUserVideoListItemThumb.setVisibility(View.VISIBLE);
            holder.imgUserVideoListItemThumb.setImageResource(imgs[position % 4]);
        }
    }

    @Override

    public int getItemCount() {
        return myVideoList.size() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        ImageView imgUserVideoListItemThumb;
        ImageView imgAdd;
        MyItemOnClickListener mListener;
        MyItemOnLongClickListener myItemOnLongClickListener;
        public ViewHolder(View itemView, MyItemOnClickListener myItemOnClickListener,MyItemOnLongClickListener myItemOnLongClickListener) {
            super(itemView);
            this.mListener = myItemOnClickListener;
            this.myItemOnLongClickListener = myItemOnLongClickListener;
            imgUserVideoListItemThumb = itemView.findViewById(R.id.img_user_video_list_item_thumb);
            imgAdd = itemView.findViewById(R.id.img_add);
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
            if(myItemOnLongClickListener != null){
                myItemOnLongClickListener.onItemOnLongClick(view,getLayoutPosition());
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
