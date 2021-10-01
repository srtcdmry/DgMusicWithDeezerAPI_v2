package com.example.sophie.spotifyapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class ArtistResult {

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("picture_big")
    @Expose
    String picture;

    @SerializedName("link")
    @Expose
    String link;

    @SerializedName("nb_album")
    @Expose
    Integer albums;

    @SerializedName("nb_fan")
    @Expose
    Integer fans;

    @SerializedName("tracklist")
    @Expose
    String tracklist;

    @SerializedName("radio")
    @Expose
    Boolean radio;

    public ArtistResult(){

    }

    public ArtistResult(String name, String picture, String link, Integer albums, Integer fans, String tracklist, Boolean radio){
        this.name = name;
        this.picture = picture;
        this.link = link;
        this.albums = albums;
        this.fans = fans;
        this.tracklist = tracklist;
        this.radio = radio;
    }

    public String getName(){
        return name;
    }

    public String getPicture(){
        return picture;
    }

    public String getLink() { return link; }

    public Integer getAlbums() { return albums; }

    public Integer getFans() { return fans; }

    public String getTracklist() { return tracklist; }

    public Boolean getRadio() { return radio; }
}
