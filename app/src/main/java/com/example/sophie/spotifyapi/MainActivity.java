package com.example.sophie.spotifyapi;

import android.content.Intent;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sophie.spotifyapi.databinding.ActivityMainBinding;
import com.google.gson.Gson;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Results, OnResultSelectedInterface, OnPlaylistResultSelectedInterface, OnArtistResultSelectedInterface, OnTrackResultsSelectedInterface {

    private final static String TAG = "LogTag";
    public ActivityMainBinding binding ;
    private String url = "https://api.deezer.com/search/album/?q=";
   // private EditText name;
    //private TextView title;
    private ArrayList<DeezerResult> deezerResults = new ArrayList<>();
    private ArrayList<TrackResult> trackResults = new ArrayList<>();
    private ArrayList<ArtistResult> artistResults = new ArrayList<>();
    private ArrayList<PlaylistResult> playlistResults = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.Adapter mAdapterTracks;
    private RecyclerView.Adapter mAdapterArtists;
    private RecyclerView.Adapter mAdapterPlaylist;
    private HttpGetRequest getRequest;
    private Gson gson = new Gson();
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private ImageButton b;
    private ImageButton stopButton1;
    //private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

/*       // setContentView(R.layout.activity_main);
        //name = findViewById(R.id.main_input);
        //final ImageButton stopButton1 = findViewById(R.id.stopButton);
       // final ImageButton searchAlbum = findViewById(R.id.btn_searchAlbum);
       // final ImageButton searchTrack = findViewById(R.id.btn_searchTrack);
       // final ImageButton searchArtist = findViewById(R.id.btn_searchArtist);
        //final ImageButton searchPlaylist = findViewById(R.id.btn_searchPlaylist);
       // final ImageButton stopButton = findViewById(R.id.btn_stop);   viewBinding ile yapıldı. */


        binding.stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MyMediaPlayer.getMediaPlayerInstance().mediaPlayer.isPlaying()) {
                    MyMediaPlayer.getMediaPlayerInstance().pauseAudioFile();
                    // mediaPlayer.reset();
                }
                else if (!MyMediaPlayer.getMediaPlayerInstance().mediaPlayer.isPlaying()) {
                    MyMediaPlayer.getMediaPlayerInstance().mediaPlayer.start();

                }
            }
        });

        binding.btnSearchAlbum.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (binding.mainInput.getText().toString().length() > 0) {
                    Intent intent = new Intent(MainActivity.this, SearchAlbumActivity.class);
                    intent.putExtra("key", binding.mainInput.getText().toString());
                    startActivity(intent);
                }

//                setRecycleViewAlbum();
//                String s = name.getText().toString();
//                if (s.length() > 0) {
//                    searchAlbum(s);
//
//                }
            }
        });

        binding.btnSearchTrack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setRecycleViewTracks();
                String s = binding.mainInput.getText().toString();
                if (s.length() > 0) {
                    searchTrack(s);
                }
            }
        });

        binding.btnSearchArtist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setRecycleViewArtist();
                String s = binding.mainInput.getText().toString();
                if (s.length() > 0) {
                    searchArtist(s);
                }
            }
        });

        binding.btnSearchPlaylist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                setRecycleViewPlaylist();
                String s = binding.mainInput.getText().toString();
                if (s.length() > 0) {
                    searchPlaylist(s);
                }
            }
        });

        binding.btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    // mediaPlayer.reset();
                }
                else if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                }
            }
        });

