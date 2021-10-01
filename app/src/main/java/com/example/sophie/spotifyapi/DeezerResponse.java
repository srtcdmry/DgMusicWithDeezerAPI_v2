package com.example.sophie.spotifyapi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DeezerResponse {

    @SerializedName("data")
    List<DeezerResult> data;

    public List<DeezerResult> getData() {
        return data;
    }
}
