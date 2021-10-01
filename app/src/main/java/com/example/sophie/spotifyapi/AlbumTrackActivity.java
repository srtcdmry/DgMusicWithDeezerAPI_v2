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

import com.example.sophie.spotifyapi.databinding.ActivityAlbumTrackBinding;
import com.example.sophie.spotifyapi.databinding.ActivityMainBinding;
import com.google.gson.Gson;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;

public class AlbumTrackActivity extends AppCompatActivity implements OnTrackResultsSelectedInterface, Results {
    private ActivityAlbumTrackBinding binding;
    private String url;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<TrackResult> trackResults = new ArrayList<>();
    private HttpGetRequest getRequest;
    private Gson gson = new Gson();
    final MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAlbumTrackBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent mainIntent = getIntent();
        final DeezerResult result = Parcels.unwrap(mainIntent.getParcelableExtra("SELECTED_RESULT"));

        url = result.getTracklist();

        ImageButton stopButton = findViewById(R.id.main5_stop);

        //RecyclerView rv = findViewById(R.id.main5_rv);
        binding.main5Rv.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        binding.main5Rv.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter2(trackResults, this);
        binding.main5Rv.setAdapter(mAdapter);
        DividerItemDecoration id = new DividerItemDecoration(this, mLayoutManager.getOrientation());
        binding.main5Rv.addItemDecoration(id);

        if (getRequest != null && !getRequest.isCancelled()) {
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
                if (MyMediaPlayer.getMediaPlayerInstance().mediaPlayer.isPlaying()) {
                    MyMediaPlayer.getMediaPlayerInstance().pauseAudioFile();
                    // mediaPlayer.reset();
                } else if (!MyMediaPlayer.getMediaPlayerInstance().mediaPlayer.isPlaying()) {
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
//            mediaPlayer.stop();
//            mediaPlayer.reset();
//            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);  mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//            mediaPlayer.setDataSource(music);
//            mediaPlayer.prepare();
//            mediaPlayer.start();
//
//        } catch (IOException e) {
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
