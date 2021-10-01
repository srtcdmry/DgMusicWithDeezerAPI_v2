package com.example.sophie.spotifyapi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlaylistResponse {

    @SerializedName("data")
    List<PlaylistResult> data;

    public List<PlaylistResult> getData() {
        return data;
    }
}
