package com.example.sophie.spotifyapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class DeezerResult{

    private final static String TAG = "LogTag";

    @SerializedName("title")
    @Expose
    String title;

    @SerializedName("cover_big")
    @Expose
    String cover_small;

    @SerializedName("link")
    @Expose
    String link;

    @SerializedName("nb_tracks")
    @Expose
    Integer tracks;

    @SerializedName("explicit_lyrics")
    @Expose
    Boolean explicit;

    @SerializedName("record_type")
    @Expose
    String type;

    @SerializedName("tracklist")
    @Expose
    String tracklist;

    @SerializedName("artist")
    @Expose
    Artist artist;


    public DeezerResult(){

    }

    public DeezerResult(String title, String cover_small, String link, Integer tracks, Boolean explicit, String type, String tracklist){
        this.title = title;
        this.cover_small = cover_small;
        this.link = link;
        this.tracks = tracks;
        this.explicit = explicit;
        this.type = type;
        this.tracklist = tracklist;
    }

    public String getTitle(){
        return title;
    }

    public String getCover_small(){
        return cover_small;
    }

    public String getLink(){
        return link;
    }

    public Integer getTracks() {
        return tracks;
    }

    public Boolean getExplicit() {
        return explicit;
    }

    public String getType(){
        return type;
    }

    public String getTracklist() { return tracklist; }
}


