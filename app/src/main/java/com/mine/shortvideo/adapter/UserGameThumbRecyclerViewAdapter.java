package com.mine.shortvideo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mine.shortvideo.R;
import com.mine.shortvideo.entity.UserInfoEntity;
import com.mine.shortvideo.myInterface.MyItemOnClickListener;
import com.mine.shortvideo.myInterface.MyItemOnLongClickListener;

import java.util.List;

public class UserGameThumbRecyclerViewAdapter extends RecyclerView.Adapter<UserGameThumbRecyclerViewAdapter.ViewHolder>{
    private Context context;
    private MyItemOnClickListener mMyItemOnClickListener;
    private MyItemOnLongClickListener myItemOnLongClickListener;
    private int[] imgs = {R.mipmap.icon_add};
    private List<UserInfoEntity.DataBean.FieldPersonalpicshowBean> personalpicshowBeanList;
    private int listSize = 0;
    private boolean isViewUserInfo = false;
    public UserGameThumbRecyclerViewAdapter(Context context,boolean isViewUserInfo,List<UserInfoEntity.DataBean.FieldPersonalpicshowBean> personalpicshowBeanList) {
        this.context = context;
        this.isViewUserInfo = isViewUserInfo;
        this.personalpicshowBeanList = personalpicshowBeanList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_game_thumb,parent,false);
        return new ViewHolder(view,mMyItemOnClickListener,myItemOnLongClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        listSize = personalpicshowBeanList.size();
        if(position+1 >  listSize && listSize < 4 && !isViewUserInfo){
            holder.imgThumb.setImageResource(imgs[0]);
        }else {
            Glide.with(context)
                    .load(personalpicshowBeanList.get(position).getUrl())
                    .into(holder.imgThumb);
        }
    }

    @Override
    public int getItemCount() {
        if (isViewUserInfo){
            return personalpicshowBeanList.size();
        }else {
            return (personalpicshowBeanList.size() >= 4) ? personalpicshowBeanList.size() : (personalpicshowBeanList.size()+1);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        ImageView imgThumb;
        MyItemOnClickListener mListener;
        MyItemOnLongClickListener myLongClickListener;
        public ViewHolder(View itemView,MyItemOnClickListener myItemOnClickListener,MyItemOnLongClickListener myItemOnLongClickListener) {
            super(itemView);
            imgThumb = itemView.findViewById(R.id.img_game_thumb);
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