//        name.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s != null && s.length() > 2) {
//                    if (url.equals("https://api.deezer.com/search/album/?q=")) {
//                        searchAlbum(name.getText().toString().toLowerCase());
//                    }else{
//                        searchTrack(name.getText().toString().toLowerCase());
//                    }
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
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

    public void searchTrack(CharSequence s) {

        if (getRequest != null && !getRequest.isCancelled()) {
            getRequest.cancel(true);
        }

        if (deezerResults.size() != 0) {
            deezerResults.clear();
        }

        url = "https://api.deezer.com/search/track/?q=";

        String myUrl = url + s + "&output=json";

        getRequest = new HttpGetRequest(this);
        getRequest.execute(myUrl);
    }

    public void searchArtist(CharSequence s) {

        if (getRequest != null && !getRequest.isCancelled()) {
            getRequest.cancel(true);
        }

        if (deezerResults.size() != 0) {
            deezerResults.clear();
        }

        url = "https://api.deezer.com/search/artist/?q=";

        String myUrl = url + s + "&output=json";

        getRequest = new HttpGetRequest(this);
        getRequest.execute(myUrl);

    }

    public void searchPlaylist(CharSequence s) {

        if (getRequest != null && !getRequest.isCancelled()) {
            getRequest.cancel(true);
        }

        if (deezerResults.size() != 0) {
            deezerResults.clear();
        }

        url = "https://api.deezer.com/search/playlist/?q=";

        String myUrl = url + s + "&output=json";

        getRequest = new HttpGetRequest(this);
        getRequest.execute(myUrl);

    }

    public void setRecycleViewTracks(){
        RecyclerView rv = findViewById(R.id.main_rv);
        rv.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(mLayoutManager);
        mAdapterTracks = new MyAdapter2(trackResults, this);
        rv.setAdapter(mAdapterTracks);
        DividerItemDecoration id = new DividerItemDecoration(this, mLayoutManager.getOrientation());
        rv.addItemDecoration(id);
    }

    public void setRecycleViewAlbum(){
        RecyclerView rv = findViewById(R.id.main_rv);
        rv.setHasFixedSize(true);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this,2);
        rv.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter(this, deezerResults, this);
        rv.setAdapter(mAdapter);
        DividerItemDecoration id = new DividerItemDecoration(this, mLayoutManager.getOrientation());
        rv.addItemDecoration(id);
    }

    public void setRecycleViewArtist(){
        RecyclerView rv = findViewById(R.id.main_rv);
        rv.setHasFixedSize(true);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this,2);
        rv.setLayoutManager(mLayoutManager);
        mAdapterArtists = new MyAdapter3(this, artistResults, this);
        rv.setAdapter(mAdapterArtists);
        DividerItemDecoration id = new DividerItemDecoration(this, mLayoutManager.getOrientation());
        rv.addItemDecoration(id);
    }

    public void setRecycleViewPlaylist(){
        RecyclerView rv = findViewById(R.id.main_rv);
        rv.setHasFixedSize(true);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this,2);
        rv.setLayoutManager(mLayoutManager);
        mAdapterPlaylist = new MyAdapter4(this, playlistResults, this);
        rv.setAdapter(mAdapterPlaylist);
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
        }else if (url.equals("https://api.deezer.com/search/track/?q=")){
            TrackResponse responseTrack = gson.fromJson(result, TrackResponse.class);
            trackResults.clear();
            trackResults.addAll(responseTrack.getData());
            mAdapterTracks.notifyDataSetChanged();
        }else if (url.equals("https://api.deezer.com/search/artist/?q=")) {
            ArtistResponse responseArtist = gson.fromJson(result, ArtistResponse.class);
            artistResults.clear();
            artistResults.addAll(responseArtist.getData());
            mAdapterArtists.notifyDataSetChanged();
        }else if (url.equals("https://api.deezer.com/search/playlist/?q=")) {
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
//        try {
        //Artist artist = new Artist();
            String music = trackResult.getPreview();
        //binding.textView2.setText("Şimdi Yürütülüyor.. "+ trackResult.artist.getName() + " - " + trackResult.getTitle());

        MyMediaPlayer.getMediaPlayerInstance().stopAudioFile();
        MyMediaPlayer.getMediaPlayerInstance().playAudioFile(this,music);
//            Log.d(TAG, music);
//            mediaPlayer.stop();
//            mediaPlayer.reset();
//            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//            mediaPlayer.setDataSource(music);
//            mediaPlayer.prepare();
//            mediaPlayer.start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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
