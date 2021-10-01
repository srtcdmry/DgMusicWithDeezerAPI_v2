package com.example.sophie.spotifyapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class PlaylistResult {

    @SerializedName("title")
    @Expose
    String title;

    @SerializedName("picture_big")
    @Expose
    String picture;

    @SerializedName("public")
    @Expose
    Boolean _public;

    @SerializedName("nb_tracks")
    @Expose
    Integer tracks;

    @SerializedName("link")
    @Expose
    String link;

    @SerializedName("tracklist")
    @Expose
    String tracklist;

    @SerializedName("creation_date")
    @Expose
    String creation;

    public PlaylistResult(){

    }

    public PlaylistResult(String title, String picture, Boolean _public, Integer tracks, String link, String tracklist, String creation){
        this.title = title;
        this.picture = picture;
        this._public = _public;
        this.tracks = tracks;
        this.link = link;
        this.tracklist = tracklist;
        this.creation = creation;
    }

    public String getTitle(){
        return title;
    }

    public String getPicture(){
        return picture;
    }

    public Boolean get_public() {
        return _public;
    }

    public Integer getTracks() {
        return tracks;
    }

    public String getLink() {
        return link;
    }

    public String getCreation() {
        return creation;
    }

    public String getTracklist() {
        return tracklist;
    }
}
