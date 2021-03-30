package com.mmt.mayurkakade.data.models;

public class TrackModel {
    String id,playlist_name,artist_name,track_title;

    public TrackModel() {
    }

    public TrackModel(String id, String playlist_name, String artist_name, String track_title) {
        this.id = id;
        this.playlist_name = playlist_name;
        this.artist_name = artist_name;
        this.track_title = track_title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaylist_name() {
        return playlist_name;
    }

    public void setPlaylist_name(String playlist_name) {
        this.playlist_name = playlist_name;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public String getTrack_title() {
        return track_title;
    }

    public void setTrack_title(String track_title) {
        this.track_title = track_title;
    }
}
