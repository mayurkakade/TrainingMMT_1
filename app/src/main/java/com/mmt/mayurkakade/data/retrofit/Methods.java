package com.mmt.mayurkakade.data.retrofit;

import com.google.gson.JsonObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface Methods {

    @GET("get_all_tracks.php")
    Call<JsonObject> getTopSongs();
}