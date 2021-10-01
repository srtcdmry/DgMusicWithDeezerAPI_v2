package com.example.sophie.spotifyapi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyAdapter3 extends RecyclerView.Adapter<MyAdapter3.ViewHolder>{

    private ArrayList<ArtistResult> mDataset;
    private OnArtistResultSelectedInterface resultsInterface;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView nameTextView;
        public ImageView pictureImageView;

        public ViewHolder(View v){
            super(v);
            nameTextView = v.findViewById(R.id.artist_name);
            pictureImageView = v.findViewById(R.id.artist_picture);
        }
    }

    public MyAdapter3(Context context, ArrayList<ArtistResult> myDataset, OnArtistResultSelectedInterface resultsInterface){

        mDataset = myDataset;
        this.resultsInterface = resultsInterface;
        this.context = context;
    }

    @Override
    public MyAdapter3.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_result, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        final ArtistResult result = mDataset.get(position);
        holder.nameTextView.setText(result.getName());

        Glide.with(context).load(result.getPicture()).into(holder.pictureImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                resultsInterface.onResultSelected(result);
            }
        });
    }

    @Override
    public int getItemCount(){
        return mDataset.size();
    }
}
