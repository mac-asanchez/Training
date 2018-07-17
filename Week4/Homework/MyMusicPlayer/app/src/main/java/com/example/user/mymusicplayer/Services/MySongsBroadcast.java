package com.example.user.mymusicplayer.Services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.mymusicplayer.Model.Constants;
import com.example.user.mymusicplayer.Model.RVSongAdapter;
import com.example.user.mymusicplayer.Model.Song;

import java.util.List;
import java.util.Objects;

public class MySongsBroadcast extends BroadcastReceiver {
    RecyclerView rvSongs;
    Context ctx;

    public MySongsBroadcast(RecyclerView rvSongs) {
        this.rvSongs = rvSongs;
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        ctx = context;
        switch (Objects.requireNonNull(intent.getAction())) {
            case Constants.ACTION.PASS_SONGS:
                List<Song> MySongs = intent.getParcelableArrayListExtra(Constants.SongsTag);
                populateRecycleView(MySongs);
                break;
        }
    }

    private void populateRecycleView(List<Song> Songs) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ctx);
        RVSongAdapter adapter = new RVSongAdapter(Songs, getSongListener());
        rvSongs.setLayoutManager(layoutManager);
        rvSongs.setAdapter(adapter);
    }

    @NonNull
    private RVSongAdapter.OnItemClickListener getSongListener() {
        return new RVSongAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Song song) {
                startService(song);
            }
        };
    }

    private void startService(Song song) {
        Intent musicPlayerServiceIntent = new Intent(ctx, MPService.class);
        musicPlayerServiceIntent.setAction(Constants.ACTION.PLAY);
        musicPlayerServiceIntent.putExtra("Song", song);
        ctx.startService(musicPlayerServiceIntent);
    }
}
