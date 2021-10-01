package com.example.sophie.spotifyapi;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;

public class MyMediaPlayer extends AppCompatActivity {
    MediaPlayer mediaPlayer = new MediaPlayer();

    private static MyMediaPlayer Instance;
    //String music = (String) getIntent().getSerializableExtra("key");


    static MyMediaPlayer getMediaPlayerInstance() {
        MediaPlayer mediaPlayer = new MediaPlayer();


        if (Instance == null) {
            return Instance = new MyMediaPlayer();
        }
        return Instance;
    }

    void playAudioFile(Context context, String music) {
        mediaPlayer.stop();
        mediaPlayer.reset();


        try {
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(music);
            mediaPlayer.prepare();
            mediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void stopAudioFile() {
        mediaPlayer.stop();
        mediaPlayer.reset();
    }

    void pauseAudioFile(){
        mediaPlayer.pause();
    }
    //mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);


}

