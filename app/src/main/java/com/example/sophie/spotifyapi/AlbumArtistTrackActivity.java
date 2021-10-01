package com.example.sophie.spotifyapi;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.example.sophie.spotifyapi.databinding.ActivityAlbumArtistTrackBinding;
import com.example.sophie.spotifyapi.databinding.ActivityMainBinding;
import com.google.gson.Gson;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;

public class AlbumArtistTrackActivity extends AppCompatActivity implements Results, OnTrackResultsSelectedInterface {
    private ActivityAlbumArtistTrackBinding binding;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<TrackResult> trackResults = new ArrayList<>();
    private HttpGetRequest getRequest;
    private Gson gson = new Gson();
    private MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAlbumArtistTrackBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Intent mainIntent = getIntent();
        final DeezerResult result = Parcels.unwrap(mainIntent.getParcelableExtra("SELECTED_RESULT"));

        String url = result.artist.getTracklist();
        //ImageButton stopButton = findViewById(R.id.main4_stop);
        //RecyclerView rv = findViewById(R.id.main4_rv);
        binding.main4Rv.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        binding.main4Rv.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter2(trackResults, this);
        binding.main4Rv.setAdapter(mAdapter);
        DividerItemDecoration id = new DividerItemDecoration(this, mLayoutManager.getOrientation());
        binding.main4Rv.addItemDecoration(id);

        if (getRequest != null && !getRequest.isCancelled()){
            getRequest.cancel(true);
        }
        if (trackResults.size() != 0) {
            trackResults.clear();
        }

        getRequest = new HttpGetRequest(this);
        getRequest.execute(url);

        binding.main4Stop.setOnClickListener(new View.OnClickListener() {
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
    public void onResultSelected(TrackResult trackResult) {

//        try {
            String music = trackResult.getPreview();
        MyMediaPlayer.getMediaPlayerInstance().stopAudioFile();
        MyMediaPlayer.getMediaPlayerInstance().playAudioFile(this,music);
//            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//            mediaPlayer.setDataSource(music);
//            mediaPlayer.prepare();
//            mediaPlayer.start();
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