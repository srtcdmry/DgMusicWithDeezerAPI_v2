package com.example.sophie.spotifyapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Artist {

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("picture_big")
    @Expose
    String picture;

    @SerializedName("link")
    @Expose
    String link;

    @SerializedName("tracklist")
    @Expose
    String tracklist;

    public Artist(){

    }

    public Artist(String name, String picture, String tracklist){
        this.name = name;
        this.picture = picture;
        this.tracklist = tracklist;
    }

    public String getName(){
        return name;
    }

    public String getPicture(){
        return picture;
    }

    public String getLink(){
        return link;
    }

    public String getTracklist() { return tracklist; }
}
