package com.example.sophie.spotifyapi;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder>{

    private final static String TAG = "LogTag";

    private ArrayList<TrackResult> mDataset;
    private ArrayList<DeezerResult> myDeezerResults;
    private OnTrackResultsSelectedInterface resultsInterface;
    private String mduration;
    private String sduration;



    public static class ViewHolder extends RecyclerView.ViewHolder {


        public TextView titleTextView;
        public TextView durationTextView;
        public TextView artistTextView;


        public ViewHolder(View v){
            super(v);
            titleTextView = v.findViewById(R.id.track_title);
            durationTextView = v.findViewById(R.id.track_duration);
            artistTextView = v.findViewById(R.id.track_artist);
        }
    }

    public MyAdapter2(ArrayList<TrackResult> myDataset, OnTrackResultsSelectedInterface resultsInterface){

        mDataset = myDataset;
        this.resultsInterface = resultsInterface;
    }

    @Override
    public MyAdapter2.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.track_result, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        final TrackResult result = mDataset.get(position);
        holder.titleTextView.setText(result.getTitle());
        String s = result.getDuration().toString();

        Log.d(TAG, s);
        if (s.length()==3) {
            mduration = s.substring(0, 1);
            sduration = s.substring(1, 3);
        }else if(s.length()==4) {
            mduration = s.substring(0, 2);
            sduration = s.substring(2, 4);
        }else{
            mduration = "0";
            sduration = s;
        }
        holder.durationTextView.setText(mduration+":"+sduration);
        holder.artistTextView.setText(result.artist.getName());


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
