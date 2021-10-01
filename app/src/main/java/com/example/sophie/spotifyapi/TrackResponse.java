package com.example.sophie.spotifyapi;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class TrackResponse {

    @SerializedName("data")
    List<TrackResult> data;

    public List<TrackResult> getData() {
        return data;
    }
}
