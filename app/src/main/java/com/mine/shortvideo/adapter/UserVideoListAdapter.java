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
import com.mine.shortvideo.myInterface.MyItemOnClickListener;

public class UserVideoListAdapter extends RecyclerView.Adapter<UserVideoListAdapter.ViewHolder>{
    private Context context;
    private MyItemOnClickListener mMyItemOnClickListener;
    private int[] imgs = {R.mipmap.img_list_example_thumb_1,R.mipmap.img_list_example_thumb_0,R.mipmap.img_list_example_thumb_1,R.mipmap.add_bg};

    public UserVideoListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_list_video,parent,false);
        return new ViewHolder(view,mMyItemOnClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imgUserVideoListItemThumb.setImageResource(imgs[position]);
    }

    @Override
    public int getItemCount() {
        return imgs.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgUserVideoListItemThumb;
        MyItemOnClickListener mListener;
        public ViewHolder(View itemView, MyItemOnClickListener myItemOnClickListener) {
            super(itemView);
            this.mListener = myItemOnClickListener;
            imgUserVideoListItemThumb = itemView.findViewById(R.id.img_user_video_list_item_thumb);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mListener!=null){
                mListener.onItemOnClick(view,getLayoutPosition());
            }
        }
    }
    public void setItemOnClickListener(MyItemOnClickListener listener){
        this.mMyItemOnClickListener = listener;
    }
}
