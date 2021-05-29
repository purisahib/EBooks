package com.pradeep.new014.ui.contener1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.pradeep.new014.R;
import com.pradeep.new014.ui.contener.LayoutContainer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapterView extends RecyclerView.Adapter<RecyclerAdapterView.MyViewHolder> {

    private Context mContext;
    private ArrayList<Title> mData;

    public RecyclerAdapterView(Context mContext, ArrayList<Title> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater= LayoutInflater.from(mContext);
        view= mInflater.inflate(R.layout.cardview_item_book,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_book_title.setText(mData.get(position).getName());
        Picasso.get().load(mData.get(position).getPick()).into(holder.img_book_thumbnail);
        //holder.img_book_thumbnail.setImageResource(mData.get(position).getThumbnail());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(mContext, LayoutContainer.class);
                LayoutContainer layoutContainer= new LayoutContainer();
                layoutContainer.startOnActivityLayoutContainer(mData.get(position).getName());

                //start the activity
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_book_title;
        ImageView img_book_thumbnail;
        CardView cardView;

        public MyViewHolder(View itemView){
            super(itemView);

            tv_book_title=(TextView)itemView.findViewById(R.id.book_title_id);
            img_book_thumbnail=(ImageView)itemView.findViewById(R.id.book_img_id);
            cardView=(CardView)itemView.findViewById(R.id.cardview_id);
        }
    }
}

