package com.mmt.mayurkakade.data;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mmt.mayurkakade.R;
import com.mmt.mayurkakade.data.models.TrackModel;

import java.util.List;

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.ViewHolder> {

    List<TrackModel> tList;
    Context context;

    public TrackAdapter(List<TrackModel> tList, Context context) {
        this.tList = tList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.single_track_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_track_title.setText(tList.get(position).getTrack_title());
        holder.tv_track_artist.setText(tList.get(position).getArtist_name());
        holder.tv_track_playlist.setText(tList.get(position).getPlaylist_name());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.mmt.training");
                Bundle bundle = new Bundle();
                bundle.putString("track_title", tList.get(position).getTrack_title());
                bundle.putString("artist_name", tList.get(position).getArtist_name());
                bundle.putString("playlist_name", tList.get(position).getPlaylist_name());
                intent.putExtras(bundle);
                context.sendBroadcast(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context,holder.itemView);
                popupMenu.getMenuInflater().inflate(R.menu.track_long_click_menu,popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        tList.remove(position);
                        notifyDataSetChanged();
                        notifyItemRangeChanged(position,tList.size());
                        return true;
                    }
                });
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return tList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_track_title,tv_track_artist,tv_track_playlist;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_track_title = itemView.findViewById(R.id.tv_track_title);
            tv_track_artist = itemView.findViewById(R.id.tv_artist_name);
            tv_track_playlist = itemView.findViewById(R.id.tv_playlist_name);
        }
    }
}
