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

import java.util.List;

public class UserGameThumbRecyclerViewAdapter extends RecyclerView.Adapter<UserGameThumbRecyclerViewAdapter.ViewHolder>{
    private Context context;
    private MyItemOnClickListener mMyItemOnClickListener;
    private int[] imgs = {R.mipmap.icon_add};
    private List<UserInfoEntity.DataBean.FieldPersonalpicshowBean> personalpicshowBeanList;
    private int listSize = 0;
    public UserGameThumbRecyclerViewAdapter(Context context,List<UserInfoEntity.DataBean.FieldPersonalpicshowBean> personalpicshowBeanList) {
        this.context = context;
        this.personalpicshowBeanList = personalpicshowBeanList;
        listSize = personalpicshowBeanList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_game_thumb,parent,false);
        return new ViewHolder(view,mMyItemOnClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(position+1 >  listSize && listSize < 4){
            holder.imgThumb.setImageResource(imgs[0]);
        }else {
            Glide.with(context)
                    .load(personalpicshowBeanList.get(position).getUrl())
                    .into(holder.imgThumb);
        }
    }

    @Override
    public int getItemCount() {
        return (listSize >= 4) ? listSize : listSize+1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgThumb;
        MyItemOnClickListener mListener;
        public ViewHolder(View itemView,MyItemOnClickListener myItemOnClickListener) {
            super(itemView);
            imgThumb = itemView.findViewById(R.id.img_game_thumb);
            this.mListener = myItemOnClickListener;
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
