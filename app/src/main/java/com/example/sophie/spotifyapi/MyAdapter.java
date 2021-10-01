package com.example.sophie.spotifyapi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private final Context context;
    private ArrayList<DeezerResult> mDataset;
    private OnResultSelectedInterface resultsInterface;
    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;


    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView nameTextView;
        public ImageView coverImageView;
        public TextView typeTextView;
        public TextView artistTextView;

        public ViewHolder (View v){
            super(v);
            nameTextView = v.findViewById(R.id.item_title);
            coverImageView = v.findViewById(R.id.item_image);
            typeTextView = v.findViewById(R.id.item_type);
            artistTextView = v.findViewById(R.id.item_artist);
        }
    }

    public MyAdapter(Context context, ArrayList<DeezerResult> mDataset, OnResultSelectedInterface resultsInterface) {
        this.context = context;
        this.mDataset = mDataset;
        this.resultsInterface = resultsInterface;
    }


    @Override
    public MyAdapter.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        final DeezerResult result = mDataset.get(position);
        holder.nameTextView.setText(result.getTitle());
        holder.typeTextView.setText(result.getType());
        holder.artistTextView.setText(result.artist.getName());

        Glide.with(context).load(result.getCover_small()).into(holder.coverImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultsInterface.onResultSelected(result);
            }
        });
    }

    @Override
    public int getItemCount(){
        return mDataset.size();
    }
}

