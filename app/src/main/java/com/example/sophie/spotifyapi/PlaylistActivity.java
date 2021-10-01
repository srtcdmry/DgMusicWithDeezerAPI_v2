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
import com.example.sophie.spotifyapi.databinding.ActivityMainBinding;
import com.example.sophie.spotifyapi.databinding.ActivityPlaylistBinding;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaylistActivity extends AppCompatActivity {

    @BindView(R.id.main8_title)
    TextView titleTextView;

    @BindView(R.id.main8_picture)
    ImageView pictureImageView;

//    @BindView(R.id.main8_creation)
//    TextView creationTextView;

    @BindView(R.id.main8_link)
    Button linkButton;

    @BindView(R.id.main8_tracks)
    TextView tracksTextView;

//    @BindView(R.id.main8_public)
//    TextView publicTextView;

    @BindView(R.id.main8_tracklist)
    Button tracklistButton;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        ButterKnife.bind(this);

        Intent mainIntent = getIntent();
        final PlaylistResult result = Parcels.unwrap(mainIntent.getParcelableExtra("SELECTED_RESULT"));
        titleTextView.setText(result.getTitle());
        //creationTextView.setText("Created At: " + result.getCreation());
       linkButton.setText(result.getLink());
        tracksTextView.setText("Şarkı Sayısı : " + result.getTracks().toString());
        String _public = result.get_public().toString().substring(0,1).toUpperCase() + result.get_public().toString().substring(1);
        //publicTextView.setText("Public :" + _public);
       tracklistButton.setText("Şarkılar");

        Glide.with(this ).load(result.getPicture()).into(pictureImageView);

        linkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri u = Uri.parse(result.getLink());
                Intent intent_button = new Intent(Intent.ACTION_VIEW, u);
                startActivity(intent_button);
            }
        });

        tracklistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), PlaylistTrackActivity.class);
                intent.putExtra("SELECTED_RESULT", Parcels.wrap(result));
                startActivity(intent);
            }
        });
    }
}
