package com.example.sophie.spotifyapi;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sophie.spotifyapi.databinding.ActivityMainBinding;
import com.example.sophie.spotifyapi.databinding.ActivitySearchAlbumActivityBinding;
import com.google.gson.Gson;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;

public class SearchAlbumActivity extends AppCompatActivity implements Results, OnResultSelectedInterface, OnPlaylistResultSelectedInterface, OnArtistResultSelectedInterface, OnTrackResultsSelectedInterface {
    private final static String TAG = "LogTag";
    private ActivitySearchAlbumActivityBinding binding;
    private String url = "https://api.deezer.com/search/album/?q=";
    private EditText name;
    private TextView title;
    private final ArrayList<DeezerResult> deezerResults = new ArrayList<>();
    private final ArrayList<TrackResult> trackResults = new ArrayList<>();
    private final ArrayList<ArtistResult> artistResults = new ArrayList<>();
    private final ArrayList<PlaylistResult> playlistResults = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.Adapter mAdapterTracks;
    private RecyclerView.Adapter mAdapterArtists;
    private RecyclerView.Adapter mAdapterPlaylist;
    private HttpGetRequest getRequest;
    private final Gson gson = new Gson();
    private final MediaPlayer mediaPlayer = new MediaPlayer();
    // private TextView textView;
    //private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchAlbumActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        // editText = findViewById(R.id.editText);
        //textView = findViewById(R.id.textView);
        // button = findViewById(R.id.button);
        Intent intent = getIntent();
        // textView.setText(data);


        setRecycleViewAlbum();
        String data = intent.getStringExtra("key");
        binding.textView.setText("'" + data + "'" + " Arama sonucu");

        // String data = editText.getText().toString();
        if (data.length() > 0) {
            searchAlbum(data);
        }


    }

    public void searchAlbum(CharSequence s) {

        if (getRequest != null && !getRequest.isCancelled()) {
            getRequest.cancel(true);
        }

        if (deezerResults.size() != 0) {
            deezerResults.clear();
        }

        url = "https://api.deezer.com/search/album/?q=";

        String myUrl = url + s + "&output=json";

        getRequest = new HttpGetRequest(this);
        getRequest.execute(myUrl);
    }

    public void setRecycleViewAlbum() {
        RecyclerView rv = findViewById(R.id.main_rv);
        rv.setHasFixedSize(true);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        rv.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter(this, deezerResults, this);
        rv.setAdapter(mAdapter);
        DividerItemDecoration id = new DividerItemDecoration(this, mLayoutManager.getOrientation());
        rv.addItemDecoration(id);
    }

    @Override
    public void handleResult(String result) {
        if (url.equals("https://api.deezer.com/search/album/?q=")) {
            DeezerResponse responseDeezer = gson.fromJson(result, DeezerResponse.class);
            deezerResults.clear();
            deezerResults.addAll(responseDeezer.getData());
            mAdapter.notifyDataSetChanged();
        } else if (url.equals("https://api.deezer.com/search/track/?q=")) {
            TrackResponse responseTrack = gson.fromJson(result, TrackResponse.class);
            trackResults.clear();
            trackResults.addAll(responseTrack.getData());
            mAdapterTracks.notifyDataSetChanged();
        } else if (url.equals("https://api.deezer.com/search/artist/?q=")) {
            ArtistResponse responseArtist = gson.fromJson(result, ArtistResponse.class);
            artistResults.clear();
            artistResults.addAll(responseArtist.getData());
            mAdapterArtists.notifyDataSetChanged();
        } else if (url.equals("https://api.deezer.com/search/playlist/?q=")) {
            PlaylistResponse responsePlaylist = gson.fromJson(result, PlaylistResponse.class);
            playlistResults.clear();
            playlistResults.addAll(responsePlaylist.getData());
            mAdapterPlaylist.notifyDataSetChanged();
        }
    }

    @Override
    public void onResultSelected(DeezerResult deezerResult) {
        Intent intent = new Intent(getBaseContext(), AlbumActivity.class);
        intent.putExtra("SELECTED_RESULT", Parcels.wrap(deezerResult));
        startActivity(intent);

    }

    @Override
    public void onResultSelected(TrackResult trackResult) {
        try {
            String music = trackResult.getPreview();
            Log.d(TAG, music);
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(music);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResultSelected(ArtistResult artistResult) {
        Intent intent = new Intent(getBaseContext(), ArtistActivity.class);
        intent.putExtra("SELECTED_RESULT", Parcels.wrap(artistResult));
        startActivity(intent);
    }

    @Override
    public void onResultSelected(PlaylistResult playlistResult) {
        Intent intent = new Intent(getBaseContext(), PlaylistActivity.class);
        intent.putExtra("SELECTED_RESULT", Parcels.wrap(playlistResult));
        startActivity(intent);

    }
}
