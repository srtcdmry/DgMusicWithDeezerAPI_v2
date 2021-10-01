package com.example.sophie.spotifyapi;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumArtistActivity extends AppCompatActivity {


    @BindView(R.id.main3_artist)
    TextView artistTextView;

    @BindView(R.id.main3_picture)
    ImageView pictureImageView;

    @BindView(R.id.main3_link)
    Button linkButton;

    @BindView(R.id.main3_tracklist)
    Button tracklistButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_artist);

        ButterKnife.bind(this);

        Intent mainIntent = getIntent();
        final DeezerResult result = Parcels.unwrap(mainIntent.getParcelableExtra("RESULTS_SELECTED"));

        artistTextView.setText(result.artist.getName());
        linkButton.setText(result.artist.getLink());

        Glide.with(this ).load(result.artist.getPicture()).into(pictureImageView);


        linkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri u = Uri.parse(result.artist.getLink());
                Intent intent_button = new Intent(Intent.ACTION_VIEW, u);
                startActivity(intent_button);
            }
        });

        tracklistButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AlbumArtistTrackActivity.class);
                intent.putExtra("SELECTED_RESULT", Parcels.wrap(result));
                startActivity(intent);
            }
        });
    }
}


