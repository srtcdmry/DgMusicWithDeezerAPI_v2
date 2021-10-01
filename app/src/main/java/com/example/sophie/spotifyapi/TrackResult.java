package com.example.sophie.spotifyapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class TrackResult {

    @SerializedName("title")
    @Expose
    String title;

    @SerializedName("duration")
    @Expose
    Integer duration;

    @SerializedName("preview")
    @Expose
    String preview;

    @SerializedName("artist")
    @Expose
    Artist artist;

    public TrackResult(){

    }

    public TrackResult(String title, Integer duration, String preview){
        this.title = title;
        this.duration = duration;
        this.preview = preview;
    }

    public String getTitle(){
        return title;
    }

    public Integer getDuration() {
        return duration;
    }

    public String getPreview(){
        return preview;
    }
}
