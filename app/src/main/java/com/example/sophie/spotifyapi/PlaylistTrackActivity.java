package com.example.sophie.spotifyapi;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.sophie.spotifyapi.databinding.ActivityMainBinding;
import com.example.sophie.spotifyapi.databinding.ActivityPlaylistTrackBinding;
import com.google.gson.Gson;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;

public class PlaylistTrackActivity extends AppCompatActivity implements  Results, OnTrackResultsSelectedInterface{
    private RecyclerView.Adapter mAdapter;
    private ArrayList<TrackResult> trackResults = new ArrayList<>();
    private HttpGetRequest getRequest;
    private Gson gson = new Gson();
    private MediaPlayer mediaPlayer= new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_track);

        Intent mainIntent = getIntent();
        final PlaylistResult result = Parcels.unwrap(mainIntent.getParcelableExtra("SELECTED_RESULT"));

        String url = result.getTracklist();
        ImageButton stopButton = findViewById(R.id.main9_stop);
        RecyclerView rv = findViewById(R.id.main9_rv);
        rv.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter2(trackResults, this);
        rv.setAdapter(mAdapter);
        DividerItemDecoration id = new DividerItemDecoration(this, mLayoutManager.getOrientation());
        rv.addItemDecoration(id);

        if (getRequest != null && !getRequest.isCancelled()){
            getRequest.cancel(true);
        }
        if (trackResults.size() != 0) {
            trackResults.clear();
        }

        getRequest = new HttpGetRequest(this);
        getRequest.execute(url);

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(MyMediaPlayer.getMediaPlayerInstance().mediaPlayer.isPlaying()) {
                    MyMediaPlayer.getMediaPlayerInstance().pauseAudioFile();
                    // mediaPlayer.reset();
                }
                else if (!MyMediaPlayer.getMediaPlayerInstance().mediaPlayer.isPlaying()) {
                    MyMediaPlayer.getMediaPlayerInstance().mediaPlayer.start();

                }
           }
        });

    }


    @Override
    public void onResultSelected(TrackResult trackResult)  {
//        try {
        MainActivity mainActivity = new MainActivity();
            String music = trackResult.getPreview();
            Intent intent = new Intent(PlaylistTrackActivity.this,MyMediaPlayer.class);

        intent.putExtra("key",music);
            //mediaPlayer.stop();
//            mediaPlayer.reset();
//            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//            mediaPlayer.setDataSource(music);
//            mediaPlayer.prepare();
           //mediaPlayer.start();
        MyMediaPlayer.getMediaPlayerInstance().stopAudioFile();
            MyMediaPlayer.getMediaPlayerInstance().playAudioFile(this,music);

//        }catch(IOException e){
//            e.printStackTrace();
//        }
    }

    @Override
    public void handleResult(String result) {
        TrackResponse responseTrack = gson.fromJson(result, TrackResponse.class);
        trackResults.addAll(responseTrack.getData());
        mAdapter.notifyDataSetChanged();
    }


}
