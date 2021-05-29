package com.pradeep.new014.ui.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pradeep.new014.R;

import java.util.ArrayList;

class RecyclerAdapterVideoView extends RecyclerView.Adapter<RecyclerAdapterVideoView.MyViewHolder> {
    public Context mContext;
    private ArrayList<Video> mData;

    public RecyclerAdapterVideoView(Context mContext, ArrayList<Video> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater= LayoutInflater.from(mContext);
        view= mInflater.inflate(R.layout.video_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.titleVideo.setText(mData.get(position).getDes());
        holder.videoView.setVideoPath(mData.get(position).getLink());
        MediaController mediaController= new MediaController(mContext);
        mediaController.setAnchorView(holder.videoView);
        holder.videoView.setMediaController(mediaController);
        holder.videoView.requestFocus();
        holder.videoView.start();
        holder.context=mContext;
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView titleVideo;
        VideoView videoView;
        Context context;
        //CardView cardViewVideo;
        public MyViewHolder(View itemView){
            super(itemView);

            titleVideo=(TextView)itemView.findViewById(R.id.titleforVodeo);
            videoView=(VideoView) itemView.findViewById(R.id.videoView_layout);
            //cardViewVideo=(CardView)itemView.findViewById(R.id.VideoCardView);

            //MediaController mediaController= new MediaController(context);
            ///mediaController.setAnchorView(videoView);
            //videoView.setMediaController(mediaController);

        }
    }
}
