package com.mine.shortvideo.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.mine.shortvideo.R;

public class VideoRecycleViewAdapter extends RecyclerView.Adapter<VideoRecycleViewAdapter.ViewHolder>{
    private int[] imgs = {R.mipmap.img_video_1,R.mipmap.timg_0,R.mipmap.timg_2};
    private int[] videos = {R.raw.video_1,R.raw.video_3,R.raw.video_2};
    private Context context;
    public VideoRecycleViewAdapter(Context context){
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_view_pager,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.img_thumb.setImageResource(imgs[position]);
        holder.videoView.setVideoURI(Uri.parse("android.resource://"+context.getPackageName()+"/"+ videos[position]));
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img_thumb;
        VideoView videoView;
        ImageView img_play;
        RelativeLayout rootView;
        public ViewHolder(View itemView) {
            super(itemView);
            img_thumb = itemView.findViewById(R.id.img_thumb);
            videoView = itemView.findViewById(R.id.video_view);
            img_play = itemView.findViewById(R.id.img_play);
            rootView = itemView.findViewById(R.id.root_view);
        }
    }
}
