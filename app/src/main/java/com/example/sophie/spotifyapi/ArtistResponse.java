package com.example.sophie.spotifyapi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArtistResponse {

    @SerializedName("data")
    List<ArtistResult> data;

    public List<ArtistResult> getData() {
        return data;
    }
}
