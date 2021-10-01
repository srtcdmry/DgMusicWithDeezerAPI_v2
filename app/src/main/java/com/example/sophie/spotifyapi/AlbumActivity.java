package com.example.sophie.spotifyapi;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumActivity extends AppCompatActivity {


    @BindView(R.id.main2_title)
    TextView titleTextView;

    @BindView(R.id.main2_cover)
    ImageView coverImageView;

    @BindView(R.id.main2_type)
    TextView typeTextView;

    @BindView(R.id.main2_tracks)
    TextView tracksTextView;



   // @BindView(R.id.main2_explicit)
    //TextView explicitTextView;

   // @BindView(R.id.main2_artist)
    //ImageButton artistButton;

    @BindView(R.id.main2_artistText)
    TextView artistTextView;

    @BindView(R.id.main2_link)
    Button linkButton;

    @BindView(R.id.main2_tracksButton)
    Button tracksButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        ButterKnife.bind(this);

        Intent mainIntent = getIntent();
        final DeezerResult result = Parcels.unwrap(mainIntent.getParcelableExtra("SELECTED_RESULT"));

        titleTextView.setText(result.getTitle());
        typeTextView.setText("Albüm Türü : "+ result.getType());
        tracksTextView.setText("Şarkı Sayısı : "+ result.getTracks().toString());
        String explicit = result.getExplicit().toString().substring(0,1).toUpperCase() + result.getExplicit().toString().substring(1);
        //explicitTextView.setText("  Explicit: " + explicit );
        artistTextView.setText(result.artist.getName());
        linkButton.setText(result.getLink());

        Glide.with(this ).load(result.getCover_small()).into(coverImageView);

        linkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri u = Uri.parse(result.getLink());
                Intent intent_button = new Intent(Intent.ACTION_VIEW, u);
                startActivity(intent_button);
            }
        });

//        artistButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getBaseContext(), AlbumArtistActivity.class);
//                intent.putExtra("RESULTS_SELECTED", Parcels.wrap(result));
//               // startActivity(intent);
//
//            }
//        });

        tracksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AlbumTrackActivity.class);
                intent.putExtra("SELECTED_RESULT", Parcels.wrap(result));
                startActivity(intent);
            }
        });
    }

}
