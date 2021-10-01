package com.example.sophie.spotifyapi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyAdapter4 extends RecyclerView.Adapter<MyAdapter4.ViewHolder> {

    private ArrayList<PlaylistResult> mDataset;
    private OnPlaylistResultSelectedInterface resultsInterface;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView titleTextView;
        public ImageView pictureImageView;

        public ViewHolder(View v){
            super(v);
            titleTextView = v.findViewById(R.id.playlist_title);
            pictureImageView = v.findViewById(R.id.playlist_picture);
        }
    }

    public MyAdapter4(Context context, ArrayList<PlaylistResult> myDataset, OnPlaylistResultSelectedInterface resultsInterface){

        mDataset = myDataset;
        this.resultsInterface = resultsInterface;
        this.context = context;
    }

    @Override
    public MyAdapter4.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.playlist_result, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        final PlaylistResult result = mDataset.get(position);
        holder.titleTextView.setText(result.getTitle());

        Glide.with(context).load(result.getPicture()).into(holder.pictureImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultsInterface.onResultSelected(result);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

