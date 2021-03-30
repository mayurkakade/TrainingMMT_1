package com.mmt.mayurkakade.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mmt.mayurkakade.R;
import com.mmt.mayurkakade.data.TrackAdapter;
import com.mmt.mayurkakade.data.models.TrackModel;
import com.mmt.mayurkakade.data.retrofit.Methods;
import com.mmt.mayurkakade.data.retrofit.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MusicFragment extends Fragment {

    RecyclerView recyclerView;
    List<TrackModel> tList;
    TrackAdapter adapter;

    public static final String TAG = "retrofit_logs";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewMusicFragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        tList = new ArrayList<>();
        adapter = new TrackAdapter(tList,container.getContext());
        recyclerView.setAdapter(adapter);


        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<JsonObject> call = methods.getTopSongs();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NotNull Call<JsonObject> call, @NotNull Response<JsonObject> response) {
                JsonObject parentObject = response.body();
                JsonArray jsonArray = parentObject.get("tracks").getAsJsonArray();
                for (int i=0; i<jsonArray.size(); i++) {
                    getDataFromObjects(jsonArray.get(i));
                }
            }

            @Override
            public void onFailure(@NotNull Call<JsonObject> call, @NotNull Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });


        return view;
    }

    private void getDataFromObjects(JsonElement jsonElement) {
        TrackModel trackModel = new TrackModel(
                jsonElement.getAsJsonObject().get("id").getAsString(),
                jsonElement.getAsJsonObject().get("playlist_name").getAsString(),
                jsonElement.getAsJsonObject().get("artist_name").getAsString(),
                jsonElement.getAsJsonObject().get("track_title").getAsString()
        );
        tList.add(trackModel);
        adapter.notifyDataSetChanged();
    }
}