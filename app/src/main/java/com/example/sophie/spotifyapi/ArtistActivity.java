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
import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArtistActivity extends AppCompatActivity {

    @BindView(R.id.main6_name)
    TextView nameTextView;

    @BindView(R.id.main6_picture)
    ImageView pictureImageView;

    @BindView(R.id.main6_albums)
    TextView albumsTextView;

    @BindView(R.id.main6_fans)
    TextView fansTextView;

//    @BindView(R.id.main6_radio)
//    TextView radioTextView;

    @BindView(R.id.main6_link)
    Button linkButton;

    @BindView(R.id.main6_tracklist)
    Button tracklistButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);

        ButterKnife.bind(this);

        Intent mainIntent = getIntent();
        final ArtistResult result = Parcels.unwrap(mainIntent.getParcelableExtra("SELECTED_RESULT"));

        nameTextView.setText(result.getName());
        albumsTextView.setText("Albüm Sayısı : " + result.getAlbums().toString());
        fansTextView.setText("Fan Sayısı : " + result.getFans().toString());
        //String radio = result.getRadio().toString().substring(0,1).toUpperCase() + result.getRadio().toString().substring(1);
        //radioTextView.setText("On the Radio: " + radio);
        linkButton.setText(result.getLink());
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

        tracklistButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ArtistTrackActivity.class);
                intent.putExtra("SELECTED_RESULT", Parcels.wrap(result));
                startActivity(intent);
            }
        });

    }
}
